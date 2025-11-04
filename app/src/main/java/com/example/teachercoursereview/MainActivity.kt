package com.example.teachercoursereview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    // UI Elements
    private lateinit var spinnerCategory: Spinner
    private lateinit var spinnerSubjects: Spinner
    private lateinit var ratingBar: RatingBar
    private lateinit var tvRatingLabel: TextView
    private lateinit var etReviewText: EditText
    private lateinit var btnSubmitReview: Button
    private lateinit var btnLogout: Button
    private lateinit var tvWelcome: TextView

    // Firebase
    private lateinit var dbReviews: DatabaseReference
    private lateinit var auth: FirebaseAuth

    // Data
    private val subjectsList = mutableListOf<Subject>()
    private lateinit var subjectSpinnerAdapter: ArrayAdapter<Subject>

    private val teacherList = listOf(
        Subject("teach_bca_01", "Dr. Shilpa Sharma (HoD, BCA)"),
        Subject("teach_bca_02", "Dr. Linesh Raja (BCA)"),
        Subject("teach_bca_03", "Dr. Amritpal Kaur (BCA)"),
        Subject("teach_cse_01", "Dr. Roheet Bhatnagar (CSE)"),
        Subject("teach_it_01", "Dr. Pratistha Mathur (HoD, IT)"),
        Subject("teach_ds_01", "Dr. Akhilesh K. Sharma (HoD, Data Science)"),
        Subject("teach_cse_02", "Dr. Amit Kumar Gupta (CSE)"),
        Subject("teach_ece_01", "Dr. Shilpi Birla (HoD, ECE)"),
        Subject("teach_cce_01", "Dr. Arjun Singh (HoD, CCE)"),
        Subject("teach_mech_01", "Dr. R K Gupta (Mech Engg)"),
        Subject("teach_mech_02", "Dr. Raviraja Adhikari (Mech Engg)"),
        Subject("teach_mecha_01", "Dr. Prabhat Ranjan (HoD, Mechatronics)"),
        Subject("teach_math_01", "Dr. Reema Jain (HoD, Maths)"),
        Subject("teach_phy_01", "Dr. K. P. Misra (HoD, Physics)"),
        Subject("teach_chem_01", "Dr. Praveen Surolia (HoD, Chemistry)"),
        Subject("teach_bio_01", "Dr. S. K. Srivastava (HoD, Biosciences)"),
        Subject("teach_bba_01", "Dr. Tina Shivnani (HoD, BBA)"),
        Subject("teach_com_01", "Dr. Sunny Dawar (HoD, Commerce)"),
        Subject("teach_mba_01", "Dr. Brajesh Kumar (Dean, TAPMI)"),
        Subject("teach_mba_02", "Dr. Kamakshi Mehta (HoD, TAPMI)"),
        Subject("teach_arts_01", "Dr. Richa Arora (HoD, Liberal Arts)"),
        Subject("teach_jmc_01", "Dr. Avneesh Kumar (Journalism)"),
        Subject("teach_law_01", "Dr. Sonu Agarwal (HoD, Law)"),
        Subject("teach_law_02", "Prof. (Dr) Vijaylakshmi Sharma (Law)"),
        Subject("teach_health_01", "Dr. K. A. Kotegar (Dean, Health)"),
        Subject("teach_pharm_01", "Dr. Krishnamurthy Bhat (Pharmacy)"),
        Subject("teach_psy_01", "Dr. V. Vineeth Kumar (HoD, Psychology)")
    )

    private val courseList = listOf(
        Subject("course_bca_01", "Course: C Programming (BCA)"),
        Subject("course_bca_02", "Course: Full Stack Web Dev (BCA)"),
        Subject("course_bca_03", "Course: Data Structures (BCA)"),
        Subject("course_btech_01", "Course: B.Tech - CSE"),
        Subject("course_btech_02", "Course: B.Tech - IT"),
        Subject("course_btech_03", "Course: B.Tech - Data Science"),
        Subject("course_btech_04", "Course: B.Tech - ECE"),
        Subject("course_btech_05", "Course: B.Tech - Mechanical"),
        Subject("course_btech_06", "Course: B.Tech - Civil")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase
        auth = FirebaseAuth.getInstance()

        // --- *** THIS IS THE FIX *** ---
        // We MUST tell Firebase the full URL of our database from the error log
        dbReviews = FirebaseDatabase.getInstance("https://teachercoursereview-default-rtdb.asia-southeast1.firebasedatabase.app")
            .getReference("reviews")
        // -------------------------------

        checkIfUserLoggedIn()

        // Initialize UI
        tvWelcome = findViewById(R.id.tvWelcome)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        spinnerSubjects = findViewById(R.id.spinnerSubjects)
        ratingBar = findViewById(R.id.ratingBar)
        tvRatingLabel = findViewById(R.id.tvRatingLabel)
        etReviewText = findViewById(R.id.etReviewText)
        btnSubmitReview = findViewById(R.id.btnSubmitReview)
        btnLogout = findViewById(R.id.btnLogout)

        // Set Welcome Text
        tvWelcome.text = "Welcome, ${auth.currentUser?.email}!"

        // Setup Category Spinner
        val categories = listOf("Select Category...", "Teachers", "Courses")
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        spinnerCategory.adapter = categoryAdapter

        // Setup the *second* dropdown (Subjects)
        subjectSpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, subjectsList)
        spinnerSubjects.adapter = subjectSpinnerAdapter

        // Set listener for the Category Spinner
        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCategory = categories[position]
                loadSubjects(selectedCategory)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        ratingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                val ratingWord = when (rating) {
                    0.0f -> "Please select a rating"
                    0.5f, 1.0f -> "Bad"
                    1.5f, 2.0f -> "Poor"
                    2.5f, 3.0f -> "Average"
                    3.5f, 4.0f -> "Good"
                    4.5f, 5.0f -> "Excellent"
                    else -> "Select a rating"
                }
                tvRatingLabel.text = ratingWord
            }

        // Set the "Submit" button
        btnSubmitReview.setOnClickListener {
            submitReview()
        }

        // Set the "Logout" button
        btnLogout.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun checkIfUserLoggedIn() {
        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun clearForm() {
        etReviewText.text.clear()
        ratingBar.rating = 0f
        spinnerCategory.setSelection(0)
        spinnerSubjects.setSelection(0)
        tvRatingLabel.text = "Please select a rating"
    }

    private fun loadSubjects(category: String) {
        subjectsList.clear()
        when (category) {
            "Teachers" -> {
                subjectsList.add(Subject("000", "Select a Teacher..."))
                subjectsList.addAll(teacherList)
            }
            "Courses" -> {
                subjectsList.add(Subject("000", "Select a Course..."))
                subjectsList.addAll(courseList)
            }
            else -> {
                subjectsList.add(Subject("000", "Please select a category first"))
            }
        }
        subjectSpinnerAdapter.notifyDataSetChanged()
    }

    private fun submitReview() {
        val selectedSubject = spinnerSubjects.selectedItem as? Subject
        val reviewText = etReviewText.text.toString().trim()
        val rating = ratingBar.rating
        val ratingLabel = tvRatingLabel.text.toString()
        val currentUser = auth.currentUser

        // 2. VALIDATE the input
        if (selectedSubject == null || selectedSubject.id == "000") {
            Toast.makeText(this, "Please select a subject", Toast.LENGTH_SHORT).show()
            return
        }
        if (reviewText.isEmpty() || rating == 0f) {
            Toast.makeText(this, "Please add a rating and review text", Toast.LENGTH_SHORT).show()
            return
        }

        val reviewId = dbReviews.push().key
        if (reviewId == null) {
            Toast.makeText(this, "Error creating review", Toast.LENGTH_SHORT).show()
            return
        }

        val sentiment = SentimentAnalyzer.analyze(reviewText)

        // Create the REAL Review object
        val review = Review(
            reviewId = reviewId,
            subjectId = selectedSubject.id,
            subjectName = selectedSubject.displayName,
            reviewText = reviewText,
            rating = rating,
            ratingLabel = ratingLabel,
            sentiment = sentiment,
            userId = currentUser!!.uid,
            userEmail = currentUser.email,
            timestamp = System.currentTimeMillis()
        )

        // Save the REAL review to Firebase
        dbReviews.child(reviewId).setValue(review)
            .addOnSuccessListener {
                // This will finally work!
                showThankYouDialog()
            }
            .addOnFailureListener {
                // We'll still show a toast if something else goes wrong
                Toast.makeText(this, "Failed to submit: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun showThankYouDialog() {
        AlertDialog.Builder(this)
            .setTitle("Thank You!")
            .setMessage("Your review has been submitted successfully.")
            .setPositiveButton("Submit Another") { dialog, _ ->
                clearForm()
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    private object SentimentAnalyzer {
        private val positiveKeywords = setOf("good", "great", "excellent", "awesome", "loved", "best", "helpful", "amazing", "easy", "clear", "recommend")
        private val negativeKeywords = setOf("bad", "terrible", "awful", "horrible", "hated", "worst", "confusing", "unhelpful", "difficult", "rude", "waste")

        fun analyze(text: String): String {
            val words = text.lowercase().split(" ")
            var score = 0
            for (word in words) {
                if (positiveKeywords.contains(word)) score++
                if (negativeKeywords.contains(word)) score--
            }
            return when {
                score > 0 -> "POSITIVE"
                score < 0 -> "NEGATIVE"
                else -> "NEUTRAL"
            }
        }
    }
}
package com.example.teachercoursereview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        // Check if user is logged in
        val currentUser = auth.currentUser
        if (currentUser == null) {
            // Not logged in, go to login
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        // Initialize views
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val cardBrowseTeachers = findViewById<CardView>(R.id.cardBrowseTeachers)
        val cardBrowseCourses = findViewById<CardView>(R.id.cardBrowseCourses)
        val cardMyReviews = findViewById<CardView>(R.id.cardMyReviews)
        val cardAddReview = findViewById<CardView>(R.id.cardAddReview)

        // Update welcome message
        tvWelcome.text = "Welcome, ${currentUser.email}!"

        // Logout button
        btnLogout.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Card click listeners (you can implement these features later)
        cardBrowseTeachers.setOnClickListener {
            Toast.makeText(this, "Browse Teachers feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        cardBrowseCourses.setOnClickListener {
            Toast.makeText(this, "Browse Courses feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        cardMyReviews.setOnClickListener {
            Toast.makeText(this, "My Reviews feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        cardAddReview.setOnClickListener {
            Toast.makeText(this, "Add Review feature coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
}
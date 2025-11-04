package com.example.teachercoursereview

// Blueprint for the review we save to Firebase
data class Review(
    val reviewId: String = "",
    val subjectId: String = "",
    val subjectName: String = "",
    val rating: Float = 0f,
    val ratingLabel: String = "", // Field for "Excellent", "Good", etc.
    val reviewText: String = "",
    val sentiment: String = "", // Field for "POSITIVE", "NEGATIVE"
    val userId: String = "",
    val userEmail: String? = null,
    val timestamp: Long = 0L
) {
    // *** THIS IS THE CRITICAL FIX ***
    // A no-argument constructor is REQUIRED for Firebase
    constructor() : this("", "", "", 0f, "", "", "", "", null, 0L)
}
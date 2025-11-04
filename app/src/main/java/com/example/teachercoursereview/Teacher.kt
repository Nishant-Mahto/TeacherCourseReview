package com.example.teachercoursereview

data class Teacher(
    var id: String = "",
    var name: String = "",
    var department: String = "",
    var email: String = "",
    var courses: List<String> = emptyList(),
    var rating: Double = 0.0,
    var reviewCount: Int = 0
)

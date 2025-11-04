package com.example.teachercoursereview

// Blueprint for an item in the dropdown
data class Subject(
    val id: String,
    val displayName: String
) {
    // This is important! This tells the Spinner (dropdown)
    // what text to show for each Subject object.
    override fun toString(): String {
        return displayName
    }
}
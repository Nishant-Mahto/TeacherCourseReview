package com.example.teachercoursereview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TeacherAdapter(
    private val teachers: List<Teacher>,
    private val onTeacherClick: (Teacher) -> Unit
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    class TeacherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvTeacherName)
        val tvDepartment: TextView = view.findViewById(R.id.tvDepartment)
        val tvRating: TextView = view.findViewById(R.id.tvRating)
        val tvReviewCount: TextView = view.findViewById(R.id.tvReviewCount)
        val tvCourses: TextView = view.findViewById(R.id.tvCourses)
        val btnViewReviews: Button = view.findViewById(R.id.btnViewReviews)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_teacher, parent, false)
        return TeacherViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teachers[position]

        holder.tvName.text = teacher.name
        holder.tvDepartment.text = teacher.department
        holder.tvRating.text = String.format("%.1f", teacher.rating)
        holder.tvReviewCount.text = "(${teacher.reviewCount} reviews)"
        holder.tvCourses.text = "Courses: ${teacher.courses.joinToString(", ")}"

        holder.btnViewReviews.setOnClickListener {
            onTeacherClick(teacher)
        }

        holder.itemView.setOnClickListener {
            onTeacherClick(teacher)
        }
    }

    override fun getItemCount() = teachers.size
}

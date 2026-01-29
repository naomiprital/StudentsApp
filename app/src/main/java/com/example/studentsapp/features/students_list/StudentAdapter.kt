package com.example.studentsapp.features.students_list

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.features.student_details.StudentDetailsActivity
import com.example.studentsapp.models.StudentModel

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    override fun getItemCount(): Int = StudentModel.students.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_list_row, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = StudentModel.students[position]

        holder.name.text = student.name
        holder.id.text = student.id
        holder.cb.isChecked = student.isChecked

        holder.cb.setOnClickListener {
            student.isChecked = holder.cb.isChecked
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, StudentDetailsActivity::class.java)
            intent.putExtra("student_pos", position)
            it.context.startActivity(intent)
        }
    }

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.student_row_name)
        val id: TextView = view.findViewById(R.id.student_row_id)
        val cb: CheckBox = view.findViewById(R.id.student_row_check)
    }
}
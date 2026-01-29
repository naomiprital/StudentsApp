package com.example.studentsapp.features.student_details

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.features.edit_student.EditStudentActivity
import com.example.studentsapp.models.StudentModel
import com.google.android.material.appbar.MaterialToolbar

class StudentDetailsActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val toolbar: MaterialToolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Student Details"

        val position = intent.getIntExtra("student_pos", -1)

        if (position != -1) {
            val student = StudentModel.students[position]

            findViewById<TextView>(R.id.name_input).text = student.name
            findViewById<TextView>(R.id.id_input).text = student.id
            findViewById<TextView>(R.id.phone_input).text = student.phone
            findViewById<TextView>(R.id.address_input).text = student.address

            val checkBox = findViewById<CheckBox>(R.id.details_check)
            checkBox.isChecked = student.isChecked
            checkBox.text = if (student.isChecked) "Checked" else "Not Checked"

            findViewById<Button>(R.id.edit_toggle_button).setOnClickListener {
                val intent = Intent(this, EditStudentActivity::class.java).apply {
                    putExtra("student_name", student.name)
                    putExtra("student_id", student.id)
                    putExtra("student_phone", student.phone)
                    putExtra("student_address", student.address)
                    putExtra("student_checkbox", student.isChecked)
                    putExtra("student_pos", position)
                }

                startActivity(intent)
                finish()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
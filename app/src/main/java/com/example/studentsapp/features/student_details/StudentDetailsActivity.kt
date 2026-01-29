package com.example.studentsapp.features.student_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
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

            findViewById<TextView>(R.id.details_name_tv).text = "Name: ${student.name}"
            findViewById<TextView>(R.id.details_id_tv).text = "ID: ${student.id}"
            findViewById<TextView>(R.id.details_phone_tv).text = "Phone: ${student.phone}"
            findViewById<TextView>(R.id.details_address_tv).text = "Address: ${student.address}"

            val checkBox = findViewById<CheckBox>(R.id.details_check)
            checkBox.isChecked = student.isChecked
            checkBox.text = if (student.isChecked) "Checked" else "Not Checked"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
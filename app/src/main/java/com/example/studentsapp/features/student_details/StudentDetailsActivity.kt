package com.example.studentsapp.features.student_details

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.databinding.ActivityStudentDetailsBinding
import com.example.studentsapp.features.edit_student.EditStudentActivity
import com.example.studentsapp.models.StudentModel
import com.google.android.material.appbar.MaterialToolbar

class StudentDetailsActivity : AppCompatActivity() {
    private var binding: ActivityStudentDetailsBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

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

            binding?.editButton?.setOnClickListener {
                val intent = Intent(this,
                    EditStudentActivity::class.java).apply {

                    putExtra("student_pos", position)
                    putExtra("student_name", student.name)
                    putExtra("student_id", student.id)
                    putExtra("student_phone", student.phone)
                    putExtra("student_address", student.address)
                    putExtra("student_checkbox", student.isChecked)
                }

                startActivity(intent)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
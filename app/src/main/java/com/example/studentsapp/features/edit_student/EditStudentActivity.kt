package com.example.studentsapp.features.edit_student

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.EditStudentBinding

class EditStudentActivity: AppCompatActivity() {
    private lateinit var binding: EditStudentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
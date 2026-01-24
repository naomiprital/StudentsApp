package com.example.studentsapp.models

data class Student(
    var name: String,
    var id: String,
    var isChecked: Boolean = false,
)
package com.example.studentsapp.models

object StudentModel {
    val students = mutableListOf<Student>()

    init {
        for (i in 1..50) {
            students.add(
                Student(
                    name = "Student $i",
                    id = "${1000 + i}",
                    isChecked = i % 2 == 0
                )
            )
        }
    }
}
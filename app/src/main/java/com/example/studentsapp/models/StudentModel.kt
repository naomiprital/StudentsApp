package com.example.studentsapp.models

object StudentModel {
    val students = mutableListOf<Student>()

    init {
        for (i in 1..50) {
            val phoneNumber = "052-${i.toString().padStart(7, '0')}"

            students.add(
                Student(
                    name = "Student $i",
                    id = "${1000 + i}",
                    phone = phoneNumber,
                    address = "Hollywood Blvd. $i",
                    isChecked = i % 2 == 0
                )
            )
        }
    }
}
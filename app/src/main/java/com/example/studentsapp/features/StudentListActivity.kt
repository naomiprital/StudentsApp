package com.example.studentsapp.features

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val toolbar: MaterialToolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Students List"

        val rv: RecyclerView = findViewById(R.id.student_list_rv)
        rv.layoutManager = LinearLayoutManager(this)

        val adapter = StudentAdapter()
        rv.adapter = adapter

        val addBtn: FloatingActionButton = findViewById(R.id.student_list_add_btn)
        addBtn.setOnClickListener {
            Toast.makeText(this, "TODO: Add student logic", Toast.LENGTH_SHORT).show()
        }
    }
}
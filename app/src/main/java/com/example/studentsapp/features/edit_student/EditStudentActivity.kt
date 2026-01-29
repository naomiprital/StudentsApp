package com.example.studentsapp.features.edit_student

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.databinding.EditStudentBinding
import com.example.studentsapp.features.students_list.StudentListActivity
import com.example.studentsapp.models.StudentModel
import com.google.android.material.appbar.MaterialToolbar

class EditStudentActivity: AppCompatActivity() {
    private val title = "Edit Student"
    private lateinit var binding: EditStudentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()

        findViewById<Button>(R.id.delete_student_button).setOnClickListener {
            onDelete()
        }

        findViewById<Button>(R.id.cancel_button).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.save_button).setOnClickListener {
            onSave()
        }
    }
    private fun setUpToolBar() {
        val toolbar: MaterialToolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title

    }

    private fun onDelete() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Delete Student")
        builder.setMessage("Are you sure you want to delete this student?")

        builder.setPositiveButton("Yes, Delete") { dialog, _ ->
            val position = intent.getIntExtra("student_pos", -1)

            StudentModel.students.removeAt(position)

            dialog.dismiss()
            Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, StudentListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

            finish()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun onSave() {
        val name = binding.includedForm.nameInput.text.toString().trim()
        val id = binding.includedForm.idInput.text.toString().trim()
        val phone = binding.includedForm.phoneInput.text.toString().trim()
        val address = binding.includedForm.addressInput.text.toString().trim()

        when {
            name.isEmpty() -> {
                binding.includedForm.nameInput.error = "Name cannot be empty"
                binding.includedForm.nameInput.requestFocus()
            }
            id.isEmpty() -> {
                binding.includedForm.idInput.error = "ID is required"
                binding.includedForm.idInput.requestFocus()
            }
            phone.isEmpty() -> {
                binding.includedForm.phoneInput.error = "Phone is required"
                binding.includedForm.phoneInput.requestFocus()
            }
            address.isEmpty() -> {
                binding.includedForm.addressInput.error = "Address is required"
                binding.includedForm.addressInput.requestFocus()
            }
            else -> {
                val position = intent.getIntExtra("student_pos", -1)
                val student = StudentModel.students[position]
                student.name = name
                student.id = id
                student.phone = phone
                student.address = address
                student.isChecked = binding.includedForm.detailsCheck.isChecked
                Toast.makeText(this, "${student.name} updated successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun setUpView() {
        setUpToolBar()

        findViewById<Button>(R.id.edit_toggle_button).visibility = View.GONE

        binding.includedForm.nameInput.setText(intent.getStringExtra("student_name"))
        binding.includedForm.idInput.setText(intent.getStringExtra("student_id"))
        binding.includedForm.phoneInput.setText(intent.getStringExtra("student_phone"))
        binding.includedForm.addressInput.setText(intent.getStringExtra("student_address"))
        binding.includedForm.detailsCheck.isChecked = intent.getBooleanExtra("student_checkbox", false)

        binding.includedForm.nameInput.apply {
            isEnabled = true
            setBackgroundResource(androidx.appcompat.R.drawable.abc_edit_text_material)
        }

        binding.includedForm.idInput.apply {
            isEnabled = true
            setBackgroundResource(androidx.appcompat.R.drawable.abc_edit_text_material)
        }

        binding.includedForm.phoneInput.apply {
            isEnabled = true
            setBackgroundResource(androidx.appcompat.R.drawable.abc_edit_text_material)
        }

        binding.includedForm.addressInput.apply {
            isEnabled = true
            setBackgroundResource(androidx.appcompat.R.drawable.abc_edit_text_material)
        }

        binding.includedForm.detailsCheck.apply {
            isClickable = true
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
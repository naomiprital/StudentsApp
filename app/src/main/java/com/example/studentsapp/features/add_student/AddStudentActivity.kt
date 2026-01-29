package com.example.studentsapp.features.add_student

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.databinding.AddStudentBinding
import com.example.studentsapp.features.students_list.StudentListActivity
import com.example.studentsapp.models.Student
import com.example.studentsapp.models.StudentModel
import com.google.android.material.appbar.MaterialToolbar


class AddStudentActivity : AppCompatActivity() {

    private var binding: AddStudentBinding? = null
    private val title = "Add Student"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AddStudentBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setUpToolBar()

        val nameEditText = findViewById<EditText>(R.id.name_edit_text)
        val idEditText = findViewById<EditText>(R.id.id_edit_text)
        val phoneEditText = findViewById<EditText>(R.id.phone_edit_text)
        val addressEditText = findViewById<EditText>(R.id.address_edit_text)
        val termsCheckBox = findViewById<CheckBox>(R.id.terms_checkbox)

        binding?.resetButton?.setOnClickListener {
            resetFields(nameEditText, idEditText, phoneEditText, addressEditText, termsCheckBox)
        }

        binding?.saveButton?.setOnClickListener {
            saveStudent(nameEditText, idEditText, phoneEditText, addressEditText, termsCheckBox)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
    private fun setUpToolBar() {
        val toolbar: MaterialToolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

    private fun resetFields(nameEditText: EditText, idEditText: EditText, phoneEditText: EditText,
                            addressEditText: EditText, termsCheckBox: CheckBox) {
        nameEditText.text.clear()
        idEditText.text.clear()
        phoneEditText.text.clear()
        addressEditText.text.clear()

        termsCheckBox.isChecked = false

        nameEditText.requestFocus()
    }

    private fun saveStudent(nameEditText: EditText, idEditText: EditText, phoneEditText: EditText,
                            addressEditText: EditText, termsCheckBox: CheckBox) {
        val name = nameEditText.text.toString().trim()
        val id = idEditText.text.toString().trim()
        val phone = phoneEditText.text.toString().trim()
        val address = addressEditText.text.toString().trim()

        when {
            name.isEmpty() -> {
                nameEditText.error = "Name is required"
                nameEditText.requestFocus()
            }

            id.isEmpty() -> {
                idEditText.error = "ID is required"
                idEditText.requestFocus()
            }

            phone.isEmpty() -> {
                phoneEditText.error = "Phone is required"
                phoneEditText.requestFocus()
            }

            address.isEmpty() -> {
                addressEditText.error = "Address is required"
                addressEditText.requestFocus()
            }

            else -> {
                Toast.makeText(this, "$name added successfully", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, StudentListActivity::class.java)
                val student = Student(
                    name = name,
                    id = id,
                    phone = phone,
                    address = address,
                    isChecked = termsCheckBox.isChecked
                )
                StudentModel.students.add(student)
                startActivity(intent)
            }
        }
    }
}
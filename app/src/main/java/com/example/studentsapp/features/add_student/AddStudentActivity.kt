package com.example.studentsapp.features.add_student

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.databinding.ActivityAddStudentBinding
import com.example.studentsapp.models.Student

class AddStudentActivity : AppCompatActivity() {
        private var binding: ActivityAddStudentBinding? = null
        private val labels = listOf("Name", "Id", "Phone", "Address")
        private val inputs = mutableMapOf<String, EditText>()
        private val title = "New Students"

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()

            binding = ActivityAddStudentBinding.inflate(layoutInflater)
            setContentView(binding?.root)
            supportActionBar?.title = title
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            setupView()

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        override fun onSupportNavigateUp(): Boolean {
            onBackPressedDispatcher.onBackPressed()
            return true
        }

        private fun setupView() {
            val fieldsContainer = binding?.fieldsContainer
            val inflater = layoutInflater

            labels.forEach { label ->
                val item = inflater.inflate(R.layout.form_item_field, fieldsContainer, false)
                val labelView = item.findViewById<TextView>(R.id.field_label)
                val inputView = item.findViewById<EditText>(R.id.field_input)

                labelView.text = label
                inputs[label] = inputView
                fieldsContainer?.addView(item)
            }

            binding?.cancelButton?.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            binding?.saveStudentButton?.setOnClickListener {
                val data = labels.associateWith { label ->
                    inputs[label]?.text?.toString().orEmpty().trim()
                }

                val isChecked = binding?.checkbox?.isChecked ?: false

                if (data.values.all { it.isNotEmpty() }) {
                    val student = Student(
                        name = data.getValue("Name"),
                        id = data.getValue("Id"),
                        phone = data.getValue("Phone"),
                        address = data.getValue("Address"),
                        isChecked = isChecked
                    )
                    StudentsList.shared.students.add(student)

                    inputs.values.forEach { it.text?.clear() }
                    binding?.checkbox?.isChecked = false

                    setResult(RESULT_OK)
                    onBackPressedDispatcher.onBackPressed()
                } else {
                    var firstEmpty: EditText? = null
                    labels.forEach { label ->
                        val value = data[label].orEmpty()
                        val input = inputs[label]
                        if (value.isEmpty()) {
                            input?.error = getString(R.string.error_required)
                            if (firstEmpty == null) firstEmpty = input
                        } else {
                            input?.error = null
                        }
                    }
                    firstEmpty?.requestFocus()
                }
            }

    }

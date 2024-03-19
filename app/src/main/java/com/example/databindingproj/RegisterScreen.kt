package com.example.databindingproj

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databindingproj.databinding.ActivityRegisterScreenBinding
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.regex.Pattern


class RegisterScreen : AppCompatActivity() {

    lateinit var registerBinding : ActivityRegisterScreenBinding
    var dob = "";
    var gender = "";

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_screen)
        registerBinding.selectDOB.setOnClickListener {

            showDatePicker();
        }

        val genders = resources.getStringArray(R.array.Genders)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genders)
        registerBinding.genderSpinner.adapter = adapter

        registerBinding.genderSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                gender = genders[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        registerBinding.registerButton.setOnClickListener {

            val firstName =  registerBinding.firstName.text.toString()
            val lastName =  registerBinding.lastName.text.toString()
            val emailId =  registerBinding.firstName.text.toString()
            val password =  registerBinding.firstName.text.toString()
            val confirmPassword =  registerBinding.firstName.text.toString()
            val dob = registerBinding.selectDOB.text.toString()

            if (Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {

                if(firstName.isNotEmpty() && lastName.isNotEmpty() && emailId.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && dob.isNotEmpty() && gender.isNotEmpty()) {

                    if (password == confirmPassword) {

                        if (registerBinding.checkBoxSelect.isChecked) {

                            Toast.makeText(this@RegisterScreen, "Registered Successfully.", Toast.LENGTH_SHORT).show()
                        } else {

                            Toast.makeText(this@RegisterScreen, "Accept all terms & and conditions to register.", Toast.LENGTH_SHORT).show()
                        }

                    } else {
                        Toast.makeText(this@RegisterScreen, "Passwords mismatching!", Toast.LENGTH_SHORT).show()
                    }

                } else {

                    Toast.makeText(this@RegisterScreen, "All fields are mandatory", Toast.LENGTH_SHORT).show()
                }

            } else {

                Toast.makeText(applicationContext,"Invalid email id!",Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun showDatePicker() {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this, {DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                // Format the selected date into a string
                val formattedDate = dateFormat.format(selectedDate.time)
                // Update the TextView to display the selected date with the "Selected Date: " prefix
                dob = formattedDate
                registerBinding.selectDOB.text = "Date of Birth: $dob"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

}
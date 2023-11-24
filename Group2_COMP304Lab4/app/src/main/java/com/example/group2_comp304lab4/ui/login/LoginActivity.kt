package com.example.group2_comp304lab4.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.group2_comp304lab4.HospitalApplication
import com.example.group2_comp304lab4.R
import com.example.group2_comp304lab4.ui.patient.PatientActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private val nurseViewModel: NurseViewModel by viewModels {
        NurseViewModelFactory((application as HospitalApplication).nurseRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // get views
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // set onclick listener for login button
        btnLogin.setOnClickListener {
            authenticate()
        }

    }

    // function for authenticating user by username and password
    private fun authenticate() {

        // check if user input username and password
        if (etUsername.text.trim().isNotEmpty() && etPassword.text.trim().isNotEmpty()) {
            val username = etUsername.text.trim().toString().toLong()
            val password = etPassword.text.trim().toString()

            // observe any update on the event
            nurseViewModel.authenticatedUser.observe(this, Observer { user ->
                if (user != null) {
                    val patientIntent = Intent(this, PatientActivity::class.java)
                    val appPref = getSharedPreferences((application as HospitalApplication).PREF_NAME, MODE_PRIVATE)
                    val editor = appPref.edit()
                    editor.putLong((application as HospitalApplication).PREF_NURSE_ID, username)
                    editor.apply()

                    startActivity(patientIntent)
                    finish()
                }else{
                    Toast.makeText(this, "Invalid username/ password", Toast.LENGTH_SHORT).show()
                }
            })

            // Observe changes in the authentication error LiveData
            nurseViewModel.authenticationError.observe(this, Observer { error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            })

            // trigger authentication
            nurseViewModel.authenticateUser(username, password)
        }

    }

}
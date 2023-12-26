package com.example.smokemate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import forgot.password.activities.ForgotPasswordPhoneActivity
import registrastion.activities.RegistrationActivity


class MainActivity: AppCompatActivity() {

    private lateinit var languageSelect: TextView
    private lateinit var loginField: EditText
    private lateinit var passwordField: EditText
    private lateinit var loginButton: Button
    private lateinit var resetPasswordButton: Button
    private lateinit var createAccountButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_main)

        loginField = findViewById(R.id.loginField)
        passwordField = findViewById(R.id.passwordField)
        loginButton = findViewById(R.id.loginButton)
        resetPasswordButton = findViewById(R.id.resetPasswordButton)
        createAccountButton = findViewById(R.id.createAccountButton)
        languageSelect = findViewById(R.id.languageSelect)

        loginButton.setOnClickListener {

            if(loginField.areFieldsNotEmpty() && passwordField.areFieldsNotEmpty())
            {
                setRedBorderForEmptyFields(loginField, passwordField)
/*Сделать свяь с базой данных*/
            }
            else
            {
                setRedBorderForEmptyFields(loginField, passwordField)
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        createAccountButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        resetPasswordButton.setOnClickListener {
            val intent = Intent(this, ForgotPasswordPhoneActivity::class.java)
            startActivity(intent)
        }

        languageSelect.setOnClickListener {
/*Вызов функции из другого класса*/
        }
    }
}

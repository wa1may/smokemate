package com.example.smokemate

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import forgot.password.activities.ForgotPasswordPhoneActivity
import registrastion.activities.RegistrationActivity

class MainActivity : AppCompatActivity() {

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

            if(areFieldsNotEmpty())
            {
/*Сделать свяь с базой данных*/
            }
            else
            {
                setRedBorderForEmptyFields()
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

    private fun areFieldsNotEmpty(): Boolean {
        return (loginField.text.isNotEmpty() &&
                passwordField.text.isNotEmpty())
    }

    private fun setRedBorderForEmptyFields() {
        if (loginField.text.isEmpty()) {
            loginField.setBackgroundResource(R.drawable.textline_error)
        } else {
            loginField.setBackgroundResource(R.drawable.textline)
        }

        if (passwordField.text.isEmpty()) {
            passwordField.setBackgroundResource(R.drawable.textline_error)
        } else {
            passwordField.setBackgroundResource(R.drawable.textline)
        }
    }
}

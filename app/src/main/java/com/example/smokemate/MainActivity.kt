package com.example.smokemate

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import forgot.password.activities.ForgotPasswordPhoneActivity
import registrastion.activities.RegistrationActivity


class MainActivity : AppCompatActivity() {

    private lateinit var languageSelect: TextView
    private lateinit var loginField: EditText
    private lateinit var passwordField: EditText
    private lateinit var loginButton: Button
    private lateinit var resetPasswordButton: Button
    private lateinit var createAccountButton: Button
    private lateinit var errorMessage: TextView
    private lateinit var languageFlag: ImageView
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_main)

        loginField = findViewById(R.id.loginField)
        passwordField = findViewById(R.id.passwordField)
        loginButton = findViewById(R.id.loginButton)
        resetPasswordButton = findViewById(R.id.resetPasswordButton)
        createAccountButton = findViewById(R.id.createAccountButton)
        languageSelect = findViewById(R.id.languageSelect)
        errorMessage = findViewById(R.id.errorMessage)
        languageFlag = findViewById(R.id.languageFlag)

        sharedPreferences = getSharedPreferences(LanguageManager.getLanguageSettingsKey(), Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        LanguageManager.loadLocal(this, languageFlag, languageSelect)

        LanguageManager.restartActivity(this)

        loginButton.setOnClickListener {
            if (loginField.areFieldsNotEmpty() && passwordField.areFieldsNotEmpty()) {
                setRedBorderForEmptyFields(loginField, passwordField)
                errorMessage.text = " "
                /*Сделать связь с базой данных*/
            } else {
                setRedBorderForEmptyFields(loginField, passwordField)
                errorMessage.text = getString(R.string.emptyFields)
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
            LanguageManager.openDialogForLanguageChange(this, languageFlag, languageSelect, editor, sharedPreferences, this)
        }
    }
}
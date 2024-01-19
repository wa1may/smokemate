package com.example.smokemate

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import forgot.password.activities.ForgotPasswordPhoneActivity
import registrastion.activities.RegistrationActivity
import java.util.Locale


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

        LoadLocal()

        loginButton.setOnClickListener {

            if (loginField.areFieldsNotEmpty() && passwordField.areFieldsNotEmpty()) {
                setRedBorderForEmptyFields(loginField, passwordField)
                errorMessage.text = " "
                /*Сделать свяь с базой данных*/
            } else {
                setRedBorderForEmptyFields(loginField, passwordField)
                errorMessage.text = "Будь ласка, заповніть всі поля"
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
            openDialogforLanguageChange()
        }
    }

    private fun LoadLocal() {
        sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val selectedFlag = sharedPreferences.getInt("selected_flag", R.drawable.language_russian)
        val selectedLanguage = sharedPreferences.getString("selected_language", "ru") // "ru" - язык по умолчанию
        languageFlag.setImageResource(selectedFlag)
        languageSelect.text = getLanguageName(selectedLanguage!!)
    }

    private fun openDialogforLanguageChange() {
        val list = arrayOf("English", "Español", "Français", "Deutsch", "Italiano", "Polski", "Português", "Русский", "Українська")

        val alertDialog = AlertDialog.Builder(this)

        alertDialog.setTitle("Select Language")

        alertDialog.setSingleChoiceItems(list, -1, DialogInterface.OnClickListener { dialog, i ->

            if (i == 0) {
                setLocal("en", R.drawable.language_english)
                recreate()
            } else if (i == 1) {
                setLocal("es", R.drawable.language_spanish)
                recreate()
            } else if (i == 2) {
                setLocal("fr", R.drawable.language_french)
                recreate()
            } else if (i == 3) {
                setLocal("de", R.drawable.language_german)
                recreate()
            } else if (i == 4) {
                setLocal("it", R.drawable.language_italian)
                recreate()
            } else if (i == 5) {
                setLocal("pl", R.drawable.language_polish)
                recreate()
            } else if (i == 6) {
                setLocal("pt", R.drawable.language_portuguese)
                recreate()
            } else if (i == 7) {
                setLocal("ru", R.drawable.language_russian)
                recreate()
            } else if (i == 8) {
                setLocal("uk", R.drawable.language_ukrainian)
                recreate()
            }

            dialog.dismiss()
        })

        alertDialog.setNeutralButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val mDialog = alertDialog.create()

        languageSelect.text = getLanguageName(sharedPreferences.getString("selected_language", "ru")!!)
        mDialog.show()
    }

    private fun setLocal(language: String, flagResourceId: Int) {
        val local = Locale(language)
        Locale.setDefault(local)
        val config = resources.configuration
        config.setLocale(local)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("selected_language", language)
        editor.putInt("selected_flag", flagResourceId)
        editor.apply()

        languageFlag.setImageResource(flagResourceId)
    }

    private fun getLanguageName(language: String): String {
        when (language) {
            "en" -> return "English"
            "es" -> return "Español"
            "fr" -> return "Français"
            "de" -> return "Deutsch"
            "it" -> return "Italiano"
            "pl" -> return "Polski"
            "pt" -> return "Português"
            "ru" -> return "Русский"
            "uk" -> return "Українська"
            else -> return "Unknown"
        }
    }
}
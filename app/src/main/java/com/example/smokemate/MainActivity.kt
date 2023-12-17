package com.example.smokemate

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import forgot.password.activities.ForgotPasswordPhoneActivity
import registrastion.activities.RegistrationActivity

class MainActivity : AppCompatActivity() {

    private lateinit var LanguageSelect: TextView
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var loginField: EditText
    private lateinit var passwordField: EditText
    private lateinit var loginButton: Button
    private lateinit var resetPasswordButton: Button
    private lateinit var db: DataBase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_main)

        val CreateAccount: android.widget.Button = findViewById(R.id.CreateAccount)
        loginField = findViewById(R.id.loginField)
        passwordField = findViewById(R.id.passwordField)
        loginButton = findViewById(R.id.loginButton)
        resetPasswordButton = findViewById(R.id.resetPasswordButton)
        db = DataBase(this)

        CreateAccount.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val username = loginField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if(areFieldsNotEmpty())
            {
                if (db.authenticateUser(username, password)) {
                Toast.makeText(this, "Вход выполнен успешно", Toast.LENGTH_SHORT).show()
                } else {
                Toast.makeText(this, "Неверные учетные данные", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        resetPasswordButton.setOnClickListener {
            val intent = Intent(this, ForgotPasswordPhoneActivity::class.java)
            startActivity(intent)
        }

        LoadLocal()

        LanguageSelect = findViewById(R.id.LanguageSelect)

        LanguageSelect.setOnClickListener {
            openDialogforLanguageChange()
        }
    }

    private fun LoadLocal() {
        sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
    }

    private fun openDialogforLanguageChange() {
        val list = arrayOf("English", "Español", "Français", "Deutsch", "Italiano", "Polski", "Português", "Русский", "Українська")

        val alertDialog = AlertDialog.Builder(this)

        alertDialog.setTitle("Select Language")

        alertDialog.setSingleChoiceItems(list, -1, DialogInterface.OnClickListener { dialog, i ->

            if (i == 0) {
                setLocal("en")
                recreate()
            } else if (i == 1) {
                setLocal("es")
                recreate()
            } else if (i == 2) {
                setLocal("fr")
                recreate()
            } else if (i == 3) {
            setLocal("de")
            recreate()
        } else if (i == 4) {
            setLocal("it")
            recreate()
        } else if (i == 5) {
            setLocal("pl")
            recreate()
        } else if (i == 6) {
                setLocal("pt")
                recreate()
            } else if (i == 7) {
                setLocal("ru")
                recreate()
            } else if (i == 8) {
                setLocal("uk")
                recreate()
            }
            dialog.dismiss()
        })

        alertDialog.setNeutralButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val mDialog = alertDialog.create()

        mDialog.show()
    }

    private fun setLocal(language: String) {
        val local = Locale(language)
        Locale.setDefault(local)
        val config = resources.configuration
        config.setLocale(local)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("selected_language", language)
        editor.apply()
    }

    private fun areFieldsNotEmpty(): Boolean {
        return (loginField.text.isNotEmpty() &&
                passwordField.text.isNotEmpty())
    }
}

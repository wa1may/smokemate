package com.example.smokemate

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

object LanguageManager {

    private const val LANGUAGE_SETTINGS = "language_settings"

    fun getLanguageSettingsKey(): String {
        return LANGUAGE_SETTINGS
    }


    fun loadLocal(context: Context, languageFlag: ImageView, languageSelect: TextView) {
        val sharedPreferences = context.getSharedPreferences(LANGUAGE_SETTINGS, Context.MODE_PRIVATE)
        val selectedFlag = sharedPreferences.getInt("selected_flag", R.drawable.language_russian)
        val selectedLanguage = sharedPreferences.getString("selected_language", "ru")
        languageFlag.setImageResource(selectedFlag)
        languageSelect.text = getLanguageName(selectedLanguage!!)
        setLocale(context, selectedLanguage)
    }

    fun openDialogForLanguageChange(
        context: Context,
        languageFlag: ImageView,
        languageSelect: TextView,
        editor: SharedPreferences.Editor,
        sharedPreferences: SharedPreferences,
        activity: AppCompatActivity
    ) {
        val list = arrayOf("English", "Español", "Français", "Deutsch", "Italiano", "Polski", "Português", "Русский", "Українська")

        val alertDialog = AlertDialog.Builder(context)

        alertDialog.setTitle("Select Language")

        alertDialog.setSingleChoiceItems(list, -1, DialogInterface.OnClickListener { dialog, i ->

            if (i in 0..8) {
                val languageCodes = arrayOf("en", "es", "fr", "de", "it", "pl", "pt", "ru", "uk")
                val flagResourceIds = arrayOf(
                    R.drawable.language_english,
                    R.drawable.language_spanish,
                    R.drawable.language_french,
                    R.drawable.language_german,
                    R.drawable.language_italian,
                    R.drawable.language_polish,
                    R.drawable.language_portuguese,
                    R.drawable.language_russian,
                    R.drawable.language_ukrainian
                )

                setLocal(context, languageCodes[i], flagResourceIds[i], languageFlag, editor, sharedPreferences, activity)

                // Set a flag in SharedPreferences to indicate that the activity has been recreated
                editor.putBoolean("is_activity_recreated", true)
                editor.apply()
            }

            dialog.dismiss()
        })

        alertDialog.setNeutralButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val mDialog = alertDialog.create()

        languageSelect.text = getLanguageName(sharedPreferences.getString("selected_language", "en")!!)
        mDialog.show()
    }

    fun setLocal(
        context: Context,
        language: String,
        flagResourceId: Int,
        languageFlag: ImageView,
        editor: SharedPreferences.Editor,
        sharedPreferences: SharedPreferences,
        activity: AppCompatActivity
    ) {
        setLocale(context, language)

        editor.putString("selected_language", language)
        editor.putInt("selected_flag", flagResourceId)
        editor.apply()

        languageFlag.setImageResource(flagResourceId)
        activity.recreate()
    }

    private fun setLocale(context: Context, language: String) {
        val local = Locale(language)
        Locale.setDefault(local)
        val config = context.resources.configuration
        config.setLocale(local)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    private fun getLanguageName(language: String): String {
        return when (language) {
            "en" -> "English"
            "es" -> "Español"
            "fr" -> "Français"
            "de" -> "Deutsch"
            "it" -> "Italiano"
            "pl" -> "Polski"
            "pt" -> "Português"
            "ru" -> "Русский"
            "uk" -> "Українська"
            else -> "Unknown"
        }
    }
    private var isActivityRestarted: Boolean = false
    fun restartActivity(activity: AppCompatActivity) {
        val sharedPreferences = activity.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        if (!isActivityRestarted) {
            isActivityRestarted = true

            val editor = sharedPreferences.edit()
            editor.putBoolean("isActivityRestarted", true)
            editor.apply()

            activity.recreate()
        } else {
            isActivityRestarted = false
            val editor = sharedPreferences.edit()
            editor.putBoolean("isActivityRestarted", false)
            editor.apply()
        }
    }
}

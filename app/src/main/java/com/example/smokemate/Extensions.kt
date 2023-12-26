package com.example.smokemate

import android.view.View
import android.widget.EditText

import kotlin.random.Random

fun View.areFieldsNotEmpty(): Boolean {
    return when (this) {
        is EditText -> this.text.isNotEmpty()
        else -> false
    }
}
fun setRedBorderForEmptyFields(vararg editTexts: EditText) {
    for (editText in editTexts) {
        if (editText.areFieldsNotEmpty()) {
            editText.setBackgroundResource(R.drawable.textline)
        } else {
            editText.setBackgroundResource(R.drawable.textline_error)
        }
    }
}

fun generateRandomNumber(): String {
    return (100000 + Random.nextInt(900000)).toString()
}

fun checkEnteredNumber(enteredNumber: String, randomNumber: String): Boolean {
    return enteredNumber.length == 6 && enteredNumber.matches(Regex("\\d+")) && enteredNumber == randomNumber
}
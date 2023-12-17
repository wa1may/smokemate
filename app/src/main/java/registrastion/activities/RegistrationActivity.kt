package registrastion.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.example.smokemate.MainActivity
import com.example.smokemate.R
import java.text.SimpleDateFormat
import java.util.*

class RegistrationActivity: AppCompatActivity() {

    private lateinit var registrationLoginField: EditText
    private lateinit var registrationPasswordField: EditText
    private lateinit var registrationConfirmPasswordField: EditText
    private lateinit var yearField: EditText
    private lateinit var monthField: EditText
    private lateinit var dateField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

val registrationNextButton: android.widget.Button = findViewById(R.id.registrationNextButton)
        val registrationToLoginButton: android.widget.Button = findViewById(R.id.registrationToLoginButton)



        registrationLoginField = findViewById(R.id.registrationLoginField)
        registrationPasswordField = findViewById(R.id.registrationPasswordField)
        registrationConfirmPasswordField = findViewById(R.id.registrationConfirmPasswordField)
        dateField = findViewById(R.id.dateField)
        monthField = findViewById(R.id.monthField)
        yearField = findViewById(R.id.yearField)

        registrationNextButton.setOnClickListener {
            if (areFieldsNotEmpty()) {
                setRedBorderForEmptyFields()
                if(arePasswordsMatching()) {
                    if (isOver18()) {
                        RegistrationDataHolder.userData = UserData(
                            registrationLoginField.text.toString().trim(),
                            registrationPasswordField.text.toString().trim(),
                            dateField.text.toString().trim(),
                            monthField.text.toString().trim(),
                            yearField.text.toString().trim()
                        )
                        val intent = Intent(this, RegistrationSecondActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "You must be over 18", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()

                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                setRedBorderForEmptyFields()
            }
        }

        registrationToLoginButton.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


private fun areFieldsNotEmpty(): Boolean {
        return (registrationLoginField.text.isNotEmpty() &&
                registrationPasswordField.text.isNotEmpty() &&
                dateField.text.isNotEmpty() &&
                monthField.text.isNotEmpty() &&
                yearField.text.isNotEmpty())
    }

    private fun arePasswordsMatching(): Boolean {
        return registrationPasswordField.text.toString().trim() == registrationConfirmPasswordField.text.toString().trim()
    }

    private fun isOver18(): Boolean {
        val currentDate = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        val day = dateField.text.toString().toInt()
        val month = monthField.text.toString().toInt() - 1
        val year = yearField.text.toString().toInt()

        val birthDate = Calendar.getInstance()
        birthDate.set(year, month, day)

        var age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)
        if (currentDate.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        return age >= 18
    }

    private fun setRedBorderForEmptyFields() {
        if (registrationLoginField.text.isEmpty()) {
            registrationLoginField.setBackgroundResource(R.drawable.textline_error)
        } else {
            registrationLoginField.setBackgroundResource(R.drawable.textline)
        }

        if (registrationPasswordField.text.isEmpty()) {
            registrationPasswordField.setBackgroundResource(R.drawable.textline_error)
        } else {
            registrationPasswordField.setBackgroundResource(R.drawable.textline)
        }

        if (registrationConfirmPasswordField.text.isEmpty()) {
            registrationConfirmPasswordField.setBackgroundResource(R.drawable.textline_error)
        } else {
            registrationConfirmPasswordField.setBackgroundResource(R.drawable.textline)
        }

        if (dateField.text.isEmpty()) {
            dateField.setBackgroundResource(R.drawable.textline_error)
        } else {
            dateField.setBackgroundResource(R.drawable.textline)
        }

        if (monthField.text.isEmpty()) {
            monthField.setBackgroundResource(R.drawable.textline_error)
        } else {
            monthField.setBackgroundResource(R.drawable.textline)
        }

        if (yearField.text.isEmpty()) {
            yearField.setBackgroundResource(R.drawable.textline_error)
        } else {
            yearField.setBackgroundResource(R.drawable.textline)
        }
    }
}
package registrastion.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.example.smokemate.MainActivity
import com.example.smokemate.R
import java.util.*
import com.example.smokemate.areFieldsNotEmpty
import com.example.smokemate.setRedBorderForEmptyFields

class RegistrationActivity: AppCompatActivity() {

    private lateinit var registrationLoginField: EditText
    private lateinit var registrationPasswordField: EditText
    private lateinit var registrationConfirmPasswordField: EditText
    private lateinit var yearField: EditText
    private lateinit var monthField: EditText
    private lateinit var dateField: EditText
    private lateinit var registrationNextButton: Button
    private lateinit var registrationToLoginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        registrationNextButton = findViewById(R.id.registrationNextButton)
        registrationToLoginButton = findViewById(R.id.registrationToLoginButton)

        registrationLoginField = findViewById(R.id.registrationLoginField)
        registrationPasswordField = findViewById(R.id.registrationPasswordField)
        registrationConfirmPasswordField = findViewById(R.id.registrationConfirmPasswordField)
        dateField = findViewById(R.id.dateField)
        monthField = findViewById(R.id.monthField)
        yearField = findViewById(R.id.yearField)

        registrationNextButton.setOnClickListener {
            if (registrationLoginField.areFieldsNotEmpty() && registrationPasswordField.areFieldsNotEmpty() && registrationConfirmPasswordField.areFieldsNotEmpty() && dateField.areFieldsNotEmpty() && monthField.areFieldsNotEmpty() && yearField.areFieldsNotEmpty() && arePasswordsMatching() && isOver18()) {
                setRedBorderForEmptyFields(registrationLoginField, registrationPasswordField, registrationConfirmPasswordField, dateField, monthField, yearField)
                /*Фунция для сохранении информации во временное хранилище*/
                val intent = Intent(this, RegistrationSecondActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                setRedBorderForEmptyFields(registrationLoginField, registrationPasswordField, registrationConfirmPasswordField, dateField, monthField, yearField)
            }
        }

        registrationToLoginButton.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun arePasswordsMatching(): Boolean {
        return registrationPasswordField.text.toString().trim() == registrationConfirmPasswordField.text.toString().trim()
    }

    private fun isOver18(): Boolean {
        val currentDate = Calendar.getInstance()

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
}
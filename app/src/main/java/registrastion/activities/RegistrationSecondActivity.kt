package registrastion.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smokemate.R

class RegistrationSecondActivity : AppCompatActivity() {

    private lateinit var registrationNameField: EditText
    private lateinit var registrationSurnameField: EditText
    private lateinit var registrationPhoneNumberField: EditText
    private lateinit var registrationCountryField: EditText
    private lateinit var registrationCityField: EditText
    private lateinit var registrationCigaretteField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_second)

        val registrationNextButton2: Button = findViewById(R.id.registrationNextButton2)

        registrationNameField = findViewById(R.id.registrationNameField)
        registrationSurnameField = findViewById(R.id.registrationSurnameField)
        registrationPhoneNumberField = findViewById(R.id.registrationPhoneNumberField)
        registrationCountryField = findViewById(R.id.registrationCountryField)
        registrationCityField = findViewById(R.id.registrationCityField)
        registrationCigaretteField = findViewById(R.id.registrationCigaretteField)

        registrationNextButton2.setOnClickListener {
            if (areFieldsNotEmpty()) {
                RegistrationDataHolder.userDetail = UserDetail(
                    registrationNameField.text.toString().trim(),
                    registrationSurnameField.text.toString().trim(),
                    registrationPhoneNumberField.text.toString().trim(),
                    registrationCountryField.text.toString().trim(),
                    registrationCityField.text.toString().trim(),
                    registrationCigaretteField.text.toString().trim()
                )
                val intent = Intent(this, RegistrationConfirmActivity::class.java)
                startActivity(intent)
                setRedBorderForEmptyFields()
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                setRedBorderForEmptyFields()
            }
        }
    }

    private fun areFieldsNotEmpty(): Boolean {
        return (registrationNameField.text.isNotEmpty() &&
                registrationSurnameField.text.isNotEmpty() &&
                registrationPhoneNumberField.text.isNotEmpty() &&
                registrationCountryField.text.isNotEmpty() &&
                registrationCityField.text.isNotEmpty() &&
                registrationCigaretteField.text.isNotEmpty())
    }

    private fun setRedBorderForEmptyFields() {
        if (registrationNameField.text.isEmpty()) {
            registrationNameField.setBackgroundResource(R.drawable.textline_error)
        } else {
            registrationNameField.setBackgroundResource(R.drawable.textline)
        }

        if (registrationSurnameField.text.isEmpty()) {
            registrationSurnameField.setBackgroundResource(R.drawable.textline_error)
        } else {
            registrationSurnameField.setBackgroundResource(R.drawable.textline)
        }

        if (registrationPhoneNumberField.text.isEmpty()) {
            registrationPhoneNumberField.setBackgroundResource(R.drawable.textline_error)
        } else {
            registrationPhoneNumberField.setBackgroundResource(R.drawable.textline)
        }

        if (registrationCountryField.text.isEmpty()) {
            registrationCountryField.setBackgroundResource(R.drawable.textline_error)
        } else {
            registrationCountryField.setBackgroundResource(R.drawable.textline)
        }

        if (registrationCityField.text.isEmpty()) {
            registrationCityField.setBackgroundResource(R.drawable.textline_error)
        } else {
            registrationCityField.setBackgroundResource(R.drawable.textline)
        }

        if (registrationCigaretteField.text.isEmpty()) {
            registrationCigaretteField.setBackgroundResource(R.drawable.textline_error)
        } else {
            registrationCigaretteField.setBackgroundResource(R.drawable.textline)
        }
    }
}
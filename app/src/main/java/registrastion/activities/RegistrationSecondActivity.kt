package registrastion.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smokemate.R
import com.example.smokemate.areFieldsNotEmpty
import com.example.smokemate.setRedBorderForEmptyFields

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
            if (registrationNameField.areFieldsNotEmpty() && registrationSurnameField.areFieldsNotEmpty() && registrationPhoneNumberField.areFieldsNotEmpty() && registrationCountryField.areFieldsNotEmpty() && registrationCityField.areFieldsNotEmpty() && registrationCigaretteField.areFieldsNotEmpty()) {
/*Код для связи с бэкэндом*/
                val intent = Intent(this, RegistrationConfirmActivity::class.java)
                startActivity(intent)
                setRedBorderForEmptyFields(registrationNameField, registrationSurnameField, registrationPhoneNumberField, registrationCountryField, registrationCityField, registrationCigaretteField)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                setRedBorderForEmptyFields(registrationNameField, registrationSurnameField, registrationPhoneNumberField, registrationCountryField, registrationCityField, registrationCigaretteField)
            }
        }
    }
}
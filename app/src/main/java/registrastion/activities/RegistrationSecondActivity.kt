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
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.view.View
import android.widget.TextView

class RegistrationSecondActivity : AppCompatActivity() {

    private lateinit var registrationNameField: EditText
    private lateinit var registrationSurnameField: EditText
    private lateinit var registrationPhoneNumberField: EditText
    private lateinit var registrationCountryField: EditText
    private lateinit var registrationCityField: EditText
    private lateinit var registrationCigaretteField: EditText
    private lateinit var genderSpinner: Spinner
    private lateinit var errorMessage4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_second)

        val registrationNextButton2: Button = findViewById(R.id.registrationNextButton2)
        val previousButton: Button = findViewById(R.id.previousButton)

        registrationNameField = findViewById(R.id.registrationNameField)
        registrationSurnameField = findViewById(R.id.registrationSurnameField)
        registrationPhoneNumberField = findViewById(R.id.registrationPhoneNumberField)
        registrationCountryField = findViewById(R.id.registrationCountryField)
        registrationCityField = findViewById(R.id.registrationCityField)
        registrationCigaretteField = findViewById(R.id.registrationCigaretteField)
        genderSpinner = findViewById(R.id.genderSpinner)
        errorMessage4 = findViewById(R.id.errorMessage4)

        ArrayAdapter.createFromResource(
            this,
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            genderSpinner.adapter = adapter
        }

        registrationNextButton2.setOnClickListener {
            if (registrationNameField.areFieldsNotEmpty() && registrationSurnameField.areFieldsNotEmpty() && registrationPhoneNumberField.areFieldsNotEmpty() && registrationCountryField.areFieldsNotEmpty() && registrationCityField.areFieldsNotEmpty() && registrationCigaretteField.areFieldsNotEmpty()) {
/*Код для связи с бэкэндом*/
                val intent = Intent(this, RegistrationConfirmActivity::class.java)
                startActivity(intent)
                setRedBorderForEmptyFields(registrationNameField, registrationSurnameField, registrationPhoneNumberField, registrationCountryField, registrationCityField, registrationCigaretteField)
                errorMessage4.text = " "
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                setRedBorderForEmptyFields(registrationNameField, registrationSurnameField, registrationPhoneNumberField, registrationCountryField, registrationCityField, registrationCigaretteField)
                errorMessage4.text = "Будь ласка, заповніть всі поля"
            }
        }

        previousButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        genderSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                val selectedGender = parentView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        })
    }
}
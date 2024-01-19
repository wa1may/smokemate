package registrastion.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.smokemate.MainActivity
import com.example.smokemate.R
import java.util.*
import com.example.smokemate.areFieldsNotEmpty
import com.example.smokemate.setRedBorderForEmptyFields
import android.app.Dialog
import android.widget.NumberPicker
import android.graphics.Color

class RegistrationActivity: AppCompatActivity() {

    private lateinit var registrationLoginField: EditText
    private lateinit var registrationPasswordField: EditText
    private lateinit var registrationConfirmPasswordField: EditText
    private lateinit var regNextButton: Button
    private lateinit var regToLoginButton: Button
    private lateinit var dateOfBirthSelector: TextView
    private lateinit var errorMessage2: TextView
    private lateinit var errorMessage3: TextView
    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        regNextButton = findViewById(R.id.regNextButton)
        regToLoginButton = findViewById(R.id.regToLoginButton)
        dateOfBirthSelector = findViewById(R.id.dateOfBirthSelector)
        registrationLoginField = findViewById(R.id.registrationLoginField)
        registrationPasswordField = findViewById(R.id.registrationPasswordField)
        registrationConfirmPasswordField = findViewById(R.id.registrationConfirmPasswordField)
        errorMessage2 = findViewById(R.id.errorMessage2)
        errorMessage3 = findViewById(R.id.errorMessage3)

        regNextButton.setOnClickListener {
            if (registrationLoginField.areFieldsNotEmpty() && registrationPasswordField.areFieldsNotEmpty() && registrationConfirmPasswordField.areFieldsNotEmpty() &&  arePasswordsMatching()) {
                setRedBorderForEmptyFields(registrationLoginField, registrationPasswordField, registrationConfirmPasswordField)
                errorMessage2.text = " "
                if(isOver18())
                {
                    /*Фунция для сохранении информации во временное хранилище*/
                    val intent = Intent(this, RegistrationSecondActivity::class.java)
                    startActivity(intent)
                    errorMessage3.text = " "
                } else {
                    errorMessage3.text = "Будь ласка, вкажіть правильний вік"
                }

            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                setRedBorderForEmptyFields(registrationLoginField, registrationPasswordField, registrationConfirmPasswordField)
                errorMessage2.text = "Будь ласка, заповніть всі поля"
           }
        }

        regToLoginButton.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        dateOfBirthSelector.setOnClickListener {
            showDatePickerDialog()
        }

    }


    private fun showDatePickerDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_date_picker)
        dialog.setTitle("Выберите дату")

        val yearPicker = dialog.findViewById<NumberPicker>(R.id.yearPicker)
        val monthPicker = dialog.findViewById<NumberPicker>(R.id.monthPicker)
        val dayPicker = dialog.findViewById<NumberPicker>(R.id.dayPicker)
        val okButton = dialog.findViewById<Button>(R.id.okButton)
        val cancelButton = dialog.findViewById<Button>(R.id.cancelButton)


        val currentDate = Calendar.getInstance()
        val currentYear = currentDate.get(Calendar.YEAR)
        val currentMonth = currentDate.get(Calendar.MONTH)
        val currentDay = currentDate.get(Calendar.DAY_OF_MONTH)
        yearPicker.minValue = currentYear - 100
        yearPicker.maxValue = currentYear + 100
        monthPicker.minValue = 1
        monthPicker.maxValue = 12
        dayPicker.minValue = 1
        dayPicker.maxValue = currentDate.getActualMaximum(Calendar.DAY_OF_MONTH)

        yearPicker.value = currentYear
        monthPicker.value = currentMonth + 1
        dayPicker.value = currentDay



        okButton.setOnClickListener {
            selectedDate = "${yearPicker.value}-${monthPicker.value}-${dayPicker.value}"
            dateOfBirthSelector.text = selectedDate
            dateOfBirthSelector.setTextColor(Color.BLACK)
            dialog.dismiss()
        }

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun arePasswordsMatching(): Boolean {
        return registrationPasswordField.text.toString().trim() == registrationConfirmPasswordField.text.toString().trim()
    }

    private fun isOver18(): Boolean {
        val currentDate = Calendar.getInstance()

        val selectedDateParts = selectedDate.split("-")
        val day = selectedDateParts[2].toInt()
        val month = selectedDateParts[1].toInt() - 1
        val year = selectedDateParts[0].toInt()

        val birthDate = Calendar.getInstance()
        birthDate.set(year, month, day)

        var age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)
        if (currentDate.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        return age >= 18
    }
}
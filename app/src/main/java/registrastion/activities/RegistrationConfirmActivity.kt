package registrastion.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.widget.EditText
import com.example.smokemate.R
import com.example.smokemate.generateRandomNumber
import com.example.smokemate.checkEnteredNumber
import com.example.smokemate.areFieldsNotEmpty
import com.example.smokemate.setRedBorderForEmptyFields

class RegistrationConfirmActivity: AppCompatActivity() {

    private lateinit var randomNumber: String
    private lateinit var confirmationCodeInput: EditText
    private lateinit var regButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_confirm)

        regButton = findViewById(R.id.confirmButton)
        confirmationCodeInput = findViewById(R.id.confirmationCodeInput)

        randomNumber = generateRandomNumber()
        Toast.makeText(this, "Generated Number: $randomNumber", Toast.LENGTH_SHORT).show()

        regButton.setOnClickListener {
            if(confirmationCodeInput.areFieldsNotEmpty())
            {
                setRedBorderForEmptyFields(confirmationCodeInput)
                val enteredNumber = confirmationCodeInput.text.toString().trim()

                if(checkEnteredNumber(enteredNumber, randomNumber))
                {
                    /*Фунцкия записи текста из строк в базу данных*/
                    Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Incorrect! Try again.", Toast.LENGTH_SHORT).show()
                }
            } else {
                setRedBorderForEmptyFields(confirmationCodeInput)
            }
        }
    }
}


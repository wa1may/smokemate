package registrastion.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.widget.EditText
import android.widget.TextView
import com.example.smokemate.R
import com.example.smokemate.generateRandomNumber
import com.example.smokemate.checkEnteredNumber
import com.example.smokemate.areFieldsNotEmpty
import com.example.smokemate.setRedBorderForEmptyFields

class RegistrationConfirmActivity: AppCompatActivity() {

    private lateinit var randomNumber: String
    private lateinit var confirmationCodeInput: EditText
    private lateinit var regButton: Button
    private lateinit var errorMessage5: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_confirm)

        regButton = findViewById(R.id.confirmButton)
        confirmationCodeInput = findViewById(R.id.confirmationCodeInput)
        errorMessage5 = findViewById(R.id.errorMessage5)

        val previousButton2: Button = findViewById(R.id.previousButton2)
        val resendCodeButton: Button = findViewById(R.id.resendCodeButton)


        randomNumber = generateRandomNumber()
        Toast.makeText(this, "Generated Number: $randomNumber", Toast.LENGTH_SHORT).show()

        regButton.setOnClickListener {
            if(confirmationCodeInput.areFieldsNotEmpty())
            {
                setRedBorderForEmptyFields(confirmationCodeInput)
                val enteredNumber = confirmationCodeInput.text.toString().trim()
                errorMessage5.text = " "
                if(checkEnteredNumber(enteredNumber, randomNumber))
                {
                    /*Фунцкия записи текста из строк в базу данных*/
                    errorMessage5.text = " "
                } else {
                    errorMessage5.text = "Введений код невірний. Будь ласка, спробуйте знову"
                }
            } else {
                setRedBorderForEmptyFields(confirmationCodeInput)
                errorMessage5.text = "Будь ласка, заповніть всі поля"
            }
        }

        previousButton2.setOnClickListener {
            val intent = Intent(this, RegistrationSecondActivity::class.java)
            startActivity(intent)
        }

        resendCodeButton.setOnClickListener {
            randomNumber = generateRandomNumber()
            Toast.makeText(this, "Generated Number: $randomNumber", Toast.LENGTH_SHORT).show()
        }
    }
}


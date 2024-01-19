package forgot.password.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smokemate.R
import com.example.smokemate.generateRandomNumber
import com.example.smokemate.checkEnteredNumber
import com.example.smokemate.areFieldsNotEmpty
import com.example.smokemate.setRedBorderForEmptyFields
import registrastion.activities.RegistrationSecondActivity

class ForgotPasswordConfirmActivity: AppCompatActivity() {

    private lateinit var randomNumber: String
    private lateinit var forgotPasswordCodeInput: EditText
    private lateinit var forgotPasswordButton: Button
    private lateinit var errorMessage7: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_confirm)

        forgotPasswordButton = findViewById(R.id.forgotPasswordButton)
        forgotPasswordCodeInput = findViewById(R.id.forgotPasswordCodeInput)
        errorMessage7 = findViewById(R.id.errorMessage7)

        val previousButton4: Button = findViewById(R.id.previousButton4)
        val resendCodeButton2: Button = findViewById(R.id.resendCodeButton2)

        randomNumber = generateRandomNumber()
        Toast.makeText(this, "Generated Number: $randomNumber", Toast.LENGTH_SHORT).show()

        forgotPasswordButton.setOnClickListener {
            if(forgotPasswordCodeInput.areFieldsNotEmpty())
            {
                setRedBorderForEmptyFields(forgotPasswordCodeInput)
                val enteredNumber = forgotPasswordCodeInput.text.toString().trim()
                errorMessage7.text = " "
                if(checkEnteredNumber(enteredNumber, randomNumber))
                {
                    /*Фунцкия записи текста из строк в базу данных*/
                    errorMessage7.text = " "
                } else {
                    errorMessage7.text = "Введений код невірний. Будь ласка, спробуйте знову"
                }
            } else {
                setRedBorderForEmptyFields(forgotPasswordCodeInput)
                errorMessage7.text = "Будь ласка, заповніть всі поля"
            }
        }

        previousButton4.setOnClickListener {
            val intent = Intent(this, ForgotPasswordPhoneActivity::class.java)
            startActivity(intent)
        }

        resendCodeButton2.setOnClickListener {
            randomNumber = generateRandomNumber()
            Toast.makeText(this, "Generated Number: $randomNumber", Toast.LENGTH_SHORT).show()
        }
    }
}
package forgot.password.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smokemate.R
import kotlin.random.Random

class ForgotPasswordConfirmActivity: AppCompatActivity() {

    private lateinit var randomNumber: String
    private lateinit var forgotPasswordCodeInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_confirm)

        val forgotPasswordButton: Button = findViewById(R.id.forgotPasswordButton)
        forgotPasswordCodeInput = findViewById(R.id.forgotPasswordCodeInput)

        randomNumber = generateRandomNumber()
        Toast.makeText(this, "Generated Number: $randomNumber", Toast.LENGTH_SHORT).show()

        forgotPasswordButton.setOnClickListener {
            val enteredNumber = forgotPasswordCodeInput.text.toString().trim()

            if(checkEnteredNumber(enteredNumber))
            {
                val intent = Intent(this, ForgotPasswordNewActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Incorrect! Try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateRandomNumber(): String {
        return (100000 + Random.nextInt(900000)).toString()
    }

    private fun checkEnteredNumber(enteredNumber: String): Boolean {
        return enteredNumber.length == 6 && enteredNumber.matches(Regex("\\d+")) && enteredNumber == randomNumber
    }


}
package forgot.password.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smokemate.R
import com.example.smokemate.generateRandomNumber
import com.example.smokemate.checkEnteredNumber
import com.example.smokemate.areFieldsNotEmpty
import com.example.smokemate.setRedBorderForEmptyFields

class ForgotPasswordConfirmActivity: AppCompatActivity() {

    private lateinit var randomNumber: String
    private lateinit var forgotPasswordCodeInput: EditText
    private lateinit var forgotPasswordButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_confirm)

        forgotPasswordButton = findViewById(R.id.forgotPasswordButton)
        forgotPasswordCodeInput = findViewById(R.id.forgotPasswordCodeInput)

        randomNumber = generateRandomNumber()
        Toast.makeText(this, "Generated Number: $randomNumber", Toast.LENGTH_SHORT).show()

        forgotPasswordButton.setOnClickListener {
           if(forgotPasswordCodeInput.areFieldsNotEmpty())
           {
               setRedBorderForEmptyFields(forgotPasswordCodeInput)
               val enteredNumber = forgotPasswordCodeInput.text.toString().trim()

               if(checkEnteredNumber(enteredNumber, randomNumber))
               {
                   val intent = Intent(this, ForgotPasswordNewActivity::class.java)
                   startActivity(intent)
               } else {
                   Toast.makeText(this, "Incorrect! Try again.", Toast.LENGTH_SHORT).show()
               }
           }else {
               setRedBorderForEmptyFields(forgotPasswordCodeInput)
           }
        }
    }
}
package forgot.password.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smokemate.R
import com.example.smokemate.areFieldsNotEmpty
import com.example.smokemate.setRedBorderForEmptyFields
import com.example.smokemate.MainActivity

class ForgotPasswordPhoneActivity: AppCompatActivity()  {

    private lateinit var forgotPasswordPhoneInput: EditText
    private lateinit var forgotPasswordPhoneButton: Button
    private lateinit var errorMessage6: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_phone)

        forgotPasswordPhoneInput = findViewById(R.id.forgotPasswordPhoneInput)
        forgotPasswordPhoneButton = findViewById(R.id.forgotPasswordPhoneButton)
        errorMessage6 = findViewById(R.id.errorMessage6)

        val previousButton3: Button = findViewById(R.id.previousButton3)

        forgotPasswordPhoneButton.setOnClickListener {
            if(forgotPasswordPhoneInput.areFieldsNotEmpty()) {
                /*Функция для работы с базой данных*/
                setRedBorderForEmptyFields(forgotPasswordPhoneInput)
                val intent = Intent(this, ForgotPasswordConfirmActivity::class.java)
                startActivity(intent)
                errorMessage6.text = " "
            } else {
                errorMessage6.text = getString(R.string.emptyFields)
                setRedBorderForEmptyFields(forgotPasswordPhoneInput)
            }
        }

        previousButton3.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
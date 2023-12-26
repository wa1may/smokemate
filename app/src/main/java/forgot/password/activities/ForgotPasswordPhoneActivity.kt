package forgot.password.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smokemate.R
import com.example.smokemate.areFieldsNotEmpty
import com.example.smokemate.setRedBorderForEmptyFields

class ForgotPasswordPhoneActivity: AppCompatActivity()  {

    private lateinit var forgotPasswordPhoneInput: EditText
    private lateinit var forgotPasswordPhoneButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_phone)

        forgotPasswordPhoneInput = findViewById(R.id.forgotPasswordPhoneInput)
        forgotPasswordPhoneButton = findViewById(R.id.forgotPasswordPhoneButton)

        forgotPasswordPhoneButton.setOnClickListener {
            if(forgotPasswordPhoneInput.areFieldsNotEmpty()) {
                /*Функция для работы с базой данных*/
                setRedBorderForEmptyFields(forgotPasswordPhoneInput)
                val intent = Intent(this, ForgotPasswordConfirmActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                setRedBorderForEmptyFields(forgotPasswordPhoneInput)
            }
        }
    }
}
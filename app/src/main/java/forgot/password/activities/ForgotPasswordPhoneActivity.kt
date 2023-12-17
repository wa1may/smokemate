package forgot.password.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smokemate.DataBase
import com.example.smokemate.R

class ForgotPasswordPhoneActivity: AppCompatActivity()  {

    private lateinit var forgotPasswordPhoneInput: EditText
    private lateinit var forgotPasswordPhoneButton: Button
    private lateinit var db: DataBase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_phone)

        forgotPasswordPhoneInput = findViewById(R.id.forgotPasswordPhoneInput)
        forgotPasswordPhoneButton = findViewById(R.id.forgotPasswordPhoneButton)
        db = DataBase(this)

        forgotPasswordPhoneButton.setOnClickListener {
            if(areFieldsNotEmpty()) {
                val phoneNumber = forgotPasswordPhoneInput.text.toString().trim()
                if(db.isPhoneNumberExists(phoneNumber)){
                    val intent = Intent(this, ForgotPasswordConfirmActivity::class.java)
                    startActivity(intent)
                }else {
                    Toast.makeText(this, "Wrong phone number", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun areFieldsNotEmpty(): Boolean {
        return (forgotPasswordPhoneInput.text.isNotEmpty())
    }

}
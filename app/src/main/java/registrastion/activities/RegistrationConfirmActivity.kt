package registrastion.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.widget.EditText
import com.example.smokemate.DataBase
import com.example.smokemate.R

import kotlin.random.Random


class RegistrationConfirmActivity: AppCompatActivity() {

    private lateinit var randomNumber: String
    private lateinit var confirmationCodeInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_confirm)

        val regButton: Button = findViewById(R.id.confirmButton)
        confirmationCodeInput = findViewById(R.id.confirmationCodeInput)

        randomNumber = generateRandomNumber()
        Toast.makeText(this, "Generated Number: $randomNumber", Toast.LENGTH_SHORT).show()

        regButton.setOnClickListener {
            val enteredNumber = confirmationCodeInput.text.toString().trim()

            if(checkEnteredNumber(enteredNumber))
            {
                saveDataToDatabase()
            } else {
                Toast.makeText(this, "Incorrect! Try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun saveDataToDatabase() {
        val myDB = DataBase(this)
        val registrationData = RegistrationDataHolder.userData
        val registrationSecondData = RegistrationDataHolder.userDetail
        myDB.addUsers(
            registrationData?.login.orEmpty(),
            registrationData?.password.orEmpty(),
            registrationData?.date.orEmpty(),
            registrationData?.month.orEmpty(),
            registrationData?.year.orEmpty()
        )

        myDB.addUserData(
            registrationSecondData?.name.orEmpty(),
            registrationSecondData?.surname.orEmpty(),
            registrationSecondData?.phoneNumber.orEmpty(),
            registrationSecondData?.country.orEmpty(),
            registrationSecondData?.city.orEmpty(),
            registrationSecondData?.cigarette.orEmpty()
        )
    }

    private fun generateRandomNumber(): String {
        return (100000 + Random.nextInt(900000)).toString()
    }

    private fun checkEnteredNumber(enteredNumber: String): Boolean {
        return enteredNumber.length == 6 && enteredNumber.matches(Regex("\\d+")) && enteredNumber == randomNumber
    }
}
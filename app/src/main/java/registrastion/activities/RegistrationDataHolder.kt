package registrastion.activities

data class UserData(
    val login: String,
    val password: String,
    val date: String,
    val month: String,
    val year: String
)

data class UserDetail(
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val country: String,
    val city: String,
    val cigarette: String
)

object RegistrationDataHolder {
    var userData: UserData? = null
    var userDetail: UserDetail? = null
}
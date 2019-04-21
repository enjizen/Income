package cockatoo.enjizen.income.ui.password.authentication

interface AuthenticationView {
    fun authenticationSuccess()

    fun authenticationFail()

    fun getEditTextPin() : String

    fun displayPinPassword(pin: String)
}
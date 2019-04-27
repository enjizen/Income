package cockatoo.enjizen.income.ui.password.confirm

interface ConfirmPasswordView {

    fun onConfirmPasswordSuccess()

    fun onConfirmPasswordNotMatch()

    fun getEditTextPin() : String

    fun displayPinPassword(pin: String)

    fun getPasswordSet() : String
}
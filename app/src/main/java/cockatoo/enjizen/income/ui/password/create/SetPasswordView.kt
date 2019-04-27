package cockatoo.enjizen.income.ui.password.create

interface SetPasswordView {

    fun getEditTextPin() : String

    fun displayPinPassword(pin: String)

    fun sentConfirmPassword(password: String)

    fun clearPasswordEditText()

    fun displayPinPasswordAll()
}
package cockatoo.enjizen.income.ui.password.authentication

import androidx.appcompat.widget.AppCompatButton
import cockatoo.enjizen.income.ui.service.PasswordService

class AuthenticationPresenter(private val view: AuthenticationView) {

    interface AuthenticationView {
        fun authenticationSuccess()

        fun authenticationFail()

        fun getEditTextPin() : String

        fun displayPinPassword(pin: String)
    }

    fun authentication(passwordInput: String){
        if(passwordInput.length == 6) {

            val isValid = PasswordService.authenticationPassword(passwordInput = passwordInput)

            if (isValid) {
                view.authenticationSuccess()
            } else {
                view.authenticationFail()
            }
        }
    }

    fun setPin(textViewPin: AppCompatButton ){
        val pin = textViewPin.text
        val temp = view.getEditTextPin()
        if(temp.length < 6) {
            view.displayPinPassword("$temp$pin")
        }
    }

    fun deletePin(){
        val temp = view.getEditTextPin()
        if(temp.isNotBlank()) {
            val notRemove = temp.substring(0, temp.length - 1)
            view.displayPinPassword(notRemove)
        }
    }

}
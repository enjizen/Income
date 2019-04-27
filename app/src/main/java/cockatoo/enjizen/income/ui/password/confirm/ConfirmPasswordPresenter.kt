package cockatoo.enjizen.income.ui.password.confirm

import androidx.appcompat.widget.AppCompatButton
import cockatoo.enjizen.income.extension.hashPassword
import cockatoo.enjizen.income.ui.service.PasswordService

class ConfirmPasswordPresenter(private val view: ConfirmPasswordView) {

    interface ConfirmPasswordView {

        fun onConfirmPasswordSuccess()

        fun onConfirmPasswordNotMatch()

        fun getEditTextPin() : String

        fun displayPinPassword(pin: String)

        fun getPasswordSet() : String
    }

    fun confirmCheckPassword(confirmPassword: String){

        if(confirmPassword.length == 6) {
            if (view.getPasswordSet() == confirmPassword) {

                val password = confirmPassword.hashPassword()

                PasswordService.savePassword(password = password
                    , onSuccess = { view.onConfirmPasswordSuccess() })


            } else {
                view.onConfirmPasswordNotMatch()
            }
        }

    }

    fun setPin(textViewPin: AppCompatButton){
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
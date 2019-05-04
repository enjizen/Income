package cockatoo.enjizen.income.ui.password.authentication

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.ui.service.PasswordService

class AuthenticationPresenter(private val view: AuthenticationView) {

    interface AuthenticationView {
        fun authenticationSuccess()

        fun authenticationFail()

        fun getEditTextPin() : String

        fun displayPinPassword(pin: String)

    }

    fun inputPinAndVerifyPin(v: View){


        when(v.id){
            R.id.key1, R.id.key2, R.id.key3, R.id.key4, R.id.key5, R.id.key6, R.id.key7, R.id.key8, R.id.key9 , R.id.key0 -> {
                val pin = v as AppCompatButton
                val temp = view.getEditTextPin()
                if(temp.length < 6) {
                    view.displayPinPassword("$temp${pin.text}")
                    if (view.getEditTextPin().length == 6)
                        authentication(view.getEditTextPin())
                }
            }
            R.id.keyDel -> {
                val temp = view.getEditTextPin()
                if(temp.isNotBlank()) {
                    val notRemove = temp.substring(0, temp.length - 1)
                    view.displayPinPassword(notRemove)
                }
            }
        }
    }

    private fun authentication(passwordInput: String){
        val isValid = PasswordService.authenticationPassword(passwordInput = passwordInput)
        if (isValid) {
            view.authenticationSuccess()
        } else {
            view.authenticationFail()
        }
    }

}
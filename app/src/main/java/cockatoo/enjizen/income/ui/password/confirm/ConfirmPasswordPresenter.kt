package cockatoo.enjizen.income.ui.password.confirm

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import cockatoo.enjizen.income.R
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

    private fun confirmCheckPassword(confirmPassword: String){

            if (view.getPasswordSet() == confirmPassword) {
                PasswordService.savePassword(password = confirmPassword.hashPassword(), onSuccess = { view.onConfirmPasswordSuccess() })
            } else {
                view.onConfirmPasswordNotMatch()
            }
    }

    fun inputPinAndVerifyPin(v: View){
        when(v.id){
            R.id.key1, R.id.key2, R.id.key3, R.id.key4, R.id.key5, R.id.key6, R.id.key7, R.id.key8, R.id.key9 , R.id.key0 -> {
                val pin = v as AppCompatButton
                val temp = view.getEditTextPin()
                if(temp.length < 6) {
                    view.displayPinPassword("$temp${pin.text}")
                    if (view.getEditTextPin().length == 6)
                        confirmCheckPassword(view.getEditTextPin())
                }
            }
            R.id.keyDel -> {
                val temp = view.getEditTextPin()
                if(temp.isNotBlank()) {
                    view.displayPinPassword( temp.substring(0, temp.length - 1))
                }
            }
        }
    }


}
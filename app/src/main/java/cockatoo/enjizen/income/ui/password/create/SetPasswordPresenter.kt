package cockatoo.enjizen.income.ui.password.create

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import cockatoo.enjizen.income.R

class SetPasswordPresenter (private val view: SetPasswordView) {

    interface SetPasswordView {

        fun getEditTextPin() : String

        fun displayPinPassword(pin: String)

        fun sentConfirmPassword(password: String)

        fun clearPasswordEditText()

        fun displayPinPasswordAll()
    }

    private fun passwordSet(){
       val password = view.getEditTextPin()
            view.sentConfirmPassword(password)
            view.clearPasswordEditText()
            view.displayPinPasswordAll()
    }

    fun inputPinAndVerifyPin(v: View){

        when(v.id){
            R.id.key1, R.id.key2, R.id.key3, R.id.key4, R.id.key5, R.id.key6, R.id.key7, R.id.key8, R.id.key9 , R.id.key0 -> {
                val pin = v as AppCompatButton
                val temp = view.getEditTextPin()
                if(temp.length < 6) {
                    view.displayPinPassword("$temp${pin.text}")
                    if (view.getEditTextPin().length == 6)
                        passwordSet()
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

}
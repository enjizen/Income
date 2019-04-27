package cockatoo.enjizen.income.ui.password.create

import androidx.appcompat.widget.AppCompatButton

class SetPasswordPresenter (private val view: SetPasswordView) {

    interface SetPasswordView {

        fun getEditTextPin() : String

        fun displayPinPassword(pin: String)

        fun sentConfirmPassword(password: String)

        fun clearPasswordEditText()

        fun displayPinPasswordAll()
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

    fun passwordSet(){
       val password = view.getEditTextPin()
        if(password.length == 6){
            view.sentConfirmPassword(password)
            view.clearPasswordEditText()
            view.displayPinPasswordAll()
        }
    }

}
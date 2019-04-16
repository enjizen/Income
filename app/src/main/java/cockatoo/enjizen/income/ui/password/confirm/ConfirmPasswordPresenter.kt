package cockatoo.enjizen.income.ui.password.confirm

import cockatoo.enjizen.income.extension.encryptPassword
import cockatoo.enjizen.income.ui.service.PasswordService

class ConfirmPasswordPresenter(private val view: ConfirmPasswordView ,private val service: PasswordService) {

    fun confirmCheckPassword(setPassword: String, confirmPassword: String){

        if(setPassword == confirmPassword){

            val password = confirmPassword.encryptPassword()

            service.savePassword(password = password
                                , onSuccess = { view.onConfirmPasswordSuccess()})

        } else {
            view.onConfirmPasswordNotMatch()
        }

    }

}
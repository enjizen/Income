package cockatoo.enjizen.income.ui.password.confirm

import cockatoo.enjizen.income.extension.hashPassword
import cockatoo.enjizen.income.ui.service.PasswordService

class ConfirmPasswordPresenter(private val view: ConfirmPasswordView ,private val service: PasswordService) {

    fun confirmCheckPassword(setPassword: String, confirmPassword: String){

        if(setPassword == confirmPassword){

            val password = confirmPassword.hashPassword()

            service.savePassword(password = password
                                , onSuccess = { view.onConfirmPasswordSuccess()})


        } else {
            view.onConfirmPasswordNotMatch()
        }

    }

}
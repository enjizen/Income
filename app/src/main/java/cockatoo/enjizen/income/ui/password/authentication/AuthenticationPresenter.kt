package cockatoo.enjizen.income.ui.password.authentication

import cockatoo.enjizen.income.ui.service.PasswordService

class AuthenticationPresenter(private val view: AuthenticationView, private val service: PasswordService) {

    fun authentication(passwordInput: String){

       val isValid = service.authenticationPassword(passwordInput = passwordInput)

        if(isValid){
            view.authenticationSuccess()
        } else {
            view.authenticationFail()
        }
    }
}
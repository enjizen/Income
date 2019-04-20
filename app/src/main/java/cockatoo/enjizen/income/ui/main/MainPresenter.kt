package cockatoo.enjizen.income.ui.main

import cockatoo.enjizen.income.constant.KeyConstant
import cockatoo.enjizen.income.manger.SharedPreferenceSecureUtil

class MainPresenter(private val view: MainView) {

    fun checkSetupPassword(){

      val password = SharedPreferenceSecureUtil.getString(KeyConstant.PASSWORD.value)

        if(password.isNullOrBlank())
            view.haveNotSetPassword()
        else
            view.passwordAlreadySet()
    }

}
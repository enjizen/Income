package cockatoo.enjizen.income.ui.main.home

import cockatoo.enjizen.income.constant.KeyConstant
import cockatoo.enjizen.income.manger.SharedPreferenceSecureUtil

class HomePresenter(private val view: HomeView) {

    fun checkSetupPassword(){

      val password = SharedPreferenceSecureUtil.getString(KeyConstant.PASSWORD.value)

        if(password.isNullOrBlank())
            view.haveNotSetPassword()
        else
            view.passwordAlreadySet()
    }

}
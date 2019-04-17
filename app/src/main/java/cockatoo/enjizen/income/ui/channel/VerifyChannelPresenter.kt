package cockatoo.enjizen.income.ui.channel

import cockatoo.enjizen.income.constant.KeyConstant
import cockatoo.enjizen.income.manger.SharedPreferenceSecureUtil

class VerifyChannelPresenter(private val view: VerifyChannelView) {

    fun checkSetupPassworded(){

      val password = SharedPreferenceSecureUtil.getString(KeyConstant.PASSWORD.value)

        if(password.isNullOrBlank())
            view.haveNotSetPassword()
        else
            view.passwordAlreadySet()

    }

}
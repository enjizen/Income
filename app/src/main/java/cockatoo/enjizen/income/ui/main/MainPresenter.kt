package cockatoo.enjizen.income.ui.main

import cockatoo.enjizen.income.constant.KeyConstant
import cockatoo.enjizen.income.manger.SharedPreferenceSecureUtil
import cockatoo.enjizen.income.ui.channel.VerifyChannelView

class MainPresenter(private val view: VerifyChannelView) {

    fun checkSetupPassworded(){

      val password = SharedPreferenceSecureUtil.getString(KeyConstant.PASSWORD.value)

        if(password.isNullOrBlank())
            view.haveNotSetPassword()
        else
            view.passwordAlreadySet()

    }

}
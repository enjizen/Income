package cockatoo.enjizen.income.ui.channel

import cockatoo.enjizen.income.constant.KeyConstant
import cockatoo.enjizen.income.util.SharedPreferenceSecureUtil

class VerifyChannelPresenter(private val view: VerifyChannelView) {

    interface VerifyChannelView {
        fun haveNotSetPassword()

        fun passwordAlreadySet()
    }

    fun checkSetupPassword(){

      val password = SharedPreferenceSecureUtil.getString(KeyConstant.PASSWORD.value)

        if(password.isNullOrBlank())
            view.haveNotSetPassword()
        else
            view.passwordAlreadySet()

    }

}
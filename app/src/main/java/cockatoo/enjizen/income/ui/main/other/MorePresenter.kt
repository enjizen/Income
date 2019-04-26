package cockatoo.enjizen.income.ui.main.other

import cockatoo.enjizen.income.constant.KeyConstant
import cockatoo.enjizen.income.constant.MoreMenuTarget
import cockatoo.enjizen.income.constant.MoreMenuType
import cockatoo.enjizen.income.manger.SharedPreferenceSecureUtil
import cockatoo.enjizen.income.model.MoreMenu

class MorePresenter(private val view: MoreView) {

    fun verifyMenuItem(menu: MoreMenu){
        if(MoreMenuType.GROUP.type == menu.menuType || MoreMenuType.LINE.type == menu.menuType) {
            return
        }
        when(menu.target){
            MoreMenuTarget.ACCOUNT -> view.gotoAccount()
            MoreMenuTarget.SET_PASSWORD-> view.gotoSetPassword()
            MoreMenuTarget.CHANGE_PASSWORD -> view.gotoChangePassword()
            MoreMenuTarget.LOGOUT -> view.gotoLogout()
        }
    }

    fun checkSetupPassword(){

        val password = view.getPasswordForCheck()

        if(password.isNullOrBlank())
            view.haveNotSetPassword()
        else
            view.passwordAlreadySet()
    }
}
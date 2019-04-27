package cockatoo.enjizen.income.ui.main.other

interface MoreView {

    fun gotoAccount()
    fun gotoSetPassword()
    fun gotoChangePassword()
    fun gotoLogout()
    fun haveNotSetPassword()
    fun passwordAlreadySet()
    fun getPasswordForCheck() : String
}
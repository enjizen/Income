package cockatoo.enjizen.income.ui.account.account

import cockatoo.enjizen.income.ui.service.AccountService

class AccountPresenter(
    private val view: AccountView,
    private val service: AccountService) {
    fun getAllAccount() {
        view.onShowLoading()
        val accounts = service.getAllAccount()
        view.onHideLoading()
        view.setDataAccount(accounts)
    }

    fun get(position: Int){
        val account = service.get(view.getAccountId(position))
        view.goToUpdate(account?.id)
    }

    fun addAccount(){
        view.goToAdd()
    }
}
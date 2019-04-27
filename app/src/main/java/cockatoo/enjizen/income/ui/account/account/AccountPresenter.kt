package cockatoo.enjizen.income.ui.account.account

import cockatoo.enjizen.income.ui.service.AccountService

class AccountPresenter(private val view: AccountView) {

    private val service: AccountService = AccountService()

    fun getAllAccount() {
        view.onShowLoading()
        val accounts = service.getAllAccount()
        view.onHideLoading()
        view.displayAccount(accounts)
    }

    fun get(position: Int){
        val account = service.get(view.getAccountId(position))
        view.goToView(account?.id)
    }

    fun addAccount(){
        view.goToAdd()
    }
}
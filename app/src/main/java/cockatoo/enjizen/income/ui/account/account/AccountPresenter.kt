package cockatoo.enjizen.income.ui.account.account

import cockatoo.enjizen.income.model.Account
import cockatoo.enjizen.income.ui.service.AccountService

class AccountPresenter(private val view: AccountView) {

    interface AccountView
    {
        fun onShowLoading()

        fun onHideLoading()

        fun displayAccount(accounts: ArrayList<Account>)

        fun getAccountId(position: Int): Int

        fun goToView(id: Int?)

        fun goToAdd()
    }


    fun getAllAccount() {
        view.onShowLoading()
        val accounts = AccountService.getAllAccount()
        view.onHideLoading()
        view.displayAccount(accounts)
    }

    fun get(position: Int){
        val account = AccountService.get(view.getAccountId(position))
        view.goToView(account?.id)
    }

    fun addAccount(){
        view.goToAdd()
    }
}
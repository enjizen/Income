package cockatoo.enjizen.income.ui.account.account

import cockatoo.enjizen.income.ui.service.AccountService
import cockatoo.enjizen.income.ui.service.BankService

class AccountPresenter(
    private val view: AccountView
) {

    private val service: AccountService = AccountService()
    private val bankService: BankService = BankService()

    fun getAllAccount() {
        view.onShowLoading()
        val accounts = service.getAllAccount()
        val banks = bankService.getAllBank()

        accounts.forEach { account ->
            banks.forEach { banks ->
                if(account.bankId == banks.id){
                    account.logo = banks.logo
                }
            }
        }

        view.onHideLoading()
        view.setDataAccount(accounts)
    }

    fun get(position: Int){
        val account = service.get(view.getAccountId(position))
        view.goToView(account?.id)
    }

    fun addAccount(){
        view.goToAdd()
    }
}
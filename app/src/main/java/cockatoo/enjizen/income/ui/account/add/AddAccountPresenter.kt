package cockatoo.enjizen.income.ui.account.add

import cockatoo.enjizen.income.ui.service.AccountService

class AddAccountPresenter(
    private val view: AddAccountView,
    private val service: AccountService
) {

    fun addAccount(accountNumber: String, accountName: String, balance: Double){
        service.insertAccount(accountNumber = accountNumber, name = accountName, balance = balance, onSuccess = {
            view.onAddAccountSuccess(accountNumber = accountName)
        })
    }
}
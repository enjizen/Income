package cockatoo.enjizen.income.ui.account.add

import cockatoo.enjizen.income.ui.service.AccountService

class AddAccountPresenter(
    private val view: AddAccountView,
    private val service: AccountService
) {

    fun addAccount(){
        service.insertAccount(accountNumber = view.getAccountNumber()
                            , name = view.getAccountName()
                            , balance = view.getBalance()
                            , onSuccess = {
                                view.onAddAccountSuccess(accountNumber = view.getAccountNumber())
                            })
    }
}
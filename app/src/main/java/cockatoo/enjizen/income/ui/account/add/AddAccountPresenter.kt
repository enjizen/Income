package cockatoo.enjizen.income.ui.account.add

import cockatoo.enjizen.income.ui.service.AccountService
import cockatoo.enjizen.income.ui.service.BankService

class AddAccountPresenter(
    private val view: AddAccountView,
    private val service: AccountService,
    private val bankService: BankService
) {

    fun addAccount() {

        var inValid = false

        if (view.getAccountNumber()!!.isBlank()) {
            view.onAccountNumberInvalid()
            inValid = true
        }

        if (view.getAccountName()!!.isBlank()) {
            view.onAccountNameInvalid()
            inValid = true
        }

        if (view.getBalance() == null) {
            view.onAccountBalanceInvalid()
            inValid = true
        }

        if(!inValid) {
            service.insertAccount(accountNumber = view.getAccountNumber()!!
                , name = view.getAccountName()!!
                , balance = view.getBalance()!!
                , onSuccess = {
                    view.onAddAccountSuccess(accountNumber = view.getAccountNumber()!!)
                })
        }

    }

    fun getBankAll(){
       val banks=  bankService.getAllBank()
        view.onSetDataBank(banks)
    }
}
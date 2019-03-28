package cockatoo.enjizen.income.ui.presenter

import cockatoo.enjizen.income.manger.db.AccountTableHelper
import cockatoo.enjizen.income.ui.router.AddAccountRouter
import cockatoo.enjizen.income.ui.service.AccountService
import cockatoo.enjizen.income.ui.view.AddAccountView

class AddAccountPresenter(
    private val view: AddAccountView,
    private val service: AccountService,
    private val addAccountRouter: AddAccountRouter
) {

    fun addAccount(accountNumber: String, accountName: String, balance: Double){
        AccountTableHelper.insertAccount(accountNumber = accountNumber
            , name = accountName
            ,  balance =  balance, onSuccess = {
                view.onAddAccountSuccess(accountNumber = accountName)
            })
    }
}
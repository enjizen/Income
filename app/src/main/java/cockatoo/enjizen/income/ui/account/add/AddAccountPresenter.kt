package cockatoo.enjizen.income.ui.account.add

import android.text.TextWatcher
import cockatoo.enjizen.income.extension.accountNumberBayFormat
import cockatoo.enjizen.income.ui.service.AccountService
import cockatoo.enjizen.income.ui.service.BankService

class AddAccountPresenter(
    private val view: AddAccountView
) {

    private val service = AccountService()
    private val bankService = BankService()



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

        if(!inValid) {
           val isValid =  service.insertAccount(bankId = view.getBankId()
                , accountNumber = view.getAccountNumber()!!
                , name = view.getAccountName()!!)

            if(isValid){
                view.onAddAccountSuccess(accountNumber = view.getAccountNumber()!!)
            }
        }

    }

    fun getBankAll(){
       val banks=  bankService.getAllBank()
        view.displayBank(banks)
    }

    fun editTextAccountNumberFormat(watcher: TextWatcher){
        view.accountNumberRemoveTextChangedListener(watcher)
        val accountNumber = view.getAccountNumber()?.replace("-","")!!.accountNumberBayFormat()
        view.displayEditTextAccountNumberFormat(accountNumber)
        view.accountNumberAddTextChangedListener(watcher)
    }

}
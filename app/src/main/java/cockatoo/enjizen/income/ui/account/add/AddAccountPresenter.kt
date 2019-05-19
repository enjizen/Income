package cockatoo.enjizen.income.ui.account.add

import android.text.TextWatcher
import cockatoo.enjizen.income.extension.accountNumberBayFormat
import cockatoo.enjizen.income.model.Bank
import cockatoo.enjizen.income.ui.service.AccountService
import cockatoo.enjizen.income.ui.service.BankService

class AddAccountPresenter(
    private val view: AddAccountView
) {


    interface AddAccountView {

        fun getAccountNumber(): String?

        fun getAccountName(): String?

        fun onShowLoading()

        fun onHideLoading()

        fun onAddAccountSuccess(accountNumber: String)

        fun onAccountNumberInvalid()

        fun onAccountNameInvalid()

        fun displayBank(banks: ArrayList<Bank>)

        fun getBankId(): Int

        fun accountNumberRemoveTextChangedListener(watcher: TextWatcher)

        fun accountNumberAddTextChangedListener(watcher: TextWatcher)

        fun displayEditTextAccountNumberFormat(accountNumberFormat: String)
    }


    fun addAccount() {

        var inValid = false

        if (view.getAccountNumber()?.isBlank()!!) {
            view.onAccountNumberInvalid()
            inValid = true
        }

        if (view.getAccountName()?.isBlank()!!) {
            view.onAccountNameInvalid()
            inValid = true
        }

        if(!inValid) {
           val isValid =  AccountService.insertAccount(bankId = view.getBankId()
                , accountNumber = view.getAccountNumber()!!
                , name = view.getAccountName()!!)

            if(isValid){
                view.onAddAccountSuccess(accountNumber = view.getAccountNumber()!!)
            }
        }

    }

    fun getBankAll(){
        view.displayBank(BankService.getAllBank())
    }

    fun editTextAccountNumberFormat(watcher: TextWatcher){
        view.accountNumberRemoveTextChangedListener(watcher)
        view.displayEditTextAccountNumberFormat(view.getAccountNumber()?.replace("-","")!!.accountNumberBayFormat())
        view.accountNumberAddTextChangedListener(watcher)
    }

}
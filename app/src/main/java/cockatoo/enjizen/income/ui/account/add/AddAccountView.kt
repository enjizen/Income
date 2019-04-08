package cockatoo.enjizen.income.ui.account.add

import cockatoo.enjizen.income.model.Bank

interface AddAccountView {

    fun getAccountNumber(): String?

    fun getAccountName(): String?

    fun getBalance(): Double?

    fun onShowLoading()

    fun onHideLoading()

    fun onAddAccountSuccess(accountNumber: String)

    fun onAccountNumberInvalid()

    fun onAccountNameInvalid()

    fun onAccountBalanceInvalid()

    fun onSetDataBank(banks: ArrayList<Bank>)
}
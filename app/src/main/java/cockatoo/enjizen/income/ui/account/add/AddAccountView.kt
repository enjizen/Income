package cockatoo.enjizen.income.ui.account.add

interface AddAccountView {

    fun getAccountNumber(): String

    fun getAccountName(): String

    fun getBalance(): Double

    fun onShowLoading()

    fun onHideLoading()

    fun onAddAccountSuccess(accountNumber: String)
}
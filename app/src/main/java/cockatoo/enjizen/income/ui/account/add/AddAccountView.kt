package cockatoo.enjizen.income.ui.account.add

interface AddAccountView {
    fun onShowLoading()

    fun onHideLoading()

    fun onAddAccountSuccess(accountNumber: String)
}
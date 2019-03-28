package cockatoo.enjizen.income.ui.view

interface AddAccountView {
    fun onShowLoading()

    fun onHideLoading()

    fun onAddAccountSuccess(accountNumber: String)
}
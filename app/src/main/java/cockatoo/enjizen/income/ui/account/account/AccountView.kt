package cockatoo.enjizen.income.ui.account.account

import cockatoo.enjizen.income.model.Account

interface AccountView
{
    fun onShowLoading()

    fun onHideLoading()

    fun displayAccount(accounts: ArrayList<Account>)

    fun getAccountId(position: Int): Int

    fun goToView(id: Int?)

    fun goToAdd()
}
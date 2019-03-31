package cockatoo.enjizen.income.ui.account.account

import cockatoo.enjizen.income.model.Account

interface AccountView
{
    fun onShowLoading()

    fun onHideLoading()

    fun setDataAccount(accounts: ArrayList<Account>)

    fun getAccountId(position: Int): Int

    fun goToUpdate(id: Int?)

    fun goToAdd()
}
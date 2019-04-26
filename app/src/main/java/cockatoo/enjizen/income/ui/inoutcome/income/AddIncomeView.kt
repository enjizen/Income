package cockatoo.enjizen.income.ui.inoutcome.income

import cockatoo.enjizen.income.model.Account

interface AddIncomeView {

    fun displayAccount(accounts: ArrayList<Account>)

    fun displayCurrentDate(dateValue: String)
}
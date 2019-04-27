package cockatoo.enjizen.income.ui.inoutcome.income

import cockatoo.enjizen.income.extension.dateThaiFullFormatter
import cockatoo.enjizen.income.model.Account
import cockatoo.enjizen.income.ui.service.AccountService
import cockatoo.enjizen.income.ui.service.IncomeService
import java.util.*
import kotlin.collections.ArrayList

class AddIncomePresenter(private val view: AddIncomeView) {

    private val incomeService = IncomeService()
    private val accountService = AccountService()

    private var dateIncome: Date? = null
    private var accountList: ArrayList<Account>? = null

    fun getAccount() {

        accountList = accountService.getAllAccount()

        view.displayAccount(accountList!!)
    }

    fun setDateIncome(dateFormat: Date? = null) {
        dateIncome = dateFormat ?: Date()
        view.displayCurrentDate(dateIncome?.dateThaiFullFormatter()!!)
    }

    fun saveIncome(){
        val accountId = accountList?.get(view.getAccountSpinnerPosition())!!.id
        val isResult = incomeService.insertIncome(view.getDetail(), view.getMoneyIncome().toDouble(), accountId, dateIncome!!)
        if(isResult){
            view.saveSuccess()
        }

    }
}
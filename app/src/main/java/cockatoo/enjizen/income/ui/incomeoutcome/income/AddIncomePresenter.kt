package cockatoo.enjizen.income.ui.incomeoutcome.income

import cockatoo.enjizen.income.extension.dateThaiFullFormatter
import cockatoo.enjizen.income.model.Account
import cockatoo.enjizen.income.ui.service.AccountService
import cockatoo.enjizen.income.ui.service.IncomeOutcomeService
import java.util.*
import kotlin.collections.ArrayList

class AddIncomePresenter(private val view: AddIncomeView) {

    interface AddIncomeView {

        fun displayAccount(accounts: ArrayList<Account>)

        fun displayCurrentDate(dateValue: String)

        fun getAccountSpinnerPosition() : Int

        fun getDetail() : String

        fun getMoneyIncome() : String

        fun saveSuccess()
    }

    private var dateIncome: Date = Date()
    private var accountList: ArrayList<Account>? = null

    fun getAccount() {
        view.displayAccount(AccountService.getAllAccount())
    }

    fun setDateIncome(dateFormat: Date? = null) {
        dateIncome = dateFormat ?: Date()
        view.displayCurrentDate(dateIncome.dateThaiFullFormatter())
    }

    fun saveIncome(){
        val accountId = accountList?.get(view.getAccountSpinnerPosition())!!.id
        val isResult = IncomeOutcomeService.insertIncome(view.getDetail(), view.getMoneyIncome().toDouble(), accountId, dateIncome)
        if(isResult){
            view.saveSuccess()
        }

    }
}
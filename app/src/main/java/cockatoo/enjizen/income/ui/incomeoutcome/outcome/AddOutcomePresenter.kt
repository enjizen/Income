package cockatoo.enjizen.income.ui.incomeoutcome.outcome

import cockatoo.enjizen.income.extension.dateThaiFullFormatter
import cockatoo.enjizen.income.model.Account
import cockatoo.enjizen.income.ui.service.AccountService
import cockatoo.enjizen.income.ui.service.IncomeOutcomeService
import java.util.*
import kotlin.collections.ArrayList

class AddOutcomePresenter(private val view : AddOutcomeView) {
    interface AddOutcomeView{

        fun getDetail() : String

        fun getMoneyOutcome() : String

        fun displayAccount(accounts: ArrayList<Account>)

        fun displayCurrentDate(dateValue: String)

        fun getAccountSpinnerPosition() : Int

        fun saveSuccess()

    }

    private var dateIncome: Date? = null
    private var accountList: ArrayList<Account>? = null

    fun getAccount() {

        accountList = AccountService.getAllAccount()

        view.displayAccount(accountList!!)
    }

    fun setDateOutcome(dateFormat: Date? = null) {
        dateIncome = dateFormat ?: Date()
        view.displayCurrentDate(dateIncome?.dateThaiFullFormatter()!!)
    }

    fun saveOutcome(){
        val accountId = accountList?.get(view.getAccountSpinnerPosition())!!.id
        val isResult = IncomeOutcomeService.insertOutcome(view.getDetail(), view.getMoneyOutcome().toDouble(), accountId, dateIncome!!)
        if(isResult){
            view.saveSuccess()
        }
    }
}
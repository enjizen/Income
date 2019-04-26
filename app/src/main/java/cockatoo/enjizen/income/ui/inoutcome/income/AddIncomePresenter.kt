package cockatoo.enjizen.income.ui.inoutcome.income

import cockatoo.enjizen.income.ui.service.AccountService
import cockatoo.enjizen.income.ui.service.BankService
import cockatoo.enjizen.income.ui.service.IncomeService
import java.text.SimpleDateFormat
import java.util.*

class AddIncomePresenter(private val view: AddIncomeView) {

    private val incomeService =  IncomeService()
    private val accountService = AccountService()
    private val bankService = BankService()

    fun getAccount(){

        val accountList = accountService.getAllAccount()
        val bankList = bankService.getAllBank()

        accountList.forEach { account ->
            bankList.forEach { banks ->
                if(account.bankId == banks.id){
                    account.logo = banks.logo
                }
            }
        }

        view.displayAccount(accountList)

    }

    fun setDateIncome(dateFormat: Date? = null){
        val formatter = SimpleDateFormat("วันที่ dd MMMM YYYY", Locale("th", "TH"))
        val date = if(dateFormat == null) Date() else dateFormat
        view.displayCurrentDate(formatter.format(Date()))
    }
}
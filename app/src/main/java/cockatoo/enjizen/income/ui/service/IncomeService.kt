package cockatoo.enjizen.income.ui.service

import android.content.ContentValues
import cockatoo.enjizen.income.constant.DBContract
import cockatoo.enjizen.income.extension.dateFormatSaveDB
import cockatoo.enjizen.income.manger.db.DBHelper
import java.util.*

class IncomeService {

    fun insertIncome(detail: String, moneyIncome: Double, accountId: Int, dateIncome: Date) : Boolean{
        val values = ContentValues().apply {
            put(DBContract.IncomeEntry.COLUMN_DETAIL.value, detail)
            put(DBContract.IncomeEntry.COLUMN_MONEY_INCOME.value, moneyIncome)
            put(DBContract.IncomeEntry.COLUMN_ACCOUNT_ID.value, accountId)
            put(DBContract.IncomeEntry.COLUMN_CREATED_DATE.value, dateIncome.dateFormatSaveDB())
        }
        DBHelper.getInstance().insert(DBContract.IncomeEntry.TABLE_NAME.value, values)
        return true
    }

}
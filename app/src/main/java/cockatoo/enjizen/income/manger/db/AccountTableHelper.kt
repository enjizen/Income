package cockatoo.enjizen.income.manger.db

import android.content.ContentValues
import cockatoo.enjizen.income.constant.DBContract

object AccountTableHelper {

    fun insertAccount(accountNumber: String, name: String, balance: Double, onSuccess: () -> Unit){
        val values = ContentValues()
        values.put(DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.columns, accountNumber)
        values.put(DBContract.AccountEntry.COLUMN_NAME.columns, name)
        values.put(DBContract.AccountEntry.COLUMN_BALANCE.columns, balance)
        DBHelper.getInstance().insert(DBContract.AccountEntry.TABLE_NAME.columns, values)
        onSuccess()
    }

    fun updateAccount(id: Int, accountNumber: String, name: String, balance: Double, onSuccess: () -> Unit){

    }

}
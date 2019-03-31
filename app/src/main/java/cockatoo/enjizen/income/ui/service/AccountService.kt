package cockatoo.enjizen.income.ui.service

import android.content.ContentValues
import cockatoo.enjizen.income.constant.DBContract
import cockatoo.enjizen.income.manger.db.DBHelper
import cockatoo.enjizen.income.model.Account
import net.sqlcipher.Cursor

class AccountService {

    fun insertAccount(accountNumber: String, name: String, balance: Double, onSuccess: () -> Unit){
        val values = ContentValues()
        values.put(DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.columns, accountNumber)
        values.put(DBContract.AccountEntry.COLUMN_NAME.columns, name)
        values.put(DBContract.AccountEntry.COLUMN_BALANCE.columns, balance)
        DBHelper.getInstance().insert(DBContract.AccountEntry.TABLE_NAME.columns, values)
        onSuccess()
    }

    fun get(id: Int): Account?{
        val selection =arrayOf(DBContract.AccountEntry.COLUMN_ID.columns)
        val valueSelection = arrayOf(id.toString())

        val cursor =   DBHelper.getInstance().get(DBContract.AccountEntry.TABLE_NAME.columns, selection, valueSelection)
        return if(cursor != null){
            cursor.moveToFirst()
            val accountNumber = cursor.getString(cursor.getColumnIndex(DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.columns))
            Account(id= id, accountNumber = accountNumber, name = "", balance = 0.00)
        } else{
            null
        }
    }


    fun getAllAccount() : ArrayList<Account>{
        val accounts = ArrayList<Account>()
        val cursor = DBHelper.getInstance().getAll(DBContract.AccountEntry.TABLE_NAME.columns)
        if(cursor != null){
            while (cursor.moveToNext()) {
                accounts.add(readAccounts(cursor))
            }
        }
        cursor?.close()
        return accounts
    }

    private fun readAccounts(cursor: Cursor): Account {

        val id = cursor.getInt(cursor.getColumnIndex( DBContract.AccountEntry.COLUMN_ID.columns))
        val accountNumber = cursor.getString(cursor.getColumnIndex(DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.columns))
        val name = cursor.getString(cursor.getColumnIndex(DBContract.AccountEntry.COLUMN_NAME.columns))
        val balance = cursor.getDouble(cursor.getColumnIndex(DBContract.AccountEntry.COLUMN_BALANCE.columns))

        return Account(id = id, accountNumber = accountNumber, name = name, balance = balance)
    }
}
package cockatoo.enjizen.income.manger.db

import android.content.ContentValues
import cockatoo.enjizen.income.constant.DBContract
import cockatoo.enjizen.income.model.Account
import net.sqlcipher.Cursor

object AccountTableHelper {

    fun insertAccount(accountNumber: String, name: String, balance: Double, onSuccess: () -> Unit){
        val values = ContentValues()
        values.put(DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.columns, accountNumber)
        values.put(DBContract.AccountEntry.COLUMN_NAME.columns, name)
        values.put(DBContract.AccountEntry.COLUMN_BALANCE.columns, balance)
        DBHelper.getInstance().insert(DBContract.AccountEntry.TABLE_NAME.columns, values)
        onSuccess()
    }

   /* fun updateAccount(id: Int, accountNumber: String, name: String, balance: Double, onSuccess: () -> Unit){

    }*/

    fun getAllAccount() : ArrayList<Account>{
        val accounts = ArrayList<Account>()
        val cursor = DBHelper.getInstance().getAll()
        if(cursor != null){
            while (cursor.moveToNext()) {
                accounts.add(readAccount(cursor))
            }
        }
        cursor?.close()
        return accounts
    }

    private fun readAccount(cursor: Cursor): Account {

        val id = cursor.getInt(cursor.getColumnIndex( DBContract.AccountEntry.COLUMN_ID.columns))
        val accountNumber = cursor.getString(cursor.getColumnIndex(DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.columns))
        val name = cursor.getString(cursor.getColumnIndex(DBContract.AccountEntry.COLUMN_NAME.columns))
        val balance = cursor.getDouble(cursor.getColumnIndex(DBContract.AccountEntry.COLUMN_BALANCE.columns))

        return Account(id = id, accountNumber = accountNumber, name = name, balance = balance)
    }

}
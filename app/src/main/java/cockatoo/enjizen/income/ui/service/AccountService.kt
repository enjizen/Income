package cockatoo.enjizen.income.ui.service

import android.content.ContentValues
import cockatoo.enjizen.income.constant.DBContract
import cockatoo.enjizen.income.manger.db.DBHelper
import cockatoo.enjizen.income.model.Account
import net.sqlcipher.Cursor

class AccountService {

    fun insertAccount(bankId: Int, accountNumber: String, name: String, balance: Double): Boolean {
        val values = ContentValues().apply {
            put(DBContract.AccountEntry.COLUMN_BANK_ID.value, bankId)
            put(DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.value, accountNumber)
            put(DBContract.AccountEntry.COLUMN_NAME.value, name)
            put(DBContract.AccountEntry.COLUMN_BALANCE.value, balance)
        }
        DBHelper.getInstance().insert(DBContract.AccountEntry.TABLE_NAME.value, values)
        return true
    }

    fun get(id: Int): Account? {
        val selection = arrayOf(DBContract.AccountEntry.COLUMN_ID.value)
        val valueSelection = arrayOf(id.toString())

        val cursor = DBHelper.getInstance().get(DBContract.AccountEntry.TABLE_NAME.value, selection, valueSelection)
        return if (cursor != null) {
            with(cursor) {
                moveToFirst()
                val accountNumber = getString(getColumnIndex(DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.value))
                val accountName = getString(getColumnIndex(DBContract.AccountEntry.COLUMN_NAME.value))
                val balance = getDouble(getColumnIndex(DBContract.AccountEntry.COLUMN_BALANCE.value))
                val bankId = getInt(getColumnIndex(DBContract.AccountEntry.COLUMN_BANK_ID.value))
                Account(id = id, accountNumber = accountNumber, name = accountName, balance = balance, bankId = bankId)
            }
        } else {
            null
        }
    }


    fun getAllAccount(): ArrayList<Account> {
        val accounts = ArrayList<Account>()
        val cursor = DBHelper.getInstance().getAll(DBContract.AccountEntry.TABLE_NAME.value)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                accounts.add(readAccounts(cursor))
            }
        }
        cursor?.close()
        return accounts
    }

    private fun readAccounts(cursor: Cursor): Account {

        with(cursor) {
            val id = getInt(getColumnIndex(DBContract.AccountEntry.COLUMN_ID.value))
            val accountNumber =
                getString(getColumnIndex(DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.value))
            val name = getString(getColumnIndex(DBContract.AccountEntry.COLUMN_NAME.value))
            val balance = getDouble(getColumnIndex(DBContract.AccountEntry.COLUMN_BALANCE.value))
            val bankId = getInt(getColumnIndex(DBContract.AccountEntry.COLUMN_BANK_ID.value))

            return Account(id = id, accountNumber = accountNumber, name = name, balance = balance, bankId = bankId)
        }


    }
}
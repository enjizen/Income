package cockatoo.enjizen.income.ui.service

import android.content.ContentValues
import cockatoo.enjizen.income.constant.DBContract
import cockatoo.enjizen.income.manger.db.DBHelper
import cockatoo.enjizen.income.model.Account
import net.sqlcipher.Cursor

object AccountService {

    fun insertAccount(bankId: Int, accountNumber: String, name: String): Boolean {
        ContentValues().apply {
            put(DBContract.AccountEntry.COLUMN_BANK_ID.value, bankId)
            put(DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.value, accountNumber)
            put(DBContract.AccountEntry.COLUMN_NAME.value, name)
        }.run {
            DBHelper.getInstance().insert(DBContract.AccountEntry.TABLE_NAME.value, this)
        }.also {
            return true
        }


    }

    fun get(id: Int): Account? {
        val selection = arrayOf(DBContract.AccountEntry.COLUMN_ID.value)
        val valueSelection = arrayOf(id.toString())
        val cursor = DBHelper.getInstance().get(DBContract.AccountEntry.TABLE_NAME.value, selection, valueSelection)
        return cursor?.let {
            with(it) {
                moveToFirst()
                Account(
                    id = id
                    , accountNumber = getString(getColumnIndex(DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.value))
                    , name = getString(getColumnIndex(DBContract.AccountEntry.COLUMN_NAME.value))
                    , bankId = getInt(getColumnIndex(DBContract.AccountEntry.COLUMN_BANK_ID.value))
                )
            }
        }
    }

    fun getAllAccount(): ArrayList<Account> {
        val sqlQuery = StringBuilder().apply {
            append("SELECT a.id")
            append(", number")
            append(", a.name")
            append(", bank_id")
            append(", b.logo")
            append(",SUM(money_income)  as money_income")
            append(",SUM(money_outcome) as money_outcome")
            append(" FROM account a")
            append(" LEFT JOIN bank b ON(a.bank_id = b.id)")
            append(" LEFT JOIN income i ON (a.id = i.account_Id)")
            append(" LEFT JOIN outcome o ON a.id = o.account_Id")
            append(" GROUP BY a.id, number, a.name, bank_id, logo")
        }

        val accounts = ArrayList<Account>()
        val cursor = DBHelper.getInstance().rawQuery(sqlQuery.toString())
        while (cursor?.moveToNext()!!) {
            accounts.add(readAccounts(cursor))
        }
        cursor.close()
        return accounts
    }

    private fun readAccounts(cursor: Cursor): Account {
        with(cursor) {
            val moneyIncome = getString(getColumnIndex(DBContract.AccountEntry.COLUMN_MONEY_INCOME.value)) ?: "0.00"
            val moneyOutCome = getString(getColumnIndex(DBContract.AccountEntry.COLUMN_MONEY_OUTCOME.value)) ?: "0.00"

            return Account(
                id = getInt(getColumnIndex(DBContract.AccountEntry.COLUMN_ID.value))
                , accountNumber = getString(getColumnIndex(DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.value))
                , name = getString(getColumnIndex(DBContract.AccountEntry.COLUMN_NAME.value))
                , bankId = getInt(getColumnIndex(DBContract.AccountEntry.COLUMN_BANK_ID.value))
                , logo = getString(getColumnIndex(DBContract.BankEntry.COLUMN_LOGO.value))
                , balance = moneyIncome.toDouble() + moneyOutCome.toDouble()
            )
        }


    }
}
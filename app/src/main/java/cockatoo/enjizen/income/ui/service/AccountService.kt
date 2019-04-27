package cockatoo.enjizen.income.ui.service

import android.content.ContentValues
import cockatoo.enjizen.income.constant.DBContract
import cockatoo.enjizen.income.manger.db.DBHelper
import cockatoo.enjizen.income.model.Account
import net.sqlcipher.Cursor

class AccountService {

    fun insertAccount(bankId: Int, accountNumber: String, name: String): Boolean {
        val values = ContentValues().apply {
            put(DBContract.AccountEntry.COLUMN_BANK_ID.value, bankId)
            put(DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.value, accountNumber)
            put(DBContract.AccountEntry.COLUMN_NAME.value, name)
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
                val bankId = getInt(getColumnIndex(DBContract.AccountEntry.COLUMN_BANK_ID.value))
                Account(id = id, accountNumber = accountNumber, name = accountName, bankId = bankId)
            }
        } else {
            null
        }
    }


    fun getAllAccount(): ArrayList<Account> {

        val sqlQuery = "SELECT a.id\n" +
                "\t\t\t, number\n" +
                "\t\t\t, a.name\n" +
                "\t\t\t, bank_id\n" +
                "\t\t\t, b.logo\n" +
                "\t\t\t,SUM(money_income)  as money_income\n" +
                "\t\t\t,SUM(money_outcome) as money_outcome\n" +
                "\t\t\tFROM account a\n" +
                "LEFT JOIN bank b ON(a.bank_id = b.id)\n" +
                "LEFT JOIN income i ON (a.id = i.account_Id)\n" +
                "LEFT JOIN outcome o ON a.id = o.account_Id\n" +
                "GROUP BY a.id, number, a.name, bank_id, logo"

        val accounts = ArrayList<Account>()
        val cursor = DBHelper.getInstance().rawQuery(sqlQuery)
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
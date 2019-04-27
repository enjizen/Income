package cockatoo.enjizen.income.ui.service

import cockatoo.enjizen.income.constant.DBContract
import cockatoo.enjizen.income.manger.db.DBHelper
import cockatoo.enjizen.income.model.Bank
import net.sqlcipher.Cursor

class BankService {

    fun getAllBank() : ArrayList<Bank>{
        val banks = ArrayList<Bank>()
        val cursor = DBHelper.getInstance().getAll(DBContract.BankEntry.TABLE_NAME.value)
        if(cursor != null){
            while (cursor.moveToNext()) {
                banks.add(readBanks(cursor))
            }
        }
        cursor?.close()
        return banks
    }

    private fun readBanks(cursor: Cursor): Bank {
        with(cursor){
            val id = getInt(getColumnIndex( DBContract.BankEntry.COLUMN_ID.value))
            val name = getString(getColumnIndex(DBContract.BankEntry.COLUMN_NAME.value))
            val initials = getString(getColumnIndex(DBContract.BankEntry.COLUMN_INITIALS.value))
            val logo = getString(getColumnIndex(DBContract.BankEntry.COLUMN_LOGO.value))

            return Bank(id = id, name = name, initials = initials, logo = logo)
        }

    }
}
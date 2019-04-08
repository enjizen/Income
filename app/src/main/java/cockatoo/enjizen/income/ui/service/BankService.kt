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

        val id = cursor.getInt(cursor.getColumnIndex( DBContract.BankEntry.COLUMN_ID.value))
        val name = cursor.getString(cursor.getColumnIndex(DBContract.BankEntry.COLUMN_NAME.value))
        val initials = cursor.getString(cursor.getColumnIndex(DBContract.BankEntry.COLUMN_INITIALS.value))
        val logo = cursor.getString(cursor.getColumnIndex(DBContract.BankEntry.COLUMN_LOGO.value))

        return Bank(id = id, name = name, initials = initials, logo = logo)
    }
}
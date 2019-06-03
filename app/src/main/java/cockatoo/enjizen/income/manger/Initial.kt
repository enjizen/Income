package cockatoo.enjizen.income.manger

import android.content.ContentValues
import cockatoo.enjizen.income.constant.DBContract
import cockatoo.enjizen.income.extension.readJsonFile
import cockatoo.enjizen.income.manger.db.DBHelper
import cockatoo.enjizen.income.model.Bank
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object Initial {

    fun bank() {
        val cursor = DBHelper.getInstance().getAll(DBContract.BankEntry.TABLE_NAME.value)

        if (!cursor!!.moveToFirst()) {
            val listType = object : TypeToken<List<Bank>>() {}.type
            val bankList = Gson().fromJson<List<Bank>>("bank.json".readJsonFile(), listType)
            ArrayList<ContentValues>().apply {
                bankList.forEach {
                    add(ContentValues().apply {
                        put(DBContract.BankEntry.COLUMN_ID.value, it.id)
                        put(DBContract.BankEntry.COLUMN_NAME.value, it.name)
                        put(DBContract.BankEntry.COLUMN_INITIALS.value, it.initials)
                        put(DBContract.BankEntry.COLUMN_LOGO.value, it.logo)
                    })
                }
            }.run {
                DBHelper.getInstance().insert(DBContract.BankEntry.TABLE_NAME.value, this)
            }
        }
        cursor.close()
    }

}
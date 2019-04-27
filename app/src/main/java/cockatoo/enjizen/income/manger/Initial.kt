package cockatoo.enjizen.income.manger

import android.content.ContentValues
import cockatoo.enjizen.income.constant.DBContract
import cockatoo.enjizen.income.extension.readJsonFile
import cockatoo.enjizen.income.manger.db.DBHelper
import cockatoo.enjizen.income.model.Bank
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Initial {

    fun bank() {
        val cursor = DBHelper.getInstance().getAll(DBContract.BankEntry.TABLE_NAME.value)

        if (!cursor!!.moveToFirst()) {
            val listType = object : TypeToken<List<Bank>>() {}.type
            val bankList = Gson().fromJson<List<Bank>>("bank.json".readJsonFile(), listType)

            val values = ArrayList<ContentValues>()
            bankList.forEach {
                val v = ContentValues()
                v.put(DBContract.BankEntry.COLUMN_ID.value, it.id)
                v.put(DBContract.BankEntry.COLUMN_NAME.value, it.name)
                v.put(DBContract.BankEntry.COLUMN_INITIALS.value, it.initials)
                v.put(DBContract.BankEntry.COLUMN_LOGO.value, it.logo)
                values.add(v)
            }

            DBHelper.getInstance().insert(DBContract.BankEntry.TABLE_NAME.value, values)
        }

        cursor.close()

    }

}
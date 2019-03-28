package cockatoo.enjizen.income

import android.app.Application
import android.util.Log
import cockatoo.enjizen.income.constant.KeyConstant
import cockatoo.enjizen.income.manger.*
import net.sqlcipher.database.SQLiteDatabase

class IncomeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Contextor.getInstance().init(applicationContext)
        SQLiteDatabase.loadLibs(applicationContext)
        ToolUtil.createKeyEncryptData()
    }


    override fun onTerminate() {
        super.onTerminate()
    }


}
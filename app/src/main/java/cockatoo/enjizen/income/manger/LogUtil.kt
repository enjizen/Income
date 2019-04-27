package cockatoo.enjizen.income.manger

import android.util.Log
import cockatoo.enjizen.income.BuildConfig

object LogUtil {

    fun i(tag: String, msg: String) {
        if (BuildConfig.DEBUG)
            Log.i(tag, msg)
    }
}

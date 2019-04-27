package cockatoo.enjizen.income.manger

import android.preference.PreferenceManager

object SharedPreferenceUtil {

    private val  sharedPreference = PreferenceManager.getDefaultSharedPreferences(Contextor.getInstance().context)!!


    fun edit(key: String, value: String){
        sharedPreference.edit().putString(key, value).apply()
    }

    fun getString(key: String) : String?{
         return   sharedPreference.getString(key, "")
    }
}
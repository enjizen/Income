package cockatoo.enjizen.income.util

import com.securepreferences.SecurePreferences
import cockatoo.enjizen.income.manger.Contextor
import cockatoo.enjizen.income.manger.KeyEncryptData


object SharedPreferenceSecureUtil {
    private val sharedPreference = SecurePreferences(Contextor.getInstance().context, KeyEncryptData.getInstance().key, "_prefs.xml")

    fun edit(key: String, value: String){
        sharedPreference.edit().putString(key, value).apply()
    }

    fun getString(key: String) : String?{
        return sharedPreference.getString(key, null)
    }
}
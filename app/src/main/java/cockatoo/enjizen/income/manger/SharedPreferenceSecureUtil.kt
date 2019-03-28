package cockatoo.enjizen.income.manger

import com.securepreferences.SecurePreferences
import android.content.SharedPreferences



object SharedPreferenceSecureUtil {
    var sharedPreference: SharedPreferences = SecurePreferences(Contextor.getInstance().context, KeyEncryptData.getInstance().key, "_prefs.xml")

    fun edit(key: String, value: String){
        sharedPreference.edit().putString(key, value).apply()
    }

    fun edit(key: String, value: Int){
        sharedPreference.edit().putInt(key, value).apply()
    }

    fun getString(key: String) : String?{
        return sharedPreference.getString(key, "")
    }

    fun getInt(key: String) : Int?{
        return sharedPreference.getInt(key, 0)
    }
}
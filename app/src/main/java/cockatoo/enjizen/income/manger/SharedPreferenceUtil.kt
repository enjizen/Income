package cockatoo.enjizen.income.manger

import android.preference.PreferenceManager
import cockatoo.enjizen.income.manger.AESService.decrypt
import cockatoo.enjizen.income.manger.AESService.encrypt

object SharedPreferenceUtil {

    private val  sharedPreference = PreferenceManager.getDefaultSharedPreferences(Contextor.getInstance().context)!!


    fun edit(key: String, value: String, isEncryptData: Boolean = false){
        val valueUse = if(isEncryptData){
            encrypt(value, KeyEncryptData.getInstance().key!!)
        } else {
            value
        }
        sharedPreference.edit().putString(key, valueUse).apply()
    }

    fun getString(key: String, isEncryptData: Boolean = false) : String?{
        return if(isEncryptData){
            val result =  sharedPreference.getString(key, "")
           decrypt(result!!, KeyEncryptData.getInstance().key!!)
        } else {
            sharedPreference.getString(key, "")
        }
    }
}
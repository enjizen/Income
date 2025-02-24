package cockatoo.enjizen.income.manger

import android.annotation.SuppressLint
import android.provider.Settings
import cockatoo.enjizen.income.BuildConfig
import cockatoo.enjizen.income.constant.KeyConstant
import cockatoo.enjizen.income.util.LogUtil
import cockatoo.enjizen.income.util.SharedPreferenceUtil

object ToolUtil {

    @SuppressLint("HardwareIds")
    fun createKeyEncryptData() {

        checkAndCreateSalt()

        val salt = SharedPreferenceUtil.getString(key = KeyConstant.SALT.value)

        val device =
            Settings.Secure.getString(Contextor.getInstance().context!!.contentResolver, Settings.Secure.ANDROID_ID)!!

        val applicationId = BuildConfig.APPLICATION_ID

        var applicationIdAscii = 0

        applicationId.forEach {
            applicationIdAscii += it.toInt()
        }
        val key = "$device${applicationIdAscii % salt!!.toInt()}${applicationId.length % salt.toInt()}"
        KeyEncryptData.getInstance().init(key)
        LogUtil.i("ToolUtil", "key = $key")
    }

    private fun checkAndCreateSalt() {
        if (SharedPreferenceUtil.getString(key = KeyConstant.SALT.value).isNullOrBlank()) {
            SharedPreferenceUtil.edit(key = KeyConstant.SALT.value, value = KeyGenerateManager.generateSalt())
        }
    }

}
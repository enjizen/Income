package cockatoo.enjizen.income.extension

import cockatoo.enjizen.income.constant.KeyConstant
import cockatoo.enjizen.income.manger.SharedPreferenceUtil
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@Throws(NoSuchAlgorithmException::class)
fun String.encryptPassword(): String {
    val salt = SharedPreferenceUtil.getString(key = KeyConstant.SALT.value)

    var totalPass = 0
    this.forEach {
        totalPass += it.toInt()
    }

    val pass = "$this${(this.toInt() % salt!!.toInt())}$totalPass"

    val digest = MessageDigest.getInstance("SHA-256")
    val encoded = digest.digest(pass.toByteArray(StandardCharsets.UTF_8))
    return bytesToHex(encoded)
}


private fun bytesToHex(hash: ByteArray): String {
    val hexString = StringBuffer()
    for (i in hash.indices) {
        val hex = Integer.toHexString(0xff and hash[i].toInt())
        if (hex.length == 1) hexString.append('0')
        hexString.append(hex)
    }
    return hexString.toString()
}
package cockatoo.enjizen.income.manger

import org.apache.commons.codec.binary.Base64

import javax.crypto.*
import javax.crypto.spec.SecretKeySpec
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.spec.IvParameterSpec

object AESService {

    @Throws(NoSuchAlgorithmException::class, NoSuchPaddingException::class, InvalidKeyException::class, UnsupportedEncodingException::class, IllegalBlockSizeException::class, BadPaddingException::class)
    fun encrypt(strToEncrypt: String, secret: String): String {

        val secretKey = getKeyAES256(secret)
        val iv = getIv(secret)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)

        return String(Base64.encodeBase64(cipher.doFinal(strToEncrypt.toByteArray(charset("UTF-8")))))
    }

    @Throws(NoSuchPaddingException::class, NoSuchAlgorithmException::class, InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class, UnsupportedEncodingException::class)
    fun decrypt(cipherText: String, key: String): String {

        val secretKey = getKeyAES256(key)
        val iv = getIv(key)
        val decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, iv)

        val v = Base64.decodeBase64(cipherText.toByteArray())
        val decodedByte = decryptCipher.doFinal(v)
        return String(decodedByte)

    }

    @Throws(UnsupportedEncodingException::class)
    private fun getKeyAES256(myKey: String): SecretKey {
        var key = myKey.toByteArray(charset("UTF-8"))
        key = key.copyOf(32)
        return SecretKeySpec(key, "AES")
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getIv(myKey: String): IvParameterSpec {
        var key = myKey.toByteArray(charset("UTF-8"))
        key = key.copyOf(16)
        return IvParameterSpec(key)
    }

}


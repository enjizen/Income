package cockatoo.enjizen.income.manger

import org.apache.commons.codec.binary.Hex
import java.security.SecureRandom
import javax.crypto.KeyGenerator

object KeyGenerateManager {

    fun generateAESKey() : String{
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(256) // or 256

        // Generate the secret key specs
        val secretKey = keyGen.generateKey()
        return  String(Hex.encodeHex(secretKey.encoded)).toUpperCase()
    }

    fun generateSalt() : String{
        val sr = SecureRandom()
       return sr.nextInt(24).toString()
    }

}
package cockatoo.enjizen.income.manger

import java.security.SecureRandom

object KeyGenerateManager {

    fun generateSalt() : String{
        val sr = SecureRandom()
       return (sr.nextInt(24) + 1).toString()
    }

}
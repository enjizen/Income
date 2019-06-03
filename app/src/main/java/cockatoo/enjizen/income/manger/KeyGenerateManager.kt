package cockatoo.enjizen.income.manger

import java.security.SecureRandom

object KeyGenerateManager {

    fun generateSalt() : String{
       return (SecureRandom().nextInt(24) + 1).toString()
    }

}
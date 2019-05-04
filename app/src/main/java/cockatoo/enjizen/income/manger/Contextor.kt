package cockatoo.enjizen.income.manger

import android.annotation.SuppressLint
import android.content.Context

/**
 * Created by Wanchalerm Yuphasuk on 4/1/2018 AD.
 */
class Contextor private constructor() {

    var context: Context? = null
        private set

    internal fun init(context: Context) {
        this.context = context
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var instance: Contextor? = null

        fun getInstance(): Contextor {
            if (instance == null)
                instance = Contextor()
            return instance as Contextor
        }
    }

}

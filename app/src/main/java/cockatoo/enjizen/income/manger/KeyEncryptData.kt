package cockatoo.enjizen.income.manger


/**
 * Created by Wanchalerm Yuphasuk on 4/1/2018 AD.
 */
class KeyEncryptData private constructor() {

    var key: String? = null
        private set

     fun init(key: String) {
        this.key = key
    }

    companion object {

        private var instance: KeyEncryptData? = null

        fun getInstance(): KeyEncryptData {
            if (instance == null)
                instance = KeyEncryptData()
            return instance as KeyEncryptData
        }
    }

}

package cockatoo.enjizen.income.ui.service

import cockatoo.enjizen.income.constant.KeyConstant
import cockatoo.enjizen.income.manger.SharedPreferenceSecureUtil

class PasswordService {

    fun savePassword(password: String, onSuccess: () -> Unit){
        SharedPreferenceSecureUtil.edit(KeyConstant.PASSWORD.value,  password)
        onSuccess()
    }

}
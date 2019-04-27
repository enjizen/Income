package cockatoo.enjizen.income.ui.service

import cockatoo.enjizen.income.constant.KeyConstant
import cockatoo.enjizen.income.extension.hashPassword
import cockatoo.enjizen.income.manger.SharedPreferenceSecureUtil

class PasswordService {

    fun savePassword(password: String, onSuccess: () -> Unit){
        SharedPreferenceSecureUtil.edit(KeyConstant.PASSWORD.value,  password)
        onSuccess()
    }

    fun authenticationPassword(passwordInput: String) : Boolean{
        val passwordInputHash = passwordInput.hashPassword()
        val passwordInSharedPreference = SharedPreferenceSecureUtil.getString(KeyConstant.PASSWORD.value)
        return passwordInputHash == passwordInSharedPreference
    }

}
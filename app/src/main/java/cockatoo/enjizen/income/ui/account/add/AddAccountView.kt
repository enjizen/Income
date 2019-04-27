package cockatoo.enjizen.income.ui.account.add

import android.text.TextWatcher
import cockatoo.enjizen.income.model.Bank

interface AddAccountView {

    fun getAccountNumber(): String?

    fun getAccountName(): String?

    fun onShowLoading()

    fun onHideLoading()

    fun onAddAccountSuccess(accountNumber: String)

    fun onAccountNumberInvalid()

    fun onAccountNameInvalid()

    fun displayBank(banks: ArrayList<Bank>)

    fun getBankId(): Int

    fun accountNumberRemoveTextChangedListener(watcher: TextWatcher)

    fun accountNumberAddTextChangedListener(watcher: TextWatcher)

    fun displayEditTextAccountNumberFormat(accountNumberFormat: String)
}
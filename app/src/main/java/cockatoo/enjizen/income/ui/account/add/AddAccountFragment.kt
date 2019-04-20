package cockatoo.enjizen.income.ui.account.add


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import cockatoo.enjizen.income.R
import com.valdesekamdem.library.mdtoast.MDToast
import kotlinx.android.synthetic.main.fragment_add_acount.*
import android.content.DialogInterface
import android.text.Editable
import android.text.TextWatcher
import cockatoo.enjizen.income.model.Bank
import cockatoo.enjizen.income.ui.adapter.spinner.BankAdapter
import cockatoo.enjizen.income.ui.base.BaseDialogFragment
import kotlinx.android.synthetic.main.edit_text.view.*
import kotlinx.android.synthetic.main.spinner.view.*


class AddAccountFragment : BaseDialogFragment(), AddAccountView {

    private lateinit var presenter: AddAccountPresenter
    private lateinit var listener: AddAccountListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
        presenter = AddAccountPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_acount, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarListener(toolBar)

        presenter.getBankAll()

        btnSave.setOnClickListener {
            presenter.addAccount()
        }

        etAccountNumber.getEditText().addTextChangedListener(editTextAccount)
    }



    override fun onAddAccountSuccess(accountNumber: String) {
        MDToast.makeText(
            context,
            "${getString(R.string.message_account_number)} $accountNumber ${getString(R.string.finish)}",
            Toast.LENGTH_SHORT,
            MDToast.TYPE_SUCCESS
        ).show()
        dismiss()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listener.onDismissAddAccount()
    }

    override fun getAccountNumber(): String? = etAccountNumber.getText()

    override fun getAccountName(): String? = etAccountName.getText()

    override fun getBalance(): Double? {
        return if (etAccountBalance.getText().isNotBlank()) {
            etAccountBalance.getText().toDouble()
        } else {
            null
        }
    }

    override fun onAccountNumberInvalid() {
        etAccountNumber.invalidStyle()
    }

    override fun onAccountNameInvalid() {
        etAccountName.invalidStyle()
    }

    override fun onAccountBalanceInvalid() {
        etAccountBalance.invalidStyle()
    }

    override fun onShowLoading() {
        showLoading()
    }

    override fun onHideLoading() {
        hideLoading()
    }

    override fun displayBank(banks: ArrayList<Bank>) {

        val adapter = BankAdapter(bankList = banks)
        bankSpinner.spinner.adapter = adapter
    }

    override fun getBankId(): Int {
        return bankSpinner.spinner.selectedItemPosition + 1
    }

    override fun accountNumberRemoveTextChangedListener(watcher: TextWatcher) {
        etAccountNumber.editText.removeTextChangedListener(watcher)
    }

    override fun accountNumberAddTextChangedListener(watcher: TextWatcher) {
        etAccountNumber.editText.addTextChangedListener(watcher)
    }

    override fun displayEditTextAccountNumberFormat(accountNumberFormat: String) {
        etAccountNumber.editText.setText(accountNumberFormat)
        etAccountNumber.editText.setSelection(accountNumberFormat.length)
    }


    fun setListener(listener: AddAccountListener) {
        this.listener = listener
    }

    interface AddAccountListener {
        fun onDismissAddAccount()
    }


    private var editTextAccount: TextWatcher = object : TextWatcher {

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            presenter.editTextAccountNumberFormat(this)
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun afterTextChanged(editText: Editable) {
        }
    }

    companion object {
        var TAG = "AddAccountFragment"
    }
}

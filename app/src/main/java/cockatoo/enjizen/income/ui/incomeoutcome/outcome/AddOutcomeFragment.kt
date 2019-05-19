package cockatoo.enjizen.income.ui.incomeoutcome.outcome


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.adapter.spinner.AccountAdapter
import cockatoo.enjizen.income.base.BaseFragment
import cockatoo.enjizen.income.model.Account
import com.valdesekamdem.library.mdtoast.MDToast
import kotlinx.android.synthetic.main.fragment_add_outcome.*
import kotlinx.android.synthetic.main.fragment_add_outcome.btnSave
import kotlinx.android.synthetic.main.fragment_add_outcome.editTextDetail
import kotlinx.android.synthetic.main.fragment_add_outcome.textViewLabelDate
import kotlinx.android.synthetic.main.fragment_add_outcome.spinnerAccount
import kotlinx.android.synthetic.main.spinner.view.*


class AddOutcomeFragment : BaseFragment(), AddOutcomePresenter.AddOutcomeView {

    private lateinit var presenter: AddOutcomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = AddOutcomePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_outcome, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarListener(addOutcomeToolbar)

        presenter.setDateOutcome()
        presenter.getAccount()

        btnSave.setOnClickListener {
            presenter.saveOutcome()
        }
    }

    override fun getDetail(): String {
        return editTextDetail.getText()
    }

    override fun getMoneyOutcome(): String {
        return editTextMoneyOutcome.getText()
    }

    override fun displayAccount(accounts: ArrayList<Account>) {
        spinnerAccount.spinner.adapter =  AccountAdapter(accounts)
    }

    override fun displayCurrentDate(dateValue: String) {
        textViewLabelDate.text = dateValue
    }

    override fun getAccountSpinnerPosition(): Int {
        return spinnerAccount.spinner.selectedItemPosition
    }

    override fun saveSuccess() {
        MDToast.makeText(
            context,
            getString(R.string.finish),
            Toast.LENGTH_SHORT,
            MDToast.TYPE_SUCCESS
        ).show()
    }

    companion object {
        fun newInstance() = AddOutcomeFragment()
    }


}

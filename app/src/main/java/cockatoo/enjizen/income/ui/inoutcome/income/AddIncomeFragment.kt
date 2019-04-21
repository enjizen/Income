package cockatoo.enjizen.income.ui.inoutcome.income


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.model.Account
import cockatoo.enjizen.income.ui.adapter.spinner.AccountAdapter
import cockatoo.enjizen.income.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_add_income.*
import kotlinx.android.synthetic.main.spinner.view.*

class AddIncomeFragment : BaseFragment(), AddIncomeView {

    private lateinit var presenter: AddIncomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        presenter = AddIncomePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_add_income, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarListener(addIncomeToolbar)
        presenter.getAccount()
    }

    override fun displayAccount(accounts: ArrayList<Account>) {
        val adapter = AccountAdapter(accounts)
        spinnerAccount.spinner.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddIncomeFragment()
    }


}

package cockatoo.enjizen.income.ui.inoutcome.income


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.ui.account.account.AccountPresenter
import cockatoo.enjizen.income.ui.account.account.AccountView
import cockatoo.enjizen.income.ui.base.BaseFragment
import cockatoo.enjizen.income.ui.service.AccountService
import cockatoo.enjizen.income.ui.service.BankService
import cockatoo.enjizen.income.ui.service.IncomeService
import kotlinx.android.synthetic.main.fragment_add_income.*

class AddIncomeFragment : BaseFragment(), AddIncomeView {

    private lateinit var presenter: AddIncomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



       activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        presenter = AddIncomePresenter(this, IncomeService())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_add_income, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarListener(addIncomeToolbar)


    }

    companion object{
        @JvmStatic
        fun newInstance() = AddIncomeFragment()
    }


}

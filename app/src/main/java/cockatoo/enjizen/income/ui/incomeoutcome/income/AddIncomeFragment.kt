package cockatoo.enjizen.income.ui.incomeoutcome.income


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.model.Account
import cockatoo.enjizen.income.adapter.spinner.AccountAdapter
import cockatoo.enjizen.income.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_add_income.*
import kotlinx.android.synthetic.main.spinner.view.*
import java.util.*
import kotlin.collections.ArrayList
import android.app.DatePickerDialog
import android.widget.Toast
import com.valdesekamdem.library.mdtoast.MDToast


class AddIncomeFragment : BaseFragment(), AddIncomePresenter.AddIncomeView , View.OnClickListener{

    private var mDay: Int = 0
    private var mMonth: Int = 0
    private var mYear: Int = 0

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
        presenter.setDateIncome()
        presenter.getAccount()

        imageViewCalendar.setOnClickListener (this)
        btnSave.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.imageViewCalendar -> {
                Calendar.getInstance().let {
                    mYear = it.get(Calendar.YEAR)
                    mMonth = it.get(Calendar.MONTH)
                    mDay = it.get(Calendar.DAY_OF_MONTH)
                }

                DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener
                { _, year, monthOfYear, dayOfMonth ->
                    Calendar.getInstance().apply {
                        set(year, monthOfYear, dayOfMonth)
                    }.run {
                        presenter.setDateIncome(time)
                    }
                },
                    mYear,
                    mMonth,
                    mDay
                ).run {
                    show()
                }
            }
            R.id.btnSave -> {
                presenter.saveIncome()
            }
        }
    }

    override fun displayAccount(accounts: ArrayList<Account>) {
        spinnerAccount.spinner.adapter = AccountAdapter(accounts)
    }

    override fun displayCurrentDate(dateValue: String) {
        textViewLabelDate.text = dateValue
    }

    override fun getAccountSpinnerPosition(): Int {
        return spinnerAccount.spinner.selectedItemPosition
    }

    override fun getDetail(): String {
        return editTextDetail.getText()
    }

    override fun getMoneyIncome(): String {
        return editTextMoneyIncome.getText()
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
        fun newInstance() = AddIncomeFragment()
    }


}

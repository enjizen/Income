package cockatoo.enjizen.income.ui.inoutcome.income


import android.annotation.SuppressLint
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
import java.util.*
import kotlin.collections.ArrayList
import android.app.DatePickerDialog
import android.widget.DatePicker


class AddIncomeFragment : BaseFragment(), AddIncomeView , View.OnClickListener{

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
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.imageViewCalendar -> {
                val c = Calendar.getInstance()
                mYear = c.get(Calendar.YEAR)
                mMonth = c.get(Calendar.MONTH)
                mDay = c.get(Calendar.DAY_OF_MONTH)


                val datePickerDialog = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    labelDate.text = "$dayOfMonth-${(monthOfYear + 1)}-$year" },
                    mYear,
                    mMonth,
                    mDay
                )
                datePickerDialog.show()
            }
        }
    }

   fun getFromDatePicker(datePicker: DatePicker){

   }

    override fun displayAccount(accounts: ArrayList<Account>) {
        val adapter = AccountAdapter(accounts)
        spinnerAccount.spinner.adapter = adapter
    }

    override fun displayCurrentDate(dateValue: String) {
        labelDate.text = dateValue
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddIncomeFragment()
    }


}

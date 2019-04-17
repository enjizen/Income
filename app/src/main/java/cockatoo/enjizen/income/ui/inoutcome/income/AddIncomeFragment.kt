package cockatoo.enjizen.income.ui.inoutcome.income


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_add_income.*

class AddIncomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_income, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarListener(addIncomeToolbar)
    }

    companion object{
        @JvmStatic
        fun newInstance() = AddIncomeFragment()
    }


}

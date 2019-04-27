package cockatoo.enjizen.income.ui.inoutcome

import android.os.Bundle
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.ui.base.BaseActivity
import cockatoo.enjizen.income.ui.inoutcome.income.AddIncomeFragment

class InOutComeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_out_come)


        supportFragmentManager.beginTransaction().add(R.id.contentContainer, AddIncomeFragment.newInstance()).commit()
    }
}

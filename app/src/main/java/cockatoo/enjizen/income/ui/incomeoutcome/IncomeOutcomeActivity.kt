package cockatoo.enjizen.income.ui.incomeoutcome

import android.os.Bundle
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.base.BaseActivity
import cockatoo.enjizen.income.constant.IncomeOutcomeMode
import cockatoo.enjizen.income.constant.IntentKey
import cockatoo.enjizen.income.ui.incomeoutcome.income.AddIncomeFragment
import cockatoo.enjizen.income.ui.incomeoutcome.outcome.AddOutcomeFragment

class IncomeOutcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_outcome)

        if(savedInstanceState == null) {
            val mode = intent.getStringExtra(IntentKey.INCOME_OUTCOME_MODE.value)

            when(mode){
                IncomeOutcomeMode.ADD_INCOME.value -> supportFragmentManager.beginTransaction().add(R.id.contentContainer, AddIncomeFragment.newInstance()).commit()
                IncomeOutcomeMode.ADD_OUTCOME.value -> supportFragmentManager.beginTransaction().add(R.id.contentContainer, AddOutcomeFragment.newInstance()).commit()
            }
        }
    }
}

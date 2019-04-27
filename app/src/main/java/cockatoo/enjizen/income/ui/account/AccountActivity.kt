package cockatoo.enjizen.income.ui.account

import android.os.Bundle
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.ui.account.account.AccountFragment
import cockatoo.enjizen.income.base.BaseActivity

class AccountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        supportFragmentManager.beginTransaction()
            .add(R.id.contentContainer,
                AccountFragment.newInstance()
            )
            .commit()
    }
}

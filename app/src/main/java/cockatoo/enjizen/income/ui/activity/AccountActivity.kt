package cockatoo.enjizen.income.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.ui.fragment.AccountFragment

class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        supportFragmentManager.beginTransaction()
            .add(R.id.contentContainer, AccountFragment.newInstance())
            .commit()
    }
}

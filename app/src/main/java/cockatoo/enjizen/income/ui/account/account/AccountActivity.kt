package cockatoo.enjizen.income.ui.account.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cockatoo.enjizen.income.R

class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        supportFragmentManager.beginTransaction()
            .add(R.id.contentContainer, AccountFragment.newInstance())
            .commit()
    }
}

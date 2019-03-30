package cockatoo.enjizen.income.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.ui.account.account.AccountActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, AccountActivity::class.java))
    }



}

package cockatoo.enjizen.income.ui.channel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cockatoo.enjizen.income.constant.IntentPasswordMode
import cockatoo.enjizen.income.constant.PasswordMode
import cockatoo.enjizen.income.ui.main.MainActivity
import cockatoo.enjizen.income.ui.password.PasswordActivity

class VerifyChannelActivity : AppCompatActivity(), VerifyChannelView {

    private lateinit var presenter: VerifyChannelPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = VerifyChannelPresenter(this)

        presenter.checkSetupPassworded()

    }


    override fun haveNotSetPassword() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    override fun passwordAlreadySet() {
        val intent = Intent(this, PasswordActivity::class.java)
        intent.putExtra(IntentPasswordMode.MODE.value, PasswordMode.AUTHENTICATION.value)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

}
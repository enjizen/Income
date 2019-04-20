package cockatoo.enjizen.income.ui.channel

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import cockatoo.enjizen.income.constant.IntentKey
import cockatoo.enjizen.income.constant.PasswordMode
import cockatoo.enjizen.income.ui.main.MainActivity
import cockatoo.enjizen.income.ui.password.PasswordActivity

class VerifyChannelActivity : AppCompatActivity(), VerifyChannelView {

    private lateinit var presenter: VerifyChannelPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
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
        intent.putExtra(IntentKey.PASSWORD_MODE.value, PasswordMode.AUTHENTICATION.value)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

}
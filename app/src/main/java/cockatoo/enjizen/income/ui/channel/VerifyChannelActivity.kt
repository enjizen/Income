package cockatoo.enjizen.income.ui.channel

import android.content.Intent
import android.os.Bundle
import cockatoo.enjizen.income.constant.IntentKey
import cockatoo.enjizen.income.constant.PasswordMode
import cockatoo.enjizen.income.base.BaseActivity
import cockatoo.enjizen.income.base.BaseRouterActivity
import cockatoo.enjizen.income.constant.TransitionScreenType
import cockatoo.enjizen.income.ui.main.MainActivity
import cockatoo.enjizen.income.ui.password.PasswordActivity

class VerifyChannelActivity : BaseActivity(), VerifyChannelPresenter.VerifyChannelView {

    private lateinit var presenter: VerifyChannelPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = VerifyChannelPresenter(this)
        presenter.checkSetupPassword()
    }


    override fun haveNotSetPassword() {
        router.goto(activity = this, intent = Intent(this, MainActivity::class.java), tranSit = TransitionScreenType.PUSH, isCloseAllScreen = true)
    }

    override fun passwordAlreadySet() {
        Intent(this, PasswordActivity::class.java).apply {
            putExtra(IntentKey.PASSWORD_MODE.value, PasswordMode.AUTHENTICATION.value)
        }.also {
            router.goto(activity = this, intent = it, tranSit = TransitionScreenType.PUSH, isCloseAllScreen = true)
        }
    }

}
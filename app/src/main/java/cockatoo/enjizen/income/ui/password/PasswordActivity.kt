package cockatoo.enjizen.income.ui.password

import android.content.Intent
import android.os.Bundle
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.constant.IntentPasswordMode
import cockatoo.enjizen.income.constant.PasswordMode
import cockatoo.enjizen.income.ui.base.BaseActivity
import cockatoo.enjizen.income.ui.main.MainActivity
import cockatoo.enjizen.income.ui.password.authentication.AuthenticationFragment
import cockatoo.enjizen.income.ui.password.confirm.ConfirmPasswordFragment
import cockatoo.enjizen.income.ui.password.create.SetPasswordFragment

class PasswordActivity : BaseActivity(), SetPasswordFragment.SetPasswordListener, ConfirmPasswordFragment.ConfirmPasswordListener, AuthenticationFragment.AuthenticationListener {

    private lateinit var mode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        mode = intent.getStringExtra(IntentPasswordMode.MODE.value)

        when(mode) {
            PasswordMode.CREATE.value -> supportFragmentManager.beginTransaction().add(
                R.id.contentContainer,
                SetPasswordFragment.newInstance()
            ).commit()
            PasswordMode.AUTHENTICATION.value, PasswordMode.CHANGE.value -> supportFragmentManager.beginTransaction().add(
                R.id.contentContainer,
                AuthenticationFragment.newInstance(mode)
            ).commit()
        }

    }


    override fun onCreatePasswordSuccess(password: String) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.slide_from_left, R.anim.slide_from_right, R.anim.pop_enter, R.anim.pop_exit)
            .addToBackStack(null)
            .replace(R.id.contentContainer, ConfirmPasswordFragment.newInstance(password)).commit()
    }

    override fun onConfirmPasswordSuccess() {
        finish()
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave)
    }

    override fun onAuthenticationPasswordSuccess() {
        if(mode == PasswordMode.AUTHENTICATION.value ) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_from_right)
        } else {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_from_left, R.anim.slide_from_right, R.anim.pop_enter, R.anim.pop_exit)
                .addToBackStack(null)
                .add(R.id.contentContainer, SetPasswordFragment.newInstance()
            ).commit()
        }
    }
}

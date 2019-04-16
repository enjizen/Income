package cockatoo.enjizen.income.ui.password

import android.os.Bundle
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.constant.IntentPasswordMode
import cockatoo.enjizen.income.constant.PasswordMode
import cockatoo.enjizen.income.ui.base.BaseActivity
import cockatoo.enjizen.income.ui.password.confirm.ConfirmPasswordFragment
import cockatoo.enjizen.income.ui.password.create.SetPasswordFragment

class PasswordActivity : BaseActivity(), SetPasswordFragment.SetPasswordListener, ConfirmPasswordFragment.ConfirmPasswordListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)


        val mode = intent.getStringExtra(IntentPasswordMode.MODE.value)

        if(mode == PasswordMode.CREATE.value) {
            supportFragmentManager.beginTransaction()
                                    .add(R.id.contentContainer, SetPasswordFragment.newInstance())
                                    .commit()
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

    }
}

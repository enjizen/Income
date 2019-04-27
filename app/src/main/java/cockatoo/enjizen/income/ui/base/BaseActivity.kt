package cockatoo.enjizen.income.ui.base

import androidx.appcompat.app.AppCompatActivity
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.custom.view.Toolbar

open class BaseActivity : AppCompatActivity(), Toolbar.ToolbarListener {



    protected fun setToolbarListener(toolbar: Toolbar) {
       toolbar.setListener(this@BaseActivity)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.animation_enter,
            R.anim.animation_leave)

    }

    override fun onClose() {
        finish()
    }

    override fun onBack() {
        onBackPressed()
    }


}
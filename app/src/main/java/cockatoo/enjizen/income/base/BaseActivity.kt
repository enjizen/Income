package cockatoo.enjizen.income.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.custom.view.Toolbar
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

open class BaseActivity : AppCompatActivity(), Toolbar.ToolbarListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
            .setDefaultFontPath(getString(R.string.font_default))
            .setFontAttrId(R.attr.fontPath)
            .build()
        )
    }

    protected fun setToolbarListener(toolbar: Toolbar) {
       toolbar.setListener(this@BaseActivity)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.animation_enter,
            R.anim.animation_leave)

    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onClose() {
        finish()
    }

    override fun onBack() {
        onBackPressed()
    }


}
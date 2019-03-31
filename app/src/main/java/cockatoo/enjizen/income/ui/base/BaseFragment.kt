package cockatoo.enjizen.income.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kaopiz.kprogresshud.KProgressHUD

open class BaseFragment: Fragment() {

    private lateinit var progressDialog : KProgressHUD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = KProgressHUD.create(context)
    }

    protected fun showLoading() {
        progressDialog
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
            .show()
    }

    protected fun hideLoading() {
        progressDialog.dismiss()
    }

}
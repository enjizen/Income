package cockatoo.enjizen.income.ui.base

import androidx.fragment.app.DialogFragment
import cockatoo.enjizen.income.custom.view.Toolbar
import com.kaopiz.kprogresshud.KProgressHUD

open class BaseDialogFragment : DialogFragment(), Toolbar.ToolbarListener {

    private lateinit var progressDialog : KProgressHUD

    override fun onClose() {
        dismiss()
    }

    override fun onBack() {

    }

     fun setToolbarListener(toolbar: Toolbar) {
        toolbar.setListener(this)
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
package cockatoo.enjizen.income.ui.base

import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.WindowManager
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

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setBackgroundDrawable(
                ColorDrawable(
                    resources.getColor(
                        android.R.color.transparent,
                        context!!.theme
                    )
                )
            )
            dialog.window!!.setLayout(width, height)
            dialog.window!!.setWindowAnimations(cockatoo.enjizen.income.R.style.DialogAnimation)
            dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
    }
}
package cockatoo.enjizen.income.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import cockatoo.enjizen.income.custom.view.Toolbar
import com.kaopiz.kprogresshud.KProgressHUD

open class BaseFragment: Fragment() , Toolbar.ToolbarListener {


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

    fun setToolbarListener(toolbar: Toolbar, iconBtnRight: Int? = null) {
        toolbar.setListener(this)
        if(iconBtnRight != null){
            toolbar.showAndSetIconRight(iconBtnRight)
        }
    }


    override fun onClose() {
    }

    override fun onBack() {
        activity!!.onBackPressed()
    }

}
package cockatoo.enjizen.income.ui.base

import androidx.fragment.app.DialogFragment
import cockatoo.enjizen.income.custom.view.Toolbar

open class BaseDialogFragment : DialogFragment(), Toolbar.ToolbarListener {



    override fun onClose() {
        dismiss()
    }

    override fun onBack() {

    }

     fun setToolbarListener(toolbar: Toolbar) {
        toolbar.setListener(this)
    }
}
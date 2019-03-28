package cockatoo.enjizen.income.ui.fragment


import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment

import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.model.Account
import cockatoo.enjizen.income.ui.presenter.AddAccountPresenter
import cockatoo.enjizen.income.ui.router.AddAccountRouter
import cockatoo.enjizen.income.ui.service.AccountService
import cockatoo.enjizen.income.ui.view.AddAccountView
import com.valdesekamdem.library.mdtoast.MDToast
import kotlinx.android.synthetic.main.fragment_add_acount.*

class AddAccountFragment : BaseDialogFragment(), AddAccountView{


    private lateinit var presenter: AddAccountPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)

        presenter = AddAccountPresenter(this, AccountService(), AddAccountRouter(activity = activity!!))

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_acount, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarListener(toolBar)

        btnSave.setOnClickListener {

            presenter.addAccount(etAccountNumber.getText().toString()
                                , etAccountName.getText().toString()
                                , etAccountBalance.getText().toString().toDouble())
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setBackgroundDrawable( ColorDrawable(resources.getColor(android.R.color.transparent, context!!.theme)))
            dialog.window!!.setLayout(width, height)
        }
    }


    override fun onAddAccountSuccess(accountNumber: String) {
        MDToast.makeText(context,  "${getString(R.string.message_account_number)} $accountNumber ${getString(R.string.finish)}", Toast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show()
        dismiss()
    }

    override fun onShowLoading() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onHideLoading() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        var TAG = "AddAccountFragment"
    }


}

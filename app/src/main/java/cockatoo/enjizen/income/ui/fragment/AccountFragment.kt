package cockatoo.enjizen.income.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.constant.DBContract
import cockatoo.enjizen.income.manger.KeyEncryptData
import cockatoo.enjizen.income.manger.db.DBHelper
import kotlinx.android.synthetic.main.fragment_account.*


class AccountFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // addAccount()
     // showAccount()

        addAccount.setOnClickListener {
            val dialog = AddAccountFragment()
            val ft = fragmentManager!!.beginTransaction()
            dialog.show(ft, AddAccountFragment.TAG)
        }

    }

    private fun addAccount() {
       // AccountTableHelper.insertAccount(accountNumber = "1111111111", name = "ทดสอบ2", balance = 400000.00)
    }


    private fun showAccount(){
       val dbHelper = DBHelper.getInstance()
        val db = dbHelper.getWritableDatabase(KeyEncryptData.getInstance().key)
        val stmt = db.compileStatement("SELECT * FROM ${DBContract.AccountEntry.TABLE_NAME} WHERE id = ?")
        stmt.bindLong(1, 1)
        stmt.execute()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            AccountFragment()
    }
}

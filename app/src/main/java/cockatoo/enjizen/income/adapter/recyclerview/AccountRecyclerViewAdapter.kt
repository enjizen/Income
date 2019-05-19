package cockatoo.enjizen.income.adapter.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.extension.numberAccountBalanceFormat
import cockatoo.enjizen.income.manger.Contextor
import cockatoo.enjizen.income.model.Account
import kotlinx.android.synthetic.main.item_account_list.view.*

class AccountRecyclerViewAdapter(private val accountList: ArrayList<Account>) : RecyclerView.Adapter<AccountRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_account_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val account =  accountList[position]
        with(holder){
            accountNumber.text = account.accountNumber
            accountName.text = account.name
            accountBalance.text = account.balance.numberAccountBalanceFormat()
            logoBank.setImageResource(Contextor.getInstance().context!!.resources.getIdentifier(account.logo, "drawable", Contextor.getInstance().context!!.packageName))
        }


    }

    override fun getItemCount(): Int = accountList.size

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val accountNumber = view.accountNumber!!
        val accountName = view.accountName!!
        val accountBalance = view.totalBalance!!
        val logoBank = view.imageViewLogoBank!!
    }

}
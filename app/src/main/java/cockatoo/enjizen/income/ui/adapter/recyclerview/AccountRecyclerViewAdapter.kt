package cockatoo.enjizen.income.ui.adapter.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.extension.numberAccountBalanceFormat
import cockatoo.enjizen.income.model.Account
import kotlinx.android.synthetic.main.item_account_list.view.*

class AccountRecyclerViewAdapter(private val accountList: ArrayList<Account>) : RecyclerView.Adapter<AccountRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_account_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.accountNumber.text = accountList[position].accountNumber
        holder.accountName.text = accountList[position].name
        holder.accountBalance.text = accountList[position].balance.numberAccountBalanceFormat()
    }

    override fun getItemCount(): Int = accountList.size

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val accountNumber = view.accountNumber!!
        val accountName = view.accountName!!
        val accountBalance = view.totalBalance!!

    }

}
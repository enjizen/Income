package cockatoo.enjizen.income.adapter.spinner

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cockatoo.enjizen.income.model.Account
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.extension.numberAccountBalanceFormat

class AccountAdapter(private val accountList: ArrayList<Account>) : BaseAdapter() {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mInflater = LayoutInflater.from(parent!!.context)
        val item = mInflater.inflate(R.layout.item_account_list, parent, false)
        val itemAccount = item.findViewById(R.id.itemAccount) as RelativeLayout
        val imageViewLogoBank = item.findViewById<AppCompatImageView>(R.id.imageViewLogoBank)
        val accountName = item.findViewById<AppCompatTextView>(R.id.accountName)
        val accountNumber = item.findViewById<AppCompatTextView>(R.id.accountNumber)
        val totalBalance = item.findViewById<AppCompatTextView>(R.id.totalBalance)
        itemAccount.background = null
        accountList[position].let{
            imageViewLogoBank.setImageResource(
                parent.context.resources.getIdentifier(
                    it.logo,
                    "drawable",
                    parent.context.packageName
                )
            )
            accountName.text = it.name
            accountNumber.text = it.accountNumber
            totalBalance.text = it.balance.numberAccountBalanceFormat()
        }

        return item
    }

    override fun getItem(position: Int): Any {
       return accountList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
       return accountList.size
    }


}
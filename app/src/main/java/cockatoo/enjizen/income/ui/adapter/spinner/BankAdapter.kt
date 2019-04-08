package cockatoo.enjizen.income.ui.adapter.spinner

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.model.Bank



class BankAdapter(private val context: Context, private val bankList: ArrayList<Bank>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mInflater: LayoutInflater = LayoutInflater.from(context)
        val item = mInflater.inflate(R.layout.item_account_sprinner_adapter, parent, false)

        val imageLogo = item.findViewById<AppCompatImageView>(R.id.imageLogo)
        val textViewName = item.findViewById<AppCompatTextView>(R.id.textViewName)
        val bank = bankList[position]
        imageLogo.setImageResource(context.resources.getIdentifier(bank.logo, "drawable", context.packageName))
        textViewName.text = bank.name
        return item
    }

    override fun getItem(position: Int): Any {
       return bankList[position]
    }

    override fun getItemId(position: Int): Long {
      return position.toLong()
    }

    override fun getCount(): Int {
       return bankList.size
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getView(position, convertView, parent)
    }

}
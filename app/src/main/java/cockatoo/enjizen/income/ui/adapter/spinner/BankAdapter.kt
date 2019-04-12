package cockatoo.enjizen.income.ui.adapter.spinner

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.model.Bank



class BankAdapter(private val bankList: ArrayList<Bank>) : BaseAdapter() {
    
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mInflater: LayoutInflater = LayoutInflater.from(parent!!.context)
        val item = mInflater.inflate(R.layout.item_sprinner_adapter, parent, false)

        val imageLogo = item.findViewById(R.id.imageLogo) as AppCompatImageView
        val textViewName = item.findViewById(R.id.textViewName) as AppCompatTextView
        val bank = bankList[position]
        imageLogo.setImageResource(parent.context.resources.getIdentifier(bank.logo, "drawable", parent.context.packageName))
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
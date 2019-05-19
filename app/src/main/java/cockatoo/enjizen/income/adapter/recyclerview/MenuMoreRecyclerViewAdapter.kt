package cockatoo.enjizen.income.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.constant.MoreMenuType
import cockatoo.enjizen.income.model.MoreMenu
import cockatoo.enjizen.income.adapter.recyclerview.holder.MoreMenuGroupHolder
import cockatoo.enjizen.income.adapter.recyclerview.holder.MoreMenuItemHolder
import cockatoo.enjizen.income.adapter.recyclerview.holder.MoreMenuLineHolder

class MenuMoreRecyclerViewAdapter(private val moreMenuList : ArrayList<MoreMenu>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return  when(viewType){
            MoreMenuType.GROUP.type -> MoreMenuGroupHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.view_more_menu_group, parent, false)
            )
           MoreMenuType.ITEM.type -> MoreMenuItemHolder(
               LayoutInflater.from(parent.context).inflate(R.layout.view_more_menu_item, parent, false)
           )
           else -> MoreMenuLineHolder(
               LayoutInflater.from(parent.context).inflate(
                   R.layout.view_more_menu_line,
                   parent,
                   false
               )
           )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val moreMenu =  moreMenuList[position]

        when(moreMenu.menuType){
            MoreMenuType.GROUP.type -> {
                val groupHolder = holder as MoreMenuGroupHolder
                groupHolder.textViewGroupMenu.text = moreMenu.menuName
            }
            MoreMenuType.ITEM.type -> {
                val itemHolder = holder as MoreMenuItemHolder
                if(moreMenu.imageIcon != 0) {
                    itemHolder.imageViewIcon.setImageResource(moreMenu.imageIcon)
                }
                itemHolder.textViewItemMenu.text = moreMenu.menuName
            }
        }
    }

    override fun getItemCount(): Int {
        return moreMenuList.size
    }

    override fun getItemViewType(position: Int): Int {
        return moreMenuList[position].menuType
    }


}
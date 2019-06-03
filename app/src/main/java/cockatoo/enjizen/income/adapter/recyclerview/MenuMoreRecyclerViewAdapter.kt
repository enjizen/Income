package cockatoo.enjizen.income.adapter.recyclerview

import android.util.Log
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
        moreMenuList[position].let {
            when(it.menuType){
                MoreMenuType.GROUP.type -> {
                    (holder as MoreMenuGroupHolder).textViewGroupMenu.text = it.menuName
                }
                MoreMenuType.ITEM.type -> {
                        (holder as MoreMenuItemHolder).apply {
                            imageViewIcon.setImageResource(it.imageIcon)
                            textViewItemMenu.text = it.menuName
                    }
                }
                else -> Log.i(MenuMoreRecyclerViewAdapter::class.java.simpleName, "Line size box menu")
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
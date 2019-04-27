package cockatoo.enjizen.income.model

import cockatoo.enjizen.income.constant.MoreMenuTarget

data class MoreMenu (
    val imageIcon: Int = 0,
    val menuName: String = "",
    val menuType: Int,
    val target: MoreMenuTarget = MoreMenuTarget.LOGOUT
)
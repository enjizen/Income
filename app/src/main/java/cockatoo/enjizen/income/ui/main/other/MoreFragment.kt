package cockatoo.enjizen.income.ui.main.other


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.constant.*
import cockatoo.enjizen.income.manger.RecyclerTouchListener
import cockatoo.enjizen.income.manger.SharedPreferenceSecureUtil
import cockatoo.enjizen.income.model.MoreMenu
import cockatoo.enjizen.income.ui.account.AccountActivity
import cockatoo.enjizen.income.adapter.recyclerview.MenuMoreRecyclerViewAdapter
import cockatoo.enjizen.income.base.BaseFragment
import cockatoo.enjizen.income.ui.password.PasswordActivity
import cockatoo.enjizen.income.base.BaseRouterActivity
import kotlinx.android.synthetic.main.fragment_more.*

class MoreFragment : BaseFragment(), MorePresenter.MoreView {

    private lateinit var presenter: MorePresenter
    private lateinit var router: BaseRouterActivity
    private val moreMenuList = arrayListOf<MoreMenu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MorePresenter(this)

        router = BaseRouterActivity()
        presenter.checkSetupPassword()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayMenu()
    }

    private fun displayMenu() {
        val adapter = MenuMoreRecyclerViewAdapter(moreMenuList)
        val mLayoutManager = LinearLayoutManager(context)
        recyclerViewMenu.layoutManager = mLayoutManager
        recyclerViewMenu.adapter = adapter

        recyclerViewMenu.addOnItemTouchListener(
            RecyclerTouchListener(
                context!!,
                recyclerViewMenu,
                object : RecyclerTouchListener.ClickListener {
                    override fun onClick(view: View, position: Int) {
                        presenter.verifyMenuItem(moreMenuList[position])
                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }

                })
        )
    }

    override fun gotoAccount() {
        val intent = Intent(context, AccountActivity::class.java)
        router.goto(activity = activity!!, intent = intent, tranSit = TransitionScreenType.PUSH)
    }

    override fun gotoSetPassword() {
        val intent = Intent(context, PasswordActivity::class.java)
        intent.putExtra(IntentKey.PASSWORD_MODE.value, PasswordMode.CREATE.value)
        router.goto(activity = activity!!, intent = intent, tranSit = TransitionScreenType.PUSH)
    }

    override fun gotoChangePassword() {
        val intent = Intent(context, PasswordActivity::class.java)
        intent.putExtra(IntentKey.PASSWORD_MODE.value, PasswordMode.CHANGE.value)
        router.goto(activity = activity!!, intent = intent, tranSit = TransitionScreenType.PUSH)
    }

    override fun gotoLogout() {
        val intent = Intent(context, PasswordActivity::class.java)
        intent.putExtra(IntentKey.PASSWORD_MODE.value, PasswordMode.AUTHENTICATION.value)
        router.goto(activity = activity!!, intent = intent, tranSit = TransitionScreenType.LEAVE, isCloseAllScreen = true)
    }

    override fun haveNotSetPassword() {
        initMenu()
        moreMenuList.forEach {
            if(it.menuName == getString(R.string.change_password)){
                moreMenuList.remove(it)
                return
            }
        }
    }

    override fun passwordAlreadySet() {
        initMenu()
        moreMenuList.forEach {
            if(it.menuName == getString(R.string.set_password)){
                moreMenuList.remove(it)
                return
            }
        }

    }

    override fun getPasswordForCheck() : String {
        return SharedPreferenceSecureUtil.getString(KeyConstant.PASSWORD.value)!!
    }

    private fun initMenu() {
        moreMenuList.add(MoreMenu(menuName = getString(R.string.account), menuType = MoreMenuType.GROUP.type))
        moreMenuList.add(
            MoreMenu(
                menuName = getString(R.string.account),
                imageIcon = R.drawable.ic_account,
                menuType = MoreMenuType.ITEM.type,
                target = MoreMenuTarget.ACCOUNT
            )
        )
        moreMenuList.add(MoreMenu(menuType = MoreMenuType.LINE.type))
        moreMenuList.add(MoreMenu(menuName = getString(R.string.security_system), menuType = MoreMenuType.GROUP.type))
        moreMenuList.add(
            MoreMenu(
                menuName = getString(R.string.set_password),
                imageIcon = R.drawable.ic_create_password,
                menuType = MoreMenuType.ITEM.type,
                target = MoreMenuTarget.SET_PASSWORD
            )
        )
        moreMenuList.add(
            MoreMenu(
                menuName = getString(R.string.change_password),
                imageIcon = R.drawable.ic_change_password,
                menuType = MoreMenuType.ITEM.type,
                target = MoreMenuTarget.CHANGE_PASSWORD
            )
        )
        moreMenuList.add(
            MoreMenu(
                menuName = getString(R.string.logout),
                imageIcon = R.drawable.ic_logout,
                menuType = MoreMenuType.ITEM.type,
                target = MoreMenuTarget.LOGOUT
            )
        )
    }

    companion object {

        const val TAG = "MoreFragment"

        fun newInstance() = MoreFragment()
    }
}

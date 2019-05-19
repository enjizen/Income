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
import cockatoo.enjizen.income.util.SharedPreferenceSecureUtil
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
    ): View? =
        inflater.inflate(R.layout.fragment_more, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayMenu()
    }

    override fun onResume() {
        super.onResume()
        presenter.checkSetupPassword()
    }

    private fun displayMenu() {
        val moreMenuAdapter = MenuMoreRecyclerViewAdapter(moreMenuList)

        recyclerViewMenu.apply {
            layoutManager =  LinearLayoutManager(context)
            adapter = moreMenuAdapter
            recyclerViewMenu.addOnItemTouchListener(
                RecyclerTouchListener(
                    context!!,
                    this,
                    object : RecyclerTouchListener.ClickListener {
                        override fun onClick(view: View, position: Int) {
                            presenter.verifyMenuItem(moreMenuList[position])
                        }

                        override fun onLongClick(view: View?, position: Int) {

                        }

                    })
            )
        }



    }

    override fun gotoAccount() {
        router.goto(activity = activity!!, intent = Intent(context, AccountActivity::class.java), tranSit = TransitionScreenType.PUSH)
    }

    override fun gotoSetPassword() {
        Intent(context, PasswordActivity::class.java).apply {
            putExtra(IntentKey.PASSWORD_MODE.value, PasswordMode.CREATE.value)
        }.also {
            router.goto(activity = activity!!, intent = it, tranSit = TransitionScreenType.PUSH)
        }
    }

    override fun gotoChangePassword() {
        Intent(context, PasswordActivity::class.java).apply {
            putExtra(IntentKey.PASSWORD_MODE.value, PasswordMode.CHANGE.value)
        }.also {
            router.goto(activity = activity!!, intent = it, tranSit = TransitionScreenType.PUSH)
        }
    }

    override fun gotoLogout() {
        Intent(context, PasswordActivity::class.java).apply {
            putExtra(IntentKey.PASSWORD_MODE.value, PasswordMode.AUTHENTICATION.value)
        }.also {
            router.goto(activity = activity!!, intent = it, tranSit = TransitionScreenType.LEAVE, isCloseAllScreen = true)
        }
    }

    override fun haveNotSetPassword() {
        initMenu()
        moreMenuList.firstOrNull{it.menuName == getString(R.string.change_password)}.let {
            moreMenuList.remove(it)
        }
    }

    override fun passwordAlreadySet() {
        initMenu()
        moreMenuList.firstOrNull{it.menuName == getString(R.string.set_password)}.let {
            moreMenuList.remove(it)
        }
    }

    override fun getPasswordForCheck() : String? {
        SharedPreferenceSecureUtil.getString(KeyConstant.PASSWORD.value)?.let {
            return it
        }
        return null
    }

    private fun initMenu() {
        moreMenuList.apply {
            clear()
            add(MoreMenu(menuName = getString(R.string.account), menuType = MoreMenuType.GROUP.type))
            add(
                MoreMenu(
                    menuName = getString(R.string.account),
                    imageIcon = R.drawable.ic_account,
                    menuType = MoreMenuType.ITEM.type,
                    target = MoreMenuTarget.ACCOUNT
                )
            )
            add(MoreMenu(menuType = MoreMenuType.LINE.type))
            add(MoreMenu(menuName = getString(R.string.security_system), menuType = MoreMenuType.GROUP.type))
            add(
                MoreMenu(
                    menuName = getString(R.string.set_password),
                    imageIcon = R.drawable.ic_create_password,
                    menuType = MoreMenuType.ITEM.type,
                    target = MoreMenuTarget.SET_PASSWORD
                )
            )
            add(
                MoreMenu(
                    menuName = getString(R.string.change_password),
                    imageIcon = R.drawable.ic_change_password,
                    menuType = MoreMenuType.ITEM.type,
                    target = MoreMenuTarget.CHANGE_PASSWORD
                )
            )
            add(
                MoreMenu(
                    menuName = getString(R.string.logout),
                    imageIcon = R.drawable.ic_logout,
                    menuType = MoreMenuType.ITEM.type,
                    target = MoreMenuTarget.LOGOUT
                )
            )
        }

    }

    companion object {

        const val TAG = "MoreFragment"

        fun newInstance() = MoreFragment()
    }
}

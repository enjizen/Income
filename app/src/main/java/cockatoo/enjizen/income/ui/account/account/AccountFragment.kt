package cockatoo.enjizen.income.ui.account.account

import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager

import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.manger.RecyclerTouchListener
import cockatoo.enjizen.income.model.Account
import cockatoo.enjizen.income.ui.adapter.recyclerview.AccountRecyclerViewAdapter
import cockatoo.enjizen.income.ui.account.add.AddAccountFragment
import cockatoo.enjizen.income.ui.base.BaseFragment
import cockatoo.enjizen.income.ui.service.AccountService
import kotlinx.android.synthetic.main.fragment_account.*
import android.view.*
import cockatoo.enjizen.income.ui.service.BankService
import kotlinx.android.synthetic.main.fragment_account.toolBar
import kotlinx.android.synthetic.main.item_toolbar.*


class AccountFragment : BaseFragment(), AddAccountFragment.AddAccountListener, AccountView {

    private val accounts = ArrayList<Account>()
    private lateinit var accountRecyclerViewAdapter: AccountRecyclerViewAdapter
    private lateinit var presenter: AccountPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = AccountPresenter(this, AccountService(), BankService())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_account, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarListener(toolBar, R.drawable.ic_add_white_24dp)


        setAccountRecycleViewAdapter()
        presenter.getAllAccount()

        btnRight.setOnClickListener {
            presenter.addAccount()
        }

      /*  addAccount.setOnClickListener {
            presenter.addAccount()
        }*/

    }

    private fun setAccountRecycleViewAdapter() {
        accountRecyclerView.layoutManager = LinearLayoutManager(context)
        accountRecyclerView.itemAnimator = DefaultItemAnimator()
        accountRecyclerViewAdapter = AccountRecyclerViewAdapter(accounts)
        accountRecyclerView.adapter = accountRecyclerViewAdapter
        accountRecyclerView.addOnItemTouchListener(
            RecyclerTouchListener(
                context!!,
                accountRecyclerView,
                object : RecyclerTouchListener.ClickListener {
                    override fun onClick(view: View, position: Int) {
                        presenter.get(position)
                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }
                })
        )

    }

    override fun onDismissAddAccount() {
        presenter.getAllAccount()
    }

    override fun setDataAccount(accounts: ArrayList<Account>) {
        this.accounts.clear()
        this.accounts.addAll(accounts)
        accountRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onShowLoading() {
        showLoading()
    }

    override fun onHideLoading() {
        hideLoading()
    }

    override fun getAccountId(position: Int): Int = accounts[position].id

    override fun goToAdd() {
        val dialog = AddAccountFragment()
        val ft = fragmentManager!!.beginTransaction()
        dialog.setListener(this)
        dialog.show(ft, AddAccountFragment.TAG)
    }

    override fun goToView(id: Int?) {

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            AccountFragment()
    }
}

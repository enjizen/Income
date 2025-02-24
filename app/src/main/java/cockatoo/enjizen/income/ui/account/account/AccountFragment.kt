package cockatoo.enjizen.income.ui.account.account

import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager

import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.manger.RecyclerTouchListener
import cockatoo.enjizen.income.model.Account
import cockatoo.enjizen.income.adapter.recyclerview.AccountRecyclerViewAdapter
import cockatoo.enjizen.income.ui.account.add.AddAccountFragment
import cockatoo.enjizen.income.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_account.*
import android.view.*


class AccountFragment : BaseFragment(), AddAccountFragment.AddAccountListener, AccountPresenter.AccountView {

    private val accounts = ArrayList<Account>()
    private lateinit var accountRecyclerViewAdapter: AccountRecyclerViewAdapter
    private lateinit var presenter: AccountPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = AccountPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_account, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarListener(accountToolBar)

        setAccountRecycleViewAdapter()
        presenter.getAllAccount()


        buttonAddAccount.setOnClickListener {
            presenter.addAccount()
        }

    }

    private fun setAccountRecycleViewAdapter() {
        accountRecyclerViewAdapter = AccountRecyclerViewAdapter(accounts)
        accountRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = accountRecyclerViewAdapter
            addOnItemTouchListener(
                RecyclerTouchListener(
                    context!!,
                    this,
                    object : RecyclerTouchListener.ClickListener {
                        override fun onClick(view: View, position: Int) {
                            presenter.get(position)
                        }

                        override fun onLongClick(view: View?, position: Int) {

                        }
                    })
            )

        }
    }

    override fun onDismissAddAccount() {
        presenter.getAllAccount()
    }

    override fun displayAccount(accounts: ArrayList<Account>) {
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
        fun newInstance() = AccountFragment()
    }
}

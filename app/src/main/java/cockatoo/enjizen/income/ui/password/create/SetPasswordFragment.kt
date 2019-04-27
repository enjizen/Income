package cockatoo.enjizen.income.ui.password.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.custom.edittext.Password
import cockatoo.enjizen.income.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_password.*
import kotlinx.android.synthetic.main.view_passcode.view.*
import kotlinx.android.synthetic.main.view_keyboard_password.*


class SetPasswordFragment : BaseFragment(), Password.PasswordListener , View.OnClickListener, SetPasswordView {

    private lateinit var listener: SetPasswordListener
    private lateinit var presenter: SetPasswordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listener = activity as SetPasswordListener
        presenter = SetPasswordPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarListener(passwordToolBar)

        passwordToolBar.setMessageTitle(getString(R.string.set_password))

        passwordPin.setListener(this)
        key1.setOnClickListener(this)
        key2.setOnClickListener(this)
        key3.setOnClickListener(this)
        key4.setOnClickListener(this)
        key5.setOnClickListener(this)
        key6.setOnClickListener(this)
        key7.setOnClickListener(this)
        key8.setOnClickListener(this)
        key9.setOnClickListener(this)
        key0.setOnClickListener(this)
        keyDel.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.key1, R.id.key2, R.id.key3, R.id.key4, R.id.key5, R.id.key6, R.id.key7, R.id.key8, R.id.key9 -> presenter.setPin(v.findViewById(v.id))
            R.id.keyDel -> presenter.deletePin()
        }
    }


    override fun onPasswordResult(password: String) {
        presenter.passwordSet()
    }

    override fun getEditTextPin(): String {
        return passwordPin.editTextPasscode.text.toString()
    }

    override fun displayPinPassword(pin: String) {
        passwordPin.editTextPasscode.setText(pin)
    }

    override fun clearPasswordEditText() {
        passwordPin.editTextPasscode.setText("")
    }

    override fun displayPinPasswordAll() {
        passwordPin.setKeyAllPin()
    }

    override fun sentConfirmPassword(password: String) {
      listener.onSetPasswordSuccess(password)
    }


    interface SetPasswordListener {
        fun onSetPasswordSuccess(password: String)
    }

    companion object{
       @JvmStatic
       fun newInstance() = SetPasswordFragment()
   }


}

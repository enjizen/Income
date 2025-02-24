package cockatoo.enjizen.income.ui.password.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.constant.PasswordMode
import cockatoo.enjizen.income.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_password.*
import kotlinx.android.synthetic.main.view_keyboard_password.*
import kotlinx.android.synthetic.main.view_passcode.view.*


class AuthenticationFragment : BaseFragment(), AuthenticationPresenter.AuthenticationView , View.OnClickListener {


    private lateinit var listener: AuthenticationListener
    private lateinit var presenter: AuthenticationPresenter

    private lateinit var mode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listener = activity as AuthenticationListener

        presenter = AuthenticationPresenter(this)

        mode = arguments!!.getString("mode")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_password, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarListener(passwordToolBar)

        if(mode == PasswordMode.AUTHENTICATION.value) {
            passwordToolBar.visibility = View.GONE
            activity!!. window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            mainLayoutPassword.fitsSystemWindows = false
        } else {
            passwordToolBar.setMessageTitle(getString(R.string.current_password))
        }

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


    override fun authenticationSuccess() {
        listener.onAuthenticationPasswordSuccess()
    }

    override fun authenticationFail() {
        passwordPin.setInvalidPin()
        passwordPin.showMessageError(getString(R.string.authentication_fail))
    }


    override fun onClick(v: View?) {
            presenter.inputPinAndVerifyPin(v!!)
    }

    override fun getEditTextPin(): String {
       return passwordPin.editTextPasscode.text.toString()
    }

    override fun displayPinPassword(pin: String) {
        passwordPin.editTextPasscode.setText(pin)
    }

    interface AuthenticationListener {
        fun onAuthenticationPasswordSuccess()
    }

    companion object{
        fun newInstance(mode: String) = AuthenticationFragment().apply {
            arguments = Bundle().apply {
                putString("mode", mode)
            }
        }
    }





}

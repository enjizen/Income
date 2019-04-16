package cockatoo.enjizen.income.ui.password.confirm


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.custom.edittext.Password
import cockatoo.enjizen.income.ui.base.BaseFragment
import cockatoo.enjizen.income.ui.service.PasswordService
import kotlinx.android.synthetic.main.fragment_confirm_password.*
import kotlinx.android.synthetic.main.view_keyboard_password.*
import kotlinx.android.synthetic.main.view_passcode.view.*

class ConfirmPasswordFragment : BaseFragment() , ConfirmPasswordView , Password.PasswordListener , View.OnClickListener {
    private lateinit var listener: ConfirmPasswordListener

    private lateinit var presenter: ConfirmPasswordPresenter

    private val PASSWORD_SET = "password_set"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = ConfirmPasswordPresenter(this, PasswordService())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarListener(confirmPasswordToolBar)

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


    @SuppressLint("SetTextI18n")
    private fun setPassword(pin: String) {
        val temp = passwordPin.editTextPasscode.text.toString()
        passwordPin.editTextPasscode.setText("$temp$pin")
    }



    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.key1 -> setPassword("1")
            R.id.key2 -> setPassword("2")
            R.id.key3 -> setPassword("3")
            R.id.key4 -> setPassword("4")
            R.id.key5 -> setPassword("5")
            R.id.key6 -> setPassword("6")
            R.id.key7 -> setPassword("7")
            R.id.key8 -> setPassword("8")
            R.id.key9 -> setPassword("9")
            R.id.key0 -> setPassword("0")
            R.id.keyDel ->{
                val temp = passwordPin.editTextPasscode.text.toString()
                if(temp.isNotBlank()) {
                    val notRemove = temp.substring(0, temp.length - 1)
                    passwordPin.setPassword(notRemove)
                }
            }
        }
    }


    override fun onPasswordResult(password: String) {
        if(password.length == 6){
           // listener.onConfirmPasswordSuccess()
            presenter.confirmCheckPassword(arguments?.getString(PASSWORD_SET)!!, password)
        }


    }

    override fun onConfirmPasswordSuccess() {
        activity!!.finish()
    }

    override fun onConfirmPasswordNotMatch() {
        passwordPin.setInvalidPin()
    }


    interface ConfirmPasswordListener {
        fun onConfirmPasswordSuccess()
    }



    companion object{
        @JvmStatic
        fun newInstance(passwordSet: String) = ConfirmPasswordFragment().apply {
            arguments = Bundle().apply {
                putString(PASSWORD_SET, passwordSet)
            }
        }
    }


}

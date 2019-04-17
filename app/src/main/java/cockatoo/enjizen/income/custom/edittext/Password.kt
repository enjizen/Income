package cockatoo.enjizen.income.custom.edittext

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import cockatoo.enjizen.income.R
import kotlinx.android.synthetic.main.view_passcode.view.*


class Password : FrameLayout {

    private var isInvalid = false

    private var listener: PasswordListener? = null

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        setup(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        setup(attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context!!, attrs, defStyleAttr, defStyleRes) {
        setup(attrs)
    }

    @SuppressLint("CustomViewStyleable")
    private fun setup(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.view_passcode, this@Password)

      editTextPasscode.addTextChangedListener(editTextPasswordTextChange)
    }

    fun setPassword(pass: String){
        editTextPasscode.setText(pass)
    }

    fun setListener(listener: PasswordListener) {
        this.listener = listener
    }

    private fun verifySetPassword(count: Int) {

        if (isInvalid) {
            setValidPin()
            isInvalid = false
        }

        when (count) {
            1 -> {
                setKeyPin1()
                clearKeyPin2()
                textViewErrorMessage.visibility = View.GONE
            }
            2 -> {
                setKeyPin2()
                clearKeyPin3()
            }
            3 -> {
                setKeyPin3()
                clearKeyPin4()
            }
            4 -> {
                setKeyPin4()
                clearKeyPin5()
            }
            5 -> {
                setKeyPin5()
                clearKeyPin6()
            }
            6 -> setKeyPin6()
            else -> clearKeyPin1()
        }
    }

    private fun setValidPin() {
        setKeyPin1()
        setKeyPin2()
        setKeyPin3()
        setKeyPin4()
        setKeyPin5()
        setKeyPin6()
    }

    fun clearPin() {
        editTextPasscode.setText("")
        clearKeyPin1()
        clearKeyPin2()
        clearKeyPin3()
        clearKeyPin4()
        clearKeyPin5()
        clearKeyPin6()
    }

    fun setInvalidPin() {
        isInvalid = true
        setFailKeyPin1()
        setFailKeyPin2()
        setFailKeyPin3()
        setFailKeyPin4()
        setFailKeyPin5()
        setFailKeyPin6()

        delayFail()
    }

    fun showMessageError(messageError: String) {
        textViewErrorMessage.text = messageError
        textViewErrorMessage.visibility = View.VISIBLE
    }

    fun setKeyAllPin(){
        setKeyPin1()
        setKeyPin2()
        setKeyPin3()
        setKeyPin4()
        setKeyPin5()
        setKeyPin5()
    }

     private fun setKeyPin1() {
        pin1.setImageResource(R.drawable.circle_password_success)
    }

     private fun setKeyPin2() {
        pin2.setImageResource(R.drawable.circle_password_success)
    }

    private fun setKeyPin3() {
        pin3.setImageResource(R.drawable.circle_password_success)
    }

    private fun setKeyPin4() {
        pin4.setImageResource(R.drawable.circle_password_success)
    }

    private fun setKeyPin5() {
        pin5.setImageResource(R.drawable.circle_password_success)
    }

    private fun setKeyPin6() {
        pin6.setImageResource(R.drawable.circle_password_success)
    }

    private fun setFailKeyPin1() {
        pin1.setImageResource(R.drawable.circle_password_fail)
    }

    private fun setFailKeyPin2() {
        pin2.setImageResource(R.drawable.circle_password_fail)
    }

    private fun setFailKeyPin3() {
        pin3.setImageResource(R.drawable.circle_password_fail)
    }

    private fun setFailKeyPin4() {
        pin4.setImageResource(R.drawable.circle_password_fail)
    }

    private fun setFailKeyPin5() {
        pin5.setImageResource(R.drawable.circle_password_fail)
    }

    private fun setFailKeyPin6() {
        pin6.setImageResource(R.drawable.circle_password_fail)
    }

    private fun clearKeyPin1() {
        pin1.setImageResource(R.drawable.circle_password_default)
    }

    private fun clearKeyPin2() {
        pin2.setImageResource(R.drawable.circle_password_default)
    }

    private fun clearKeyPin3() {
        pin3.setImageResource(R.drawable.circle_password_default)
    }

    private fun clearKeyPin4() {
        pin4.setImageResource(R.drawable.circle_password_default)
    }

    private fun clearKeyPin5() {
        pin5.setImageResource(R.drawable.circle_password_default)
    }

    private fun clearKeyPin6() {
        pin6.setImageResource(R.drawable.circle_password_default)
    }

    interface PasswordListener {
        fun onPasswordResult(password: String)
    }

  private var editTextPasswordTextChange: TextWatcher = object : TextWatcher {

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(editText: Editable) {
            verifySetPassword(editText.count())
            listener!!.onPasswordResult(editText.toString())
        }
    }

    private fun delayFail() {
        val handler = Handler()
        handler.postDelayed({
            clearPin()
        }, 1000)
    }

}
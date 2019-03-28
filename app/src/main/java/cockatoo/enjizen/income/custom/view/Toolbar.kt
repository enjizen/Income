package cockatoo.enjizen.income.custom.view

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import cockatoo.enjizen.income.R
import kotlinx.android.synthetic.main.item_toolbar.view.*

class Toolbar : FrameLayout {

    private var listener: ToolbarListener? = null

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


    @SuppressLint("Recycle")
    private fun setup(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.item_toolbar, this@Toolbar)
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.Toolbar)
        updateView(typeArray)

    }

    private fun updateView(typeArray: TypedArray) {
        setMessageTitle(typeArray.getString(R.styleable.Toolbar_android_text))
        showCloseButton(typeArray.getBoolean(R.styleable.Toolbar_showCloseButton, false))
        showBackButton(typeArray.getBoolean(R.styleable.Toolbar_showBackButton, false))


        btnClose.setOnClickListener {
            listener!!.onClose()
        }

        btnBack.setOnClickListener {
            listener!!.onBack()
        }
    }

    private fun showBackButton(isShow: Boolean) {
        if(isShow)
            btnBack.visibility = View.VISIBLE
        else
            btnBack.visibility = View.GONE
    }

    fun setMessageTitle(message: String?) {
        textViewMessageTitle.text = message
    }

    fun showCloseButton(isShow: Boolean = false){
        if(isShow)
            btnClose.visibility = View.VISIBLE
        else
            btnClose.visibility = View.GONE
    }


    fun setListener(listener: ToolbarListener) {
        this.listener = listener

    }

    interface ToolbarListener {

        fun onClose()
        fun onBack()

    }

}
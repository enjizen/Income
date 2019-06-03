package cockatoo.enjizen.income.custom.view

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.util.AttributeSet
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.manger.Contextor
import kotlinx.android.synthetic.main.spinner.view.*


class Spinner : FrameLayout {

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        setup(attrs, 0, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        setup(attrs, defStyleAttr, 0)
    }

    @TargetApi(LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context!!, attrs, defStyleAttr, defStyleRes) {
        setup(attrs, defStyleAttr, defStyleRes)
    }

    @SuppressLint("CustomViewStyleable")
    private fun setup(attrs: AttributeSet?,  defStyleAttr: Int, defStyleRes: Int) {
        LayoutInflater.from(context).inflate(R.layout.spinner, this@Spinner)


        val typeArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Spinner,
            defStyleAttr, defStyleRes)

        try {

            setLabelText(typeArray.getString(R.styleable.Spinner_spinnerLabel))

        } finally {
            typeArray.recycle()
        }
    }

    fun setLayoutTextHeight(height: String?) {
        val heightLayout = when (height) {
                                    null -> convertToDp(height = 50f)
                                    "MATCH_PARENT" -> -1
                                    "WRAP_CONTENT" -> -2
                                    else -> {
                                        convertToDp(height = height.toFloat())
                                    }
        }
        spinner.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, heightLayout)
    }

    fun setLabelText(text: String?) {
        textViewLabel.text = text
    }

    fun getLabelText() = textViewLabel.text

    fun setTexLabelColor(color: Int) {
        textViewLabel.setTextColor(color)
    }

    fun showErrorMessage(message: String?, color: Int?) {
        textViewErrorMessage.text = message
        textViewErrorMessage.visibility =  View.VISIBLE
        if (color != null) {
            textViewErrorMessage.setTextColor(resources.getColor(color, Contextor.getInstance().context!!.theme))
        }
    }


    private fun convertToDp(height: Float): Int {
        val metrics = context.resources.displayMetrics
        val pixels = metrics.density * height
        return (pixels + 0.5f).toInt()
    }



}
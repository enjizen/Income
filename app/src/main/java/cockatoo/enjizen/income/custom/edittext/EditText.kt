package cockatoo.enjizen.income.custom.edittext

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.text.InputFilter
import android.text.InputType.TYPE_CLASS_TEXT
import android.util.AttributeSet
import android.util.TypedValue
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.manger.Contextor
import kotlinx.android.synthetic.main.edit_text.view.*


class EditText : FrameLayout {

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        setup(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        setup(attrs)
    }

    @TargetApi(LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context!!, attrs, defStyleAttr, defStyleRes) {
        setup(attrs)
    }

    @SuppressLint("CustomViewStyleable")
    private fun setup(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.edit_text, this@EditText)
        updateView(context.obtainStyledAttributes(attrs, R.styleable.EditText))
    }

    private fun updateView(typeArray: TypedArray?) {
        with(typeArray!!) {
            setLabelText(getString(R.styleable.EditText_labelText))
            setText(getString(R.styleable.EditText_android_text))
            setInputType(getInt(R.styleable.EditText_android_inputType, TYPE_CLASS_TEXT))
            val validStyle = getBoolean(R.styleable.EditText_valid, true)
            if (validStyle) setValidStyle() else invalidStyle()
            if (getInteger(R.styleable.EditText_maxLength, -1) != -1) {
                setMaxLength(getInteger(R.styleable.EditText_maxLength, -1))
            }
            setErrorMessageSize(getFloat(R.styleable.EditText_errorMessageSize, 13f))
            setLayoutTextHeight(getString(R.styleable.EditText_inputTextLayoutHeight))
            setHintText(getString(R.styleable.EditText_android_hint))
            setEditTextAlignment(getInt(R.styleable.EditText_android_textAlignment, View.TEXT_ALIGNMENT_TEXT_START))

        }


        editText!!.customSelectionActionModeCallback = object : ActionMode.Callback {
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?) = false

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean = false

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = false

            override fun onDestroyActionMode(mode: ActionMode?) {}

        }

        typeArray.recycle()

    }

    fun invalidStyle() {
        editText.setBackgroundResource(R.drawable.edit_text_invalid)
    }

    fun setValidStyle() {

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
        editText.layoutParams = LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, heightLayout)
    }

    fun setLabelText(text: String?) {
        textViewLabel.text = text
    }

    fun getLabelText() = textViewLabel.text

    fun setText(text: String?) {
        editText.setText(text)
    }

    fun getText(): String {
       return editText.text.toString()
    }


    fun getEditText() = editText

    fun setTexLabelColor(color: Int) {
        textViewLabel.setTextColor(color)
    }

    fun setInputType(type: Int) {
        editText.inputType = type
    }
/*
    fun invalidStyle() {
        editText.setBackgroundResource(R.drawable.edit_text_invalid)
        editText.setTextColor(getColor(R.color.black))
    }

    fun setValidStyle() {
        editText.setBackgroundResource(R.drawable.selector_edit_text_valid)
        editText.setTextColor(getColor(R.color.black))
        textViewErrorMessage.text = ""
        textViewErrorMessage.visibility = View.INVISIBLE
    }*/

    fun showErrorMessage(message: String?, color: Int?) {
        textViewErrorMessage.text = message
        textViewErrorMessage.visibility =  View.VISIBLE
        if (color != null) {
            textViewErrorMessage.setTextColor(resources.getColor(color, Contextor.getInstance().context!!.theme))
        }
    }


    fun setInputTextColor(color: Int?) {
        editText.setTextColor(color!!)
    }

    fun setInputTextColor(color: String) {
        editText.setTextColor(Color.parseColor(color))
    }

    fun setMaxLength(maxLength: Int) {
        editText?.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
    }

    fun setLabelTextSize(size: Float?) {
        textViewLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, size!!)
    }

    fun setInputTextSize(size: Float?) {
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, size!!)
    }

    fun setErrorMessageSize(size: Float?) {
        textViewErrorMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, size!!)
    }

    private fun getColor(resId: Int): Int = ContextCompat.getColor(context, resId)

    private fun convertToDp(height: Float): Int {
        val metrics = context.resources.displayMetrics
        val pixels = metrics.density * height
        return (pixels + 0.5f).toInt()
    }

    fun setHintText(textHint: String?) {
        editText.hint = textHint
    }

    fun setEditTextAlignment(alignment: Int){
        editText.textAlignment = alignment
    }


}
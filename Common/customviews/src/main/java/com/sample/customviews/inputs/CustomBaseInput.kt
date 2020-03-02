package com.sample.customviews.inputs

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.sample.customviews.R

open class CustomBaseInput(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs) {

    lateinit var editText: EditText

    fun initView(layoutResId: Int, attrs: AttributeSet?, styleableResId: IntArray) {
        View.inflate(context, layoutResId, this)
        context.theme.obtainStyledAttributes(attrs, styleableResId, 0, 0).apply {
            try {
                editText = findViewById(R.id.edtInput)
                setTextColorFromAttr(this)
                setTextFromAttr(this)
            } finally {
                recycle()
            }
        }
    }

    private fun setTextFromAttr(attributeSet: TypedArray) {
        val titleText = attributeSet.getString(R.styleable.CustomButton_title)
        editText.setText(titleText)
    }

    private fun setTextColorFromAttr(attributeSet: TypedArray) {
        val colorResId = attributeSet.getInt(R.styleable.CustomInput_colorResId, R.color.colorText)
        editText.setTextColor(ContextCompat.getColor(context, colorResId))
    }




}
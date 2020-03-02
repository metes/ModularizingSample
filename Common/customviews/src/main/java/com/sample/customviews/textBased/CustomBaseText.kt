package com.sample.customviews.textBased

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sample.customviews.R

open class CustomBaseText(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs) {

    lateinit var txtClickable: TextView

    fun initView(layoutResId: Int, attrs: AttributeSet?, styleableResId: IntArray) {
        View.inflate(context, layoutResId, this)
        context.theme.obtainStyledAttributes(attrs, styleableResId, 0, 0).apply {
            try {
                txtClickable = findViewById(R.id.txtClickable)
                setTextColorFromAttr(this)
                setTextFromAttr(this)
            } finally {
                recycle()
            }
        }
    }

    private fun setTextFromAttr(attributeSet: TypedArray) {
        val titleText = attributeSet.getString(R.styleable.CustomButton_title)
        txtClickable.text = titleText
    }

    private fun setTextColorFromAttr(attributeSet: TypedArray) {
        val isWhiteText = attributeSet.getBoolean(R.styleable.CustomButton_isWhiteText, false)
        if (isWhiteText){
            txtClickable.setTextColor(ContextCompat.getColor(context, R.color.colorTextWhite))
        }
    }

}
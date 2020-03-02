package com.sample.customviews.textBased

import android.content.Context
import android.util.AttributeSet
import com.sample.customviews.R

open class CustomButton(context: Context, attrs: AttributeSet? = null) :
    CustomBaseText(context, attrs) {

    init {
        initView(R.layout.custom_botton, attrs, R.styleable.CustomButton)
    }

    fun setFunction(function: () -> Unit) {
        txtClickable.setOnClickListener { function() }
    }


}
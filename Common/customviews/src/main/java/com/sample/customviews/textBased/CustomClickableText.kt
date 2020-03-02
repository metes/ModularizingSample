package com.sample.customviews.textBased

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.sample.customviews.R

open class CustomClickableText(context: Context, attrs: AttributeSet? = null) :
    CustomBaseText(context, attrs) {

    init {
        Log.d("CustomClickableText" , "CustomClickableText init:")
        initView(R.layout.custom_clickable_text, attrs, R.styleable.CustomButton)
    }

    fun setFunction(function: () -> Unit) {
        txtClickable.setOnClickListener { function() }
    }



}
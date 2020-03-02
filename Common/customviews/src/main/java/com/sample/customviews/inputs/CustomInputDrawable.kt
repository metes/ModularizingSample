package com.sample.customviews.inputs

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.ImageView
import com.sample.customviews.R


open class CustomInputDrawable(context: Context, attrs: AttributeSet? = null) :
    CustomBaseInput(context, attrs) {

    private val startDrawable: ImageView

    init {
        initView(R.layout.custom_input_drawble, attrs, R.styleable.CustomInput)
        startDrawable = findViewById(R.id.imgStartDrawable)
    }

    private fun setImage(attributeSet: TypedArray) {
        val drawableResId = attributeSet.getInt(R.styleable.CustomInput_image, R.drawable.ic_error_24dp)
        startDrawable.setImageResource(drawableResId)
    }


}
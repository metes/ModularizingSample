package com.sample.utils.extentions_bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.sample.utils.R


@BindingAdapter("imageResource")
fun setImageResource(view: ImageView, resId: Int?) {
    val resourceId = if (resId == null || resId == 0) com.sample.resources.R.drawable.ic_error_24dp else resId
    view.setImageResource(resourceId)
}

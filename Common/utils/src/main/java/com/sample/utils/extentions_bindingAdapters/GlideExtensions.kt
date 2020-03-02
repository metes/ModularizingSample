package com.sample.utils.extentions_bindingAdapters

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.sample.utils.R
import com.sample.utils.components.MyAppGlide
import java.io.File
import java.net.URI


@SuppressLint("CheckResult")
fun ImageView.getGlideRequestOptions(): RequestOptions {
    val requestOptions = RequestOptions()
//    requestOptions.placeholder(BaseProgress(context))
    requestOptions.error(com.sample.resources.R.drawable.ic_error_24dp)
    return requestOptions
}

/**
 * String url, File,
 */
@SuppressLint("CheckResult")
fun ImageView.setImage(any: String, isCircle: Boolean = false) {
    val glide = MyAppGlide.with(this)
        .load(any)
        .apply(getGlideRequestOptions())
    if (isCircle) {
        glide.apply(RequestOptions.circleCropTransform())
    }
    glide.into(this)
}

@SuppressLint("CheckResult")
fun ImageView.setImage(any: File?, isCircle: Boolean = false) {
    val glide = MyAppGlide.with(this)
        .load(any)
        .apply(getGlideRequestOptions())
    if (isCircle) {
        glide.apply(RequestOptions.circleCropTransform())
    }
    glide.into(this)
}

@SuppressLint("CheckResult")
fun ImageView.setImage(any: Int?, isCircle: Boolean = false) {
    val glide = MyAppGlide.with(this)
        .load(any)
        .apply(getGlideRequestOptions())
    if (isCircle) {
        glide.apply(RequestOptions.circleCropTransform())
    }
    glide.into(this)
}

@SuppressLint("CheckResult")
fun ImageView.setImage(any: URI, isCircle: Boolean = false) {
    val glide = MyAppGlide.with(this)
        .load(any)
        .apply(getGlideRequestOptions())
    if (isCircle) {
        glide.apply(RequestOptions.circleCropTransform())
    }
    glide.into(this)
}

/**
 * String url, File, URİ
 * success and error listeners
 */
fun ImageView.setImageWithSuccessListener(
    any: Any,
    successAction: () -> Unit,
    errorAction: (() -> Unit)? = null
) {
    MyAppGlide.with(this)
        .load(any)
        .apply(getGlideRequestOptions())
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                errorAction?.let { it() }
                return true
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                successAction()
                return false
            }

        })
        .into(this)
}
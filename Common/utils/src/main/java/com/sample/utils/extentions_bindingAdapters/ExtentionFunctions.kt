package com.sample.utils.extentions_bindingAdapters

import android.view.View
import com.sample.utils.utils.AnimUtils.startFadeInAnimation
import com.sample.utils.utils.AnimUtils.startFadeOutAnimation
import com.sample.utils.utils.AnimUtils.startSpringResize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun View.splashAnimation(functionOnFinish: () -> Unit) {
    CoroutineScope(Dispatchers.Main).launch {
        startSpringResize(this@splashAnimation)
        delay(2000)
        functionOnFinish()
    }
}

fun View.visibleIf(showing: Boolean) {
    if (showing) {
        startFadeInAnimation(this, 300L)
    } else {
        startFadeOutAnimation(this, 300L)
    }
}

fun <E> Collection<E>.toArrayList(): ArrayList<E> {
    return ArrayList<E>().apply {
        addAll(this)
    }
}

fun <E> ArrayList<E>.replaceData(newList: Collection<E>) {
    clear()
    addAll(newList)
}


fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}


fun View.setGone() {
    visibility = View.GONE
}


fun View.startFadeIn() {
    startFadeInAnimation(this, 300L)
}


fun View.startFadeOut() {
    startFadeOutAnimation(this, 300L)
}


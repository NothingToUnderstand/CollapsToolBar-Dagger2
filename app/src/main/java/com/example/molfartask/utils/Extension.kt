package com.example.molfartask.utils

import android.content.res.Resources
import android.view.View
import android.widget.HorizontalScrollView


inline fun <reified T : View> HorizontalScrollView.scrollToPosition(
    id: Int?) {
    val view = id?.let { findViewById<T>(it) } ?: return
    val leftEdgePx = view.left
    val screenCenterPx = Resources.getSystem().displayMetrics.widthPixels / 2
    val scrollPx = if (leftEdgePx < screenCenterPx) 0
    else leftEdgePx - screenCenterPx + view.width / 2
    this.post {
        this.smoothScrollTo(scrollPx, view.top)
    }
}
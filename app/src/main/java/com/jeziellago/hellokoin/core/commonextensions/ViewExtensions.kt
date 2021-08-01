package com.jeziellago.hellokoin.core.commonextensions

import android.view.View
import androidx.core.view.isVisible

fun View.toggleVisibility() {
    this.isVisible = this.isVisible.not()
}
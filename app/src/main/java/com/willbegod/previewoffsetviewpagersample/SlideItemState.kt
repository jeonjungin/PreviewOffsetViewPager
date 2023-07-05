package com.willbegod.previewoffsetviewpagersample

import androidx.annotation.ColorInt

data class SlideItemState(
    val text: String,
    @ColorInt
    val color: Int,
    private val onClick: (text: String) -> Unit
) {
    fun onClick() = onClick(text)
}
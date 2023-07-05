package com.willbegod.previewoffsetviewpagersample

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.willbegod.previewoffsetviewpagersample.databinding.ListItemSlideBinding

class SlideViewHolder(private val binding: ListItemSlideBinding): ViewHolder(binding.root) {

    fun bind(state: SlideItemState) {
        binding.state = state
        binding.executePendingBindings()
    }
}
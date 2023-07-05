package com.willbegod.previewoffsetviewpagersample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.willbegod.previewoffsetviewpagersample.databinding.ListItemSlideBinding

class SlideAdapter: ListAdapter<SlideItemState, SlideViewHolder>(DiffImpl) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SlideViewHolder(ListItemSlideBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object DiffImpl: DiffUtil.ItemCallback<SlideItemState>() {
        override fun areItemsTheSame(oldItem: SlideItemState, newItem: SlideItemState) =
            oldItem.text == newItem.text

        override fun areContentsTheSame(oldItem: SlideItemState, newItem: SlideItemState) =
            oldItem == newItem
    }
}
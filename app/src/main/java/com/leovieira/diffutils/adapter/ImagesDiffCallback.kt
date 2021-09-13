package com.leovieira.diffutils.adapter

import androidx.recyclerview.widget.DiffUtil
import com.leovieira.diffutils.model.Image

open class ImagesDiffCallback(
) : DiffUtil.ItemCallback<Image>() {

    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

}
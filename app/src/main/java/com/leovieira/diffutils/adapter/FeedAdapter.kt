package com.leovieira.diffutils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leovieira.diffutils.R
import com.leovieira.diffutils.databinding.FeedItemBinding
import com.leovieira.diffutils.model.Image

class FeedAdapter : RecyclerView.Adapter<FeedViewHolder>() {

    private var listOfImages = emptyList<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false).apply {
            return FeedViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        listOfImages[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = listOfImages.size

    fun update(newList: List<Image>) {
        val diffCallback = ImagesDiffCallback(oldList = listOfImages, newList = newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listOfImages = newList
        diffResult.dispatchUpdatesTo(this)
    }

}

class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: FeedItemBinding = FeedItemBinding.bind(view)

    fun bind(image: Image) {
        binding.textViewName.text = image.user
        Glide.with(itemView).load(image.largeImageURL).into(binding.imageViewPhoto)
        Glide.with(itemView).load(image.userImageURL).into(binding.imageViewAvatar)
    }

}
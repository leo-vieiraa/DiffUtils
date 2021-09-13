package com.leovieira.diffutils.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.leovieira.diffutils.R
import com.leovieira.diffutils.databinding.FeedItemBinding
import com.leovieira.diffutils.model.Image

class FeedAdapter : ListAdapter<Image, FeedViewHolder>(ImagesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false).apply {
            return FeedViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        getItem(position).let { image ->
            holder.bind(image)
        }
    }

}

class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: FeedItemBinding = FeedItemBinding.bind(view)

    fun bind(image: Image) {
        binding.textViewName.text = image.user
        Glide.with(itemView).load(image.userImageURL).into(binding.imageViewAvatar)
        binding.textViewLikes.text = image.likes.toString()

        Glide.with(itemView)
            .load(image.largeImageURL)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    p0: GlideException?,
                    p1: Any?,
                    p2: Target<Drawable>?,
                    p3: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    p0: Drawable?,
                    p1: Any?,
                    p2: Target<Drawable>?,
                    p3: DataSource?,
                    p4: Boolean
                ): Boolean {
                    binding.bottomBarInfo.visibility = View.VISIBLE
                    return false
                }
            })
            .into(binding.imageViewPhoto)

    }

}
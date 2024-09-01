package com.zohaib.starzplay.ui.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zohaib.starzplay.R
import com.zohaib.starzplay.ui.detail.DetailScreenActivity
import com.zohaib.starzplayllib.data.model.MediaItem
import com.zohaib.starzplayllib.utils.AppConstant

class MediaItemAdapter(
    private val mediaItems: List<MediaItem>
) : RecyclerView.Adapter<MediaItemAdapter.MediaItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.media_item_view, parent, false)
        return MediaItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MediaItemViewHolder, position: Int) {
        val mediaItem = mediaItems[position]
        Glide.with(holder.mediaImageView.context)
            .load(AppConstant.BASE_URL_IMAGE + mediaItem.poster_path)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder_error)
            .into(holder.mediaImageView)
        holder.mediaImageView.setOnClickListener {
            val context = holder.mediaImageView.context
            val intent = Intent(context, DetailScreenActivity::class.java).apply {
                putExtra("mediaItem", mediaItem)
            }
            holder.mediaImageView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mediaItems.size
    }

    inner class MediaItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mediaImageView: ImageView = itemView.findViewById(R.id.mediaImageView)
    }
}

package com.zohaib.starzplay.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zohaib.starzplay.R
import com.zohaib.starzplayllib.data.model.CarouselItem

class CarouselItemAdapter(
    private var carouselItems: List<CarouselItem>
) : RecyclerView.Adapter<CarouselItemAdapter.CarouselItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_secreen_recyclerview, parent, false)
        return CarouselItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        val carouselItem = carouselItems[position]
        holder.bind(carouselItem)
    }

    override fun getItemCount(): Int {
        return carouselItems.size
    }

    inner class CarouselItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val carouselHeading: TextView = itemView.findViewById(R.id.carouselHeading)
        private val carouselRecyclerView: RecyclerView = itemView.findViewById(R.id.carouselRecyclerView)

        fun bind(carouselItem: CarouselItem) {
            carouselHeading.text = carouselItem.mediaType.uppercase()
            carouselRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            carouselRecyclerView.adapter = MediaItemAdapter(carouselItem.mediaItems)
        }
    }

    fun updateData(carouselItems: List<CarouselItem>){
        this.carouselItems = carouselItems
        this.notifyDataSetChanged()
    }
}
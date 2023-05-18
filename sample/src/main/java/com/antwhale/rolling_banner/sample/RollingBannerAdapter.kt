package com.antwhale.rolling_banner.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.antwhale.AntwhaleImageSliderAdapter

class RollingBannerAdapter(
    override val sliderImages: List<String>
) : AntwhaleImageSliderAdapter<String, RollingBannerAdapter.RollingBannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RollingBannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return RollingBannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RollingBannerViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(sliderImages[getRealPosition(position)])
            .into(holder.imageView)
    }

    override fun getItemListSize(): Int = sliderImages.size

    inner class RollingBannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)

    }

}
package com.antwhale.imageslider

import androidx.recyclerview.widget.RecyclerView

abstract class AntwhaleImageSliderAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    abstract val sliderImages: List<T>
    private var placeHolderCount = 100000

    override fun getItemCount(): Int {
        return sliderImages.size * placeHolderCount
    }

    abstract fun getItemListSize(): Int

    fun getRealPosition(position: Int) = position % getItemListSize()
}
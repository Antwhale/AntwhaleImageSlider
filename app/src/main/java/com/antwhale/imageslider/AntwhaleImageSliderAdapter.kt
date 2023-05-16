package com.antwhale.imageslider

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.antwhale.view.imageslider.R
import com.antwhale.view.imageslider.databinding.ItemContainerAntwhaleSliderImageBinding

class AntwhaleImageSliderAdapter(val sliderImages: List<String>) : RecyclerView.Adapter<AntwhaleImageSliderAdapter.ImageSliderViewHolder>() {
    private var placeHolderCount = 100000
    val realItemCount : Int
        get() = sliderImages.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val sliderImageBinding = DataBindingUtil.inflate<ItemContainerAntwhaleSliderImageBinding>(
            layoutInflater, R.layout.item_container_antwhale_slider_image, parent, false
        )
        return ImageSliderViewHolder(sliderImageBinding)
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
       holder.bindSliderImage(sliderImages[getRealPosition(position)])
    }

    override fun getItemCount(): Int {
        return sliderImages.size * placeHolderCount
    }

    fun getRealPosition(position: Int) = position % realItemCount

    inner class ImageSliderViewHolder(val itemContainerAntwhaleSliderImageBinding: ItemContainerAntwhaleSliderImageBinding)
        : RecyclerView.ViewHolder(itemContainerAntwhaleSliderImageBinding.root) {
            fun bindSliderImage(imageURL : String) {
                itemContainerAntwhaleSliderImageBinding.imageURL = imageURL
            }
        }
}
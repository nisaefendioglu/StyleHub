package com.nisaefendioglu.shoppingapp.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nisaefendioglu.shoppingapp.R

class ImageSliderAdapter(private val context: Context, private val images: List<String>) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_image_slider, parent, false)
        return ImageSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        val imageUrl = images[position]
        Glide.with(context).load(imageUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ImageSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageSliderImageView)
    }
}

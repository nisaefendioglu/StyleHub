package com.nisaefendioglu.shoppingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nisaefendioglu.shoppingapp.R
import com.nisaefendioglu.shoppingapp.databinding.ItemCardBinding
import com.nisaefendioglu.shoppingapp.model.Product

class ProductAdapter(private var productList: List<Product> = emptyList()) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    fun setProductList(productList: List<Product>) {
        this.productList = productList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        with(holder.binding) {
            productName.text = product.title
            productBrand.text = product.brand
            productDiscount.text = "%${product.discountPercentage}"
            productPrice.text = "$${product.price}"
            productCardRatingBar.rating = product.rating.toFloat()
            productCardRatedValue.text = product.rating.toString()
            Glide.with(holder.itemView.context)
                .load(product.thumbnail)
                .error(R.drawable.placeholder)
                .into(productCardImageview)
        }
    }

    override fun getItemCount(): Int = productList.size

    class ViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)
}

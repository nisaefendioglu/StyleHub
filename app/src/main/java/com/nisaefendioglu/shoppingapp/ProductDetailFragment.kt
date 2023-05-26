package com.nisaefendioglu.shoppingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.nisaefendioglu.shoppingapp.databinding.FragmentProductDetailBinding
import com.nisaefendioglu.shoppingapp.model.Product

class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding

    companion object {
        const val DATA_KEY = "product"

        fun newInstance(product: Product): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(DATA_KEY, product)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)
        val product = arguments?.getParcelable<Product>(DATA_KEY)
        product?.let { displayProductDetails(it) }
        return binding.root
    }

    private fun displayProductDetails(product: Product) {
        context?.let {
            Glide.with(it).load(product.thumbnail).into(binding.productImageView)
            binding.titleTextView.text = product.title
            binding.descriptionTextView.text = product.description
            binding.priceTextView.text = "$${product.price}"

        }
    }
}

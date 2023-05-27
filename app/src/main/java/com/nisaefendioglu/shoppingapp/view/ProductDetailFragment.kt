package com.nisaefendioglu.shoppingapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.nisaefendioglu.shoppingapp.R
import com.nisaefendioglu.shoppingapp.adapter.ImageSliderAdapter
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
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_product_detail, container, false)
        val product = arguments?.getParcelable<Product>(DATA_KEY)
        product?.let { displayProductDetails(it) }
        return binding.root
    }

    private fun displayProductDetails(product: Product) {
        context?.let {
            val imageSliderAdapter = ImageSliderAdapter(it, product.images)
            binding.imageViewPager.adapter = imageSliderAdapter

            val tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.imageViewPager) { tab, position ->
                val customView = LayoutInflater.from(binding.tabLayout.context)
                    .inflate(R.layout.custom_tab_indicator, binding.tabLayout, false)

                val dotImageView = customView.findViewById<ImageView>(R.id.dotImageView)
                if (position == binding.imageViewPager.currentItem) {
                    dotImageView.setImageResource(R.drawable.ic_dot_selected)
                } else {
                    dotImageView.setImageResource(R.drawable.ic_dot_unselected)
                }

                tab.customView = customView
            }
            tabLayoutMediator.attach()

            binding.imageViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    for (i in 0 until binding.tabLayout.tabCount) {
                        val tab = binding.tabLayout.getTabAt(i)
                        val customView = tab?.customView
                        val dotImageView = customView?.findViewById<ImageView>(R.id.dotImageView)
                        if (i == position) {
                            dotImageView?.setImageResource(R.drawable.ic_dot_selected)
                        } else {
                            dotImageView?.setImageResource(R.drawable.ic_dot_unselected)
                        }
                    }
                }
            })

            binding.titleTextView.text = product.title
            binding.descriptionTextView.text = product.description
            binding.priceTextView.text = "$${product.price}"
        }
    }
}

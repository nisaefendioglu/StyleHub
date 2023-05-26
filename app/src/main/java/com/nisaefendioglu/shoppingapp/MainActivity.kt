package com.nisaefendioglu.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.nisaefendioglu.shoppingapp.adapter.ProductAdapter
import com.nisaefendioglu.shoppingapp.databinding.ActivityMainBinding
import com.nisaefendioglu.shoppingapp.viewmodel.ProductViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()
        observeProductList()
        viewModel.getAllProducts().observe(this, Observer { productList ->
            productAdapter.setProductList(productList)
        })
    }

    private fun prepareRecyclerView() {
        productAdapter = ProductAdapter()
        binding.recylerview.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = productAdapter
        }
    }

private fun observeProductList() {
        viewModel.getAllProducts().observe(this, Observer { productList ->
            productAdapter.setProductList(productList)
        })
    }
}

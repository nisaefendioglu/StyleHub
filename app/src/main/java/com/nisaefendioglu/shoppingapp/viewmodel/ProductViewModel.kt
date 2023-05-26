package com.nisaefendioglu.shoppingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nisaefendioglu.shoppingapp.model.Product
import com.nisaefendioglu.shoppingapp.repository.ProductRepository

class ProductViewModel : ViewModel() {

    private val productRepository = ProductRepository()

    fun getAllProducts(): LiveData<List<Product>> {
        return productRepository.getAllProducts()
    }
}

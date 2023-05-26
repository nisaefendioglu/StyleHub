package com.nisaefendioglu.shoppingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nisaefendioglu.shoppingapp.api.RetrofitInstance
import com.nisaefendioglu.shoppingapp.model.Product
import com.nisaefendioglu.shoppingapp.model.ProductResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {

    private val productLiveData = MutableLiveData<List<Product>>()

    fun getAllProducts() {
        val api = RetrofitInstance.api
        api.getProducts().enqueue(object : Callback<ProductResponseModel> {
            override fun onResponse(
                call: Call<ProductResponseModel>,
                response: Response<ProductResponseModel>
            ) {
                val productList = response.body()?.products
                productLiveData.value = productList ?: emptyList()
            }

            override fun onFailure(call: Call<ProductResponseModel>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun observeProductLiveData(): LiveData<List<Product>> {
        return productLiveData
    }
}

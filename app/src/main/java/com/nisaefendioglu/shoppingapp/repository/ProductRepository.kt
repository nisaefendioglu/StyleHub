package com.nisaefendioglu.shoppingapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nisaefendioglu.shoppingapp.api.RetrofitInstance
import com.nisaefendioglu.shoppingapp.model.Product
import com.nisaefendioglu.shoppingapp.model.ProductResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {

    private val productLiveData = MutableLiveData<List<Product>>()

    fun getAllProducts(): LiveData<List<Product>> {
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
        return productLiveData
    }
}

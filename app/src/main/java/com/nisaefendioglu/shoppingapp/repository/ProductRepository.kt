package com.nisaefendioglu.shoppingapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nisaefendioglu.shoppingapp.api.ApiService
import com.nisaefendioglu.shoppingapp.model.ProductResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository(private val apiService: ApiService) {
    private var _productLiveData = MutableLiveData<ProductResponseModel?>()
    val productLiveData: LiveData<ProductResponseModel?>
        get() = _productLiveData

    private var _productErrorLiveData = MutableLiveData<Boolean>()
    val productErrorLiveData: LiveData<Boolean>
        get() = _productErrorLiveData

    fun getData() {
        val result = apiService.getProducts()
        result.enqueue(object : Callback<ProductResponseModel> {
            override fun onResponse(
                call: Call<ProductResponseModel>,
                response: Response<ProductResponseModel>
            ) {
                val responseBody = response.body()
                _productLiveData.postValue(responseBody)
                _productErrorLiveData.postValue((responseBody?.products ?: true) as Boolean?)
            }

            override fun onFailure(call: Call<ProductResponseModel>, t: Throwable) {
                _productErrorLiveData.postValue(true)
            }
        })
    }
}
}
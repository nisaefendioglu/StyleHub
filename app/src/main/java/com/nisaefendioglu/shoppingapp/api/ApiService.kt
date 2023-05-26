package com.nisaefendioglu.shoppingapp.api
import com.nisaefendioglu.shoppingapp.model.ProductResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    fun getProducts(): Call<ProductResponseModel>
}

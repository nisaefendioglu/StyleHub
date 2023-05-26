package com.nisaefendioglu.shoppingapp.api

import com.nisaefendioglu.shoppingapp.api.helper.Constant
import com.nisaefendioglu.shoppingapp.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {
    @Provides
    fun provideRetrofitInterface(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: ApiService): ProductRepository {
        return ProductRepository(apiService)
    }
}
package com.tops.androidfundamentals.service

import com.tops.androidfundamentals.model.ProductRoot
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("/products?limit=5")
    fun listproduct(): Call<ProductRoot>
}
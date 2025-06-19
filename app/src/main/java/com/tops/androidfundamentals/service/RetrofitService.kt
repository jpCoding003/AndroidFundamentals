package com.tops.androidfundamentals.service

import com.tops.androidfundamentals.model.NewProduct
import com.tops.androidfundamentals.model.ProductRoot
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @GET("/products?limit=5")
    fun listproduct(): Call<ProductRoot>

    @POST("products/add")
    fun saveProduct(@Body product: NewProduct): Call<NewProduct>
}
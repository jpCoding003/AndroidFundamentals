package com.tops.androidfundamentals.model


import com.google.gson.annotations.SerializedName

data class ProductRoot(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int
)
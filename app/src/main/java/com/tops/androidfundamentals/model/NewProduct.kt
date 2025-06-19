package com.tops.androidfundamentals.model

data class NewProduct(

   // @SerializedName("Title")  // Use this "serializedName" if you want to give "data object" name different
                              // than the name present in data in the json data
    val title: String,
    val description: String
)

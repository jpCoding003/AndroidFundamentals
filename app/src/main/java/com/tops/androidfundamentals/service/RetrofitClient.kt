package com.tops.androidfundamentals.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{

        var service: RetrofitService ?= null

        fun getInstance(): RetrofitService = service?: synchronized(this) {
            service?: getdata().also { service = it }
        }

        private fun getdata(): RetrofitService{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RetrofitService::class.java)
        }
    }
}
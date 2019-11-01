package com.dev.hadra.service

import com.dev.hadra.repository.APIService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    //private lateinit var retrofit: Retrofit
    companion object {
        fun create(url:String) : APIService {
            val retrofit =Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build()
            return retrofit.create(APIService::class.java)
        }
    }
}
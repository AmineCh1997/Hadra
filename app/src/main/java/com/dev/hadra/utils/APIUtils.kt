package com.dev.hadra.utils

import com.dev.hadra.repository.APIService
import com.dev.hadra.service.RetrofitClient

class APIUtils {

companion object {
    val BASE_URL_CCC = "http://192.168.1.25:4000/"
    fun webService(): APIService {
        return RetrofitClient.create(BASE_URL_CCC)
    }
}

}
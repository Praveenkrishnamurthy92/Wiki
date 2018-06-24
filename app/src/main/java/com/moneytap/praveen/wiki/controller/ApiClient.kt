package com.moneytap.praveen.wiki.controller

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by cheluva on 24/06/18.
 */
class ApiClient() {
    companion object compObjName {
        const val BASE_URL = "https://en.wikipedia.org//w/"
        val getApiRetrofitClient: Retrofit by lazy {
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }

}
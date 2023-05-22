package br.senai.sp.jandira.deliveryyvy.api

import br.senai.sp.jandira.deliveryyvy.api.commons._CommonsAPIService
import br.senai.sp.jandira.deliveryyvy.constants.Constants

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {
    companion object {
        private lateinit var instance: Retrofit

        private fun getRetrofit(URL: String): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun commonsRetrofitService(): _CommonsAPIService {
            return getRetrofit(Constants.BASE_URL).create(_CommonsAPIService::class.java)
        }
    }
}
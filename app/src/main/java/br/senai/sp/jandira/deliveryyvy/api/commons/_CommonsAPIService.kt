package br.senai.sp.jandira.deliveryyvy.api.commons

import br.senai.sp.jandira.deliveryyvy.dto.Credentials
import br.senai.sp.jandira.deliveryyvy.dto.Token
import br.senai.sp.jandira.deliveryyvy.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST



interface _CommonsAPIService {
    @POST("commons/login/")
    fun auth(@Body credentials: Credentials): Call<Token>

    @GET("commons/user/details")
    fun getDetailsOfUser(@Header("Authorization") token: String): Call<User>

}
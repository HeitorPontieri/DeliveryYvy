package br.senai.sp.jandira.deliveryyvy.api.commons

import br.senai.sp.jandira.deliveryyvy.dto.Credentials
import br.senai.sp.jandira.deliveryyvy.dto.Token
import br.senai.sp.jandira.deliveryyvy.models.Route
import br.senai.sp.jandira.deliveryyvy.models.User
import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.http.*


interface _CommonsAPIService {
    @POST("commons/login/")
    fun auth(@Body credentials: Credentials): Call<Token>

    @GET("commons/user/details")
    fun getDetailsOfUser(@Header("Authorization") token: String): Call<User>

}


package com.example.friendslocation.net.retrofit

import com.example.friendslocation.entity.Location
import retrofit2.Call
import retrofit2.http.*

interface LocationService {

    @POST("user/{userId}/location")
    @Headers("Accept-Encoding: application/json")
    fun updateLocation(
        @Path("userId") userId: String,
        @Body location: Location
    ): Call<Location>

    @GET("user/{userId}/location/{friendId}")
    @Headers("Accept-Encoding: application/json")
    fun friendLocation(
        @Path("userId") userId: String,
        @Path("friendId") friendId: String
    ): Call<Location>

}

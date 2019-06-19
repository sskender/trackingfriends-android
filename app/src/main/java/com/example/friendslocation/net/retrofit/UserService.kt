package com.example.friendslocation.net.retrofit

import com.example.friendslocation.entity.User
import com.example.friendslocation.entity.UserPublicProfile
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @POST("user/register")
    @Headers("Accept-Encoding: application/json")
    fun register(
        @Body user: User
    ): Call<UserPublicProfile>

    @POST("user/login")
    @Headers("Accept-Encoding: application/json")
    fun login(
        @Body user: User
    ): Call<UserPublicProfile>

    @GET("user/{userId}")
    @Headers("Accept-Encoding: application/json")
    fun userProfileById(
        @Path("userId") userId: String
    ): Call<UserPublicProfile>

    @POST("user/{userId}/update")
    @Headers("Accept-Encoding: application/json")
    fun updateProfile(
        @Path("userId") userId: String,
        @Body user: User
    ): Call<UserPublicProfile>

    @GET("user/{userId}/search")
    @Headers("Accept-Encoding: application/json")
    fun searchUsernames(
        @Path("userId") userId: String,
        @Query("username") username: String
    ): Call<List<UserPublicProfile>>

    @GET("user/{userId}/friends")
    @Headers("Accept-Encoding: application/json")
    fun getFriendsProfiles(

    ): Call<List<UserPublicProfile>>

    @GET("user/{userId}/friends/requests")
    @Headers("Accept-Encoding: application/json")
    fun getFriendRequestsProfiles(

    ): Call<List<UserPublicProfile>>

    @POST("user/{userId}/friends")
    @Headers("Accept-Encoding: application/json")
    fun sendFriendRequestAction(
        @Path("userId") userId: String,
        @Query("friendId") friendId: String,
        @Query("action") action: String
    ): Call<Unit>
    
}

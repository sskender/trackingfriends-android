package com.example.friendslocation.net.retrofit

import com.example.friendslocation.entity.User
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory
import com.example.friendslocation.net.RestInterface
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class RestRetrofit : RestInterface {

    private val userService: UserService

    init {
        val SERVER_URL = "http://" + RestFactory.SERVER_IP + ":" + RestFactory.SERVER_PORT + "/api/v1/"

        val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .build()

        this.userService = retrofit.create(UserService::class.java)
    }

    override fun registerUser(user: User): UserPublicProfile? {
        val call: Call<UserPublicProfile> = userService.register(user)
        val response: Response<UserPublicProfile> = call.execute()

        if (response.isSuccessful) {
            return response.body()
        } else {
            TODO("not implemented")
        }
    }

    override fun loginUser(user: User): UserPublicProfile? {
        val call: Call<UserPublicProfile> = userService.login(user)
        val response: Response<UserPublicProfile> = call.execute()

        if (response.isSuccessful) {
            return response.body()
        } else {
            TODO("not implemented")
        }
    }

    override fun getUserProfileById(userId: String): UserPublicProfile? {
        val call: Call<UserPublicProfile> = userService.userProfileById(userId)
        val response: Response<UserPublicProfile> = call.execute()

        if (response.isSuccessful) {
            return response.body()
        } else {
            TODO("not implemented")
        }
    }

    override fun updateUserProfile(user: User): UserPublicProfile? {
        val call: Call<UserPublicProfile> = userService.updateProfile(user.userId, user)
        val response: Response<UserPublicProfile> = call.execute()

        if (response.isSuccessful) {
            return response.body()
        } else {
            TODO("not implemented")
        }
    }

}

package com.example.friendslocation.net.retrofit

import com.example.friendslocation.entity.Location
import com.example.friendslocation.entity.User
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory
import com.example.friendslocation.net.RestInterface
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class RestRetrofit : RestInterface {

    private val userService: UserService
    private val locationService: LocationService

    init {
        val SERVER_URL = "http://" + RestFactory.SERVER_IP + ":" + RestFactory.SERVER_PORT + "/api/v1/"

        val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .build()

        this.userService = retrofit.create(UserService::class.java)
        this.locationService = retrofit.create(LocationService::class.java)
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

    override fun updateUserLocation(location: Location): Location? {
        val call: Call<Location> = locationService.updateLocation(location.userId, location)
        val response: Response<Location> = call.execute()

        if (response.isSuccessful) {
            return response.body()
        } else {
            TODO("not implemented")
        }
    }

    override fun getFriendsLocation(userId: String, friendId: String): Location? {
        val call: Call<Location> = locationService.friendLocation(userId, friendId)
        val response: Response<Location> = call.execute()

        if (response.isSuccessful) {
            return response.body()
        } else {
            TODO("not implemented")
        }
    }

}

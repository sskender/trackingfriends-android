package com.example.friendslocation.net.retrofit

import com.example.friendslocation.config.AppConstants
import com.example.friendslocation.entity.Location
import com.example.friendslocation.entity.User
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestRetrofit : RestInterface {

    /* services */
    private val userService: UserService
    private val locationService: LocationService


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        this.userService = retrofit.create(UserService::class.java)
        this.locationService = retrofit.create(LocationService::class.java)
    }


    override fun registerUser(user: User): UserPublicProfile? {
        return userService.register(user).execute().body()
    }

    override fun loginUser(user: User): UserPublicProfile? {
        return userService.login(user).execute().body()
    }

    override fun getUserProfileById(userId: String): UserPublicProfile? {
        return userService.userProfileById(userId).execute().body()
    }

    override fun updateUserProfile(user: User): UserPublicProfile? {
        return userService.updateProfile(user.userId, user).execute().body()
    }

    override fun updateUserLocation(location: Location): Location? {
        return locationService.updateLocation(location.userId, location).execute().body()
    }

    override fun getFriendsLocation(userId: String, friendId: String): Location? {
        return locationService.friendLocation(userId, friendId).execute().body()
    }

    override fun searchUsersForFriends(userId: String, searchUsername: String): List<UserPublicProfile>? {
        return userService.searchUsernames(userId, searchUsername).execute().body()
    }

    override fun getUserFriends(userId: String): List<UserPublicProfile>? {
        return userService.getFriendsProfiles(userId).execute().body()
    }

    override fun getUserFriendRequests(userId: String): List<UserPublicProfile>? {
        return userService.getFriendRequestsProfiles(userId).execute().body()
    }

    override fun sendFriendRequest(userId: String, friendId: String): Unit? {
        return userService.sendFriendRequestAction(userId, friendId, AppConstants.FRIEND_SEND_REQUEST).execute().body()
    }

    override fun acceptFriendRequest(userId: String, friendId: String): Unit? {
        return userService.sendFriendRequestAction(userId, friendId, AppConstants.FRIEND_ACCEPT_REQUEST).execute()
            .body()
    }

    override fun denyFriendRequest(userId: String, friendId: String): Unit? {
        return userService.sendFriendRequestAction(userId, friendId, AppConstants.FRIEND_DENY_REQUEST).execute().body()
    }

    override fun deleteFriend(userId: String, friendId: String): Unit? {
        return userService.deleteFriend(userId, friendId).execute().body()
    }

}

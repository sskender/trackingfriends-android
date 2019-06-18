package com.example.friendslocation.net.retrofit

import com.example.friendslocation.entity.Location
import com.example.friendslocation.entity.User
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory
import com.example.friendslocation.net.RestInterface
import retrofit2.Retrofit

class RestRetrofit : RestInterface {

    /* server ip */
    val SERVER_URL: String = "http://" + RestFactory.SERVER_IP + ":" + RestFactory.SERVER_PORT + "/api/v1/"


    /* friend requests */
    private val FRIEND_SEND_REQUEST: String = "request"
    private val FRIEND_ACCEPT_REQUEST: String = "accept"
    private val FRIEND_DENY_REQUEST: String = "deny"


    /* services */
    private val userService: UserService
    private val locationService: LocationService


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
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

    override fun getUserFriends() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserFriendRequests() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendFriendRequest(userId: String, friendId: String): Void? {
        return userService.sendFriendRequestAction(userId, friendId, FRIEND_SEND_REQUEST).execute().body()
    }

    override fun acceptFriendRequest(userId: String, friendId: String): Void? {
        return userService.sendFriendRequestAction(userId, friendId, FRIEND_ACCEPT_REQUEST).execute().body()
    }

    override fun denyFriendRequest(userId: String, friendId: String): Void? {
        return userService.sendFriendRequestAction(userId, friendId, FRIEND_DENY_REQUEST).execute().body()
    }

}
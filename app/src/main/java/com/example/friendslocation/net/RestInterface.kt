package com.example.friendslocation.net

import com.example.friendslocation.entity.Location
import com.example.friendslocation.entity.User
import com.example.friendslocation.entity.UserPublicProfile

interface RestInterface {

    fun registerUser(user: User): UserPublicProfile?

    fun loginUser(user: User): UserPublicProfile?


    fun getUserProfileById(userId: String): UserPublicProfile?

    fun updateUserProfile(user: User): UserPublicProfile?


    fun searchUsersForFriends(userId: String, searchUsername: String): List<UserPublicProfile>?

    fun getUserFriends(userId: String): List<UserPublicProfile>?

    fun getUserFriendRequests(userId: String): List<UserPublicProfile>?


    fun sendFriendRequest(userId: String, friendId: String): Unit?

    fun acceptFriendRequest(userId: String, friendId: String): Unit?

    fun denyFriendRequest(userId: String, friendId: String): Unit?


    fun deleteFriend(userId: String, friendId: String): Unit?


    fun updateUserLocation(location: Location): Location?

    fun getFriendsLocation(userId: String, friendId: String): Location?

}

package com.example.friendslocation.net

import com.example.friendslocation.entity.User
import com.example.friendslocation.entity.UserPublicProfile

interface RestInterface {

    fun registerUser(user: User): UserPublicProfile?

    fun loginUser(user: User): UserPublicProfile?

    fun getUserProfileById(userId: String): UserPublicProfile?

    fun updateUserProfile(user: User): UserPublicProfile?

}

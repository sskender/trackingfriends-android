package com.example.friendslocation.dao

import com.example.friendslocation.entity.Location
import com.example.friendslocation.entity.UserPublicProfile

/**
 * List used for adapters
 */
object UserDataDao {

    /**
     * User public profiles are saved here.
     *
     * E.g.:
     * - search users
     * - friend requests profiles
     * - friends profiles
     */
    var userPublicProfilesList: MutableList<UserPublicProfile> = mutableListOf()

    /**
     * Friends locations are saved here.
     */
    var friendLocationsList: MutableList<Location> = mutableListOf()

}

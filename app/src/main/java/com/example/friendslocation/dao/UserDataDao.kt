package com.example.friendslocation.dao

import com.example.friendslocation.entity.Location
import com.example.friendslocation.entity.UserPublicProfile

/**
 * List used for adapters
 */
object UserDataDao {


    // TODO move all this to Room database


    /**
     * User friends are saved here.
     */
    var friendsList: MutableList<UserPublicProfile> = mutableListOf()

    /**
     * Temporary user public profiles are saved here.
     *
     * E.g.:
     * - search users
     * - friend requests profiles
     */
    var userPublicProfilesList: MutableList<UserPublicProfile> = mutableListOf()

    /**
     * List of friends whose location is being tracked.
     */
    var trackingFriendsList: MutableList<UserPublicProfile> = mutableListOf()

    /**
     * Friends locations are saved here.
     */
    var locationsList: MutableList<Location> = mutableListOf()

}

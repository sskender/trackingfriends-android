package com.example.friendslocation.dao

import com.example.friendslocation.entity.Location
import com.example.friendslocation.entity.UserPublicProfile

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
     * And their locations.
     *
     * String is user id so each location can be connected to username,
     */
    var trackingFriendsMap: MutableMap<String, UserPublicProfile> = mutableMapOf()
    var friendsLocationsMap: MutableMap<String, Location?> = mutableMapOf()

}

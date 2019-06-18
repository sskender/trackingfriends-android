package com.example.friendslocation.entity

import java.io.Serializable

/**
 * User's public profile class
 *
 * Object of this type is coming from the server.
 * All other user's profiles will be displayed like this.
 */
data class UserPublicProfile(
    var userId: String,
    var username: String
) : Serializable

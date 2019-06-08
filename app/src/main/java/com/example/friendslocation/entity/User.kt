package com.example.friendslocation.entity

import java.io.Serializable

/**
 * User class
 *
 * Object of this type is created when registering new user.
 * userId shall not be provided and will be overwritten on server.
 */
data class User(
    var userId: String,
    var displayName: String,
    var email: String,
    var password: String
) : Serializable {
    constructor(displayName: String, email: String, password: String) : this("userId", displayName, email, password)
}

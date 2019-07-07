package com.example.friendslocation.entity

import java.io.Serializable

/**
 * Location class
 *
 * Object of this type is used to send user's location to server,
 * and retrieve other user's locations as well.
 *
 * In case of sending location use id of logged in user and his gps coordinates.
 */
data class Location(
    var userId: String,
    var latitude: Double,
    var longitude: Double
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Location

        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        return userId.hashCode()
    }

}

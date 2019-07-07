package com.example.friendslocation.config

class AppConstants {

    companion object {

        // this ip is only valid for emulator
        //private const val SERVER_IP = "10.0.2.2"

        private const val SERVER_IP = "192.168.1.4"
        private const val SERVER_PORT = "8080"

        // server base url
        const val SERVER_URL: String = "http://$SERVER_IP:$SERVER_PORT/api/v1/"


        // login register verification
        const val PASSWORD_MIN_LEN = 8


        // friend requests
        const val FRIEND_SEND_REQUEST: String = "request"
        const val FRIEND_ACCEPT_REQUEST: String = "accept"
        const val FRIEND_DENY_REQUEST: String = "deny"


        // intent data
        const val USER_PROFILE_INTENT_EXTRA: String = "userPublicProfileData"


        // location
        const val LOCATION_REQUEST_INTERVAL = 1000
        const val MY_PERMISSIONS_REQUEST_FINE_LOCATION = 0
        const val MAP_ZOOM_RATE = 16.0f

    }

}

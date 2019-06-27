package com.example.friendslocation.misc

class AppConstants {

    companion object {

        // this ip is only valid for emulator
        private const val SERVER_IP = "10.129.136.155"
        private const val SERVER_PORT = "8080"

        // server base url
        const val SERVER_URL: String = "http://$SERVER_IP:$SERVER_PORT/api/v1/"


        // login register verification
        const val PASSWORD_MIN_LEN = 8


        // friend request
        const val FRIEND_SEND_REQUEST: String = "request"
        const val FRIEND_ACCEPT_REQUEST: String = "accept"
        const val FRIEND_DENY_REQUEST: String = "deny"


        // intent data
        const val USER_PROFILE_INTENT_EXTRA: String = "userPublicProfileData"

    }

}

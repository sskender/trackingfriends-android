package com.example.friendslocation.net

import com.example.friendslocation.net.retrofit.RestRetrofit

object RestFactory {

    // this ip is only valid for emulator
    const val SERVER_IP = "10.0.2.2"
    const val SERVER_PORT = "8080"

    val instance: RestInterface
        get() = RestRetrofit()

}

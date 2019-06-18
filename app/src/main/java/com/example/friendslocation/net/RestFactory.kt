package com.example.friendslocation.net

import com.example.friendslocation.net.retrofit.RestRetrofit

object RestFactory {

    val instance: RestInterface
        get() = RestRetrofit()

}

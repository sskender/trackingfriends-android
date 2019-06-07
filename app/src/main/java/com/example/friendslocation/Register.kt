package com.example.friendslocation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class Register: AppCompatActivity() {
    var username: String?=null
    var password: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_view)


    }
}
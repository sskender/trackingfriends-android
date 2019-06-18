package com.example.friendslocation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import kotlinx.android.synthetic.main.login_view.*

class LoginActivity: AppCompatActivity() {
    var username: String?=null
    var password: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_view)




        loginButton.setOnClickListener { view ->

            username = (findViewById(R.id.usernameLogin)as EditText).text.toString()
            password = (findViewById(R.id.passwordLogin)as EditText).text.toString()

            Log.d(username, password)

            var i:Intent = Intent(this, HomeActivity::class.java)
            startActivity(i)

        }


    }
}
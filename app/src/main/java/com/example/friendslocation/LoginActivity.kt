package com.example.friendslocation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.friendslocation.entity.User
import com.example.friendslocation.tasks.LoginUserTask
import kotlinx.android.synthetic.main.login_view.*
import java.lang.ref.WeakReference

class LoginActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_view)


        // login

        loginButton.setOnClickListener { view ->
            val email = emailLogin.text.toString()
            val password = passwordLogin.text.toString()

            val user: User = User("", "", email, password)

            LoginUserTask(WeakReference(this)).execute(user)
        }


        // start register activity

        registerButton.setOnClickListener {
            val registerActivityIntent = Intent(this, RegisterActivity::class.java)

            startActivity(registerActivityIntent)
        }

    }

}

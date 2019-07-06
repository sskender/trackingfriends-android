package com.example.friendslocation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.friendslocation.config.AppConstants
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

            if (email.isEmpty() || password.length < AppConstants.PASSWORD_MIN_LEN) {
                Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_LONG).show()
            } else {
                val user: User = User("", "", email, password)

                LoginUserTask(WeakReference(this)).execute(user)
            }
        }


        // start register activity

        registerButton.setOnClickListener {
            val registerActivityIntent = Intent(this, RegisterActivity::class.java)

            startActivity(registerActivityIntent)
        }

    }

}

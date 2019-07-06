package com.example.friendslocation

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.friendslocation.config.AppConstants
import com.example.friendslocation.entity.User
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory
import kotlinx.android.synthetic.main.login_view.*

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

                LoginUserTask().execute(user)
            }
        }


        // start register activity

        registerButton.setOnClickListener {
            val registerActivityIntent = Intent(this, RegisterActivity::class.java)

            startActivity(registerActivityIntent)
        }

    }


    /**
     * Login user task
     */
    inner class LoginUserTask :
        AsyncTask<User, Unit, UserPublicProfile?>() {

        override fun doInBackground(vararg params: User): UserPublicProfile? {
            val rest = RestFactory.instance

            return rest.loginUser(params[0])
        }

        override fun onPostExecute(result: UserPublicProfile?) {

            if (result == null) {
                Toast.makeText(this@LoginActivity, "Invalid credentials!", Toast.LENGTH_LONG).show()

            } else {
                // start home activity
                // pass user public profile retrieved from server

                val homeActivityWithExtra = Intent(this@LoginActivity, HomeActivity::class.java)
                homeActivityWithExtra.putExtra(AppConstants.USER_PROFILE_INTENT_EXTRA, result)

                startActivity(homeActivityWithExtra)

            }

        }

    }

}

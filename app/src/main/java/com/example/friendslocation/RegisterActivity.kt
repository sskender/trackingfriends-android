package com.example.friendslocation

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.example.friendslocation.config.AppConstants
import com.example.friendslocation.entity.User
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory
import kotlinx.android.synthetic.main.register_view.*

class RegisterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_view)


        registerButton.setOnClickListener { view ->

            val username = (findViewById<EditText>(R.id.usernameRegister)).text.toString()
            val password1 = (findViewById<EditText>(R.id.passwordOneRegister)).text.toString()
            val password2 = (findViewById<EditText>(R.id.passwordTwoRegister)).text.toString()
            val email = (findViewById<EditText>(R.id.emailRegister)).text.toString()

            if (username.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Fields can't be empty!", Toast.LENGTH_LONG).show()
            } else if (password1 != password2) {
                Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_LONG).show()
            } else if (password1.length < AppConstants.PASSWORD_MIN_LEN) {
                Toast.makeText(this, "Password is too short!", Toast.LENGTH_LONG).show()
            }
            else{
                val user: User = User("", username, email, password1)

                RegisterUserTask().execute(user)
            }

        }


    }


    /**
     * Register user task
     */
    inner class RegisterUserTask :
        AsyncTask<User, Unit, UserPublicProfile?>() {

        override fun doInBackground(vararg params: User): UserPublicProfile? {
            val rest = RestFactory.instance

            return rest.registerUser(params[0])
        }

        override fun onPostExecute(result: UserPublicProfile?) {

            if (result == null) {
                Toast.makeText(this@RegisterActivity, "Email already taken!", Toast.LENGTH_LONG).show()
            } else {
                // start home activity
                // pass user public profile retrieved from server

                val homeActivityWithExtra = Intent(this@RegisterActivity, HomeActivity::class.java)
                homeActivityWithExtra.putExtra(AppConstants.USER_PROFILE_INTENT_EXTRA, result)

                startActivity(homeActivityWithExtra)

            }

        }

    }

}

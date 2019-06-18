package com.example.friendslocation.tasks

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.widget.Toast
import com.example.friendslocation.HomeActivity
import com.example.friendslocation.entity.User
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.misc.AppConstants
import com.example.friendslocation.net.RestFactory
import java.lang.ref.WeakReference

class LoginUserTask(private val contextReference: WeakReference<Context>) :
    AsyncTask<User, Unit, UserPublicProfile?>() {

    override fun doInBackground(vararg params: User): UserPublicProfile? {
        val rest = RestFactory.instance
        return rest.loginUser(params[0])
    }

    override fun onPostExecute(result: UserPublicProfile?) {

        if (result == null) {
            Toast.makeText(contextReference.get(), "Invalid credentials!", Toast.LENGTH_LONG).show()

        } else {
            // start home activity
            // pass user public profile retrieved from server

            val homeActivityWithExtra = Intent(contextReference.get(), HomeActivity::class.java)
            homeActivityWithExtra.putExtra(AppConstants.USER_PROFILE_INTENT_EXTRA, result)

            (contextReference.get() as Activity).startActivity(homeActivityWithExtra)

        }
    }

}

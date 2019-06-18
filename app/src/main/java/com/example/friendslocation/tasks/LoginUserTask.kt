package com.example.friendslocation.tasks

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.example.friendslocation.entity.User
import com.example.friendslocation.entity.UserPublicProfile
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
            Toast.makeText(contextReference.get(), "Invalid credentials", Toast.LENGTH_LONG).show()
        } else {
            Log.d("USER", result.toString())
        }
    }

}

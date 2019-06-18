package com.example.friendslocation.tasks

import android.os.AsyncTask
import com.example.friendslocation.entity.User
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory

class LoginUserTask : AsyncTask<User, Unit, UserPublicProfile?>() {

    override fun doInBackground(vararg params: User): UserPublicProfile? {
        val rest = RestFactory.instance
        return rest.loginUser(params[0])
    }

    override fun onPostExecute(result: UserPublicProfile?) {
        super.onPostExecute(result)
        // TODO return result?.userId
    }

}

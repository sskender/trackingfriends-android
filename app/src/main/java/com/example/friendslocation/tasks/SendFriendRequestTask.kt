package com.example.friendslocation.tasks

import android.os.AsyncTask
import com.example.friendslocation.net.RestFactory

class SendFriendRequestTask(private val userId: String) : AsyncTask<String, Unit, Unit?>() {

    override fun doInBackground(vararg p0: String): Unit? {
        val rest = RestFactory.instance

        return rest.sendFriendRequest(userId, p0[0])
    }

}

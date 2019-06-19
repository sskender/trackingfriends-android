package com.example.friendslocation.tasks

import android.os.AsyncTask
import com.example.friendslocation.entity.Location
import com.example.friendslocation.net.RestFactory

class GetFriendLocationTask(private val userId: String) :
    AsyncTask<String, Unit, Location?>() {

    override fun doInBackground(vararg p0: String): Location? {
        val rest = RestFactory.instance

        return rest.getFriendsLocation(userId, p0[0])
    }

    override fun onPostExecute(result: Location?) {
        super.onPostExecute(result)

        // TODO return result

    }

}

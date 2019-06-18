package com.example.friendslocation.tasks

import android.os.AsyncTask
import com.example.friendslocation.entity.Location
import com.example.friendslocation.net.RestFactory

class GetFriendLocationTask : AsyncTask<Pair<String, String>, Unit, Location>() {

    override fun doInBackground(vararg params: Pair<String, String>): Location? {
        val rest = RestFactory.instance
        return rest.getFriendsLocation(params[0].first, params[0].second)
    }

    override fun onPostExecute(result: Location?) {
        super.onPostExecute(result)
        // TODO return result
    }

}

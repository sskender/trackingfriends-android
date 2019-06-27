package com.example.friendslocation.tasks

import android.os.AsyncTask
import com.example.friendslocation.adapter.SearchUsersAdapter
import com.example.friendslocation.dao.UserPublicProfileList
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory

class SearchUsersTask(
    private val userId: String,
    private val searchUsersAdapter: SearchUsersAdapter
) :
    AsyncTask<String, Unit, List<UserPublicProfile>?>() {

    override fun doInBackground(vararg p0: String): List<UserPublicProfile>? {
        val rest = RestFactory.instance

        return rest.searchUsersForFriends(userId, p0[0])
    }

    override fun onPostExecute(result: List<UserPublicProfile>?) {

        if (result != null) {
            UserPublicProfileList.list = result as MutableList<UserPublicProfile>
        }

        // update adapter
        searchUsersAdapter.notifyDataSetChanged()

        super.onPostExecute(result)
    }

}

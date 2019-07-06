package com.example.friendslocation.tasks

import android.os.AsyncTask
import com.example.friendslocation.adapter.SearchUsersAdapter
import com.example.friendslocation.dao.UserListDao
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory

class LoadSearchUsersTask(
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
            UserListDao.publicProfileList = result as MutableList<UserPublicProfile>
        }

        // update adapter
        searchUsersAdapter.notifyDataSetChanged()

        super.onPostExecute(result)
    }

}

package com.example.friendslocation.tasks

import android.os.AsyncTask
import com.example.friendslocation.adapter.FriendRequestsAdapter
import com.example.friendslocation.dao.UserListDao
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory

class LoadFriendRequestsTask(
    private val userId: String,
    private val friendRequestsAdapter: FriendRequestsAdapter
) :
    AsyncTask<Unit, Unit, List<UserPublicProfile>?>() {

    override fun onPostExecute(result: List<UserPublicProfile>?) {

        if (result != null) {
            UserListDao.publicProfileList = result as MutableList<UserPublicProfile>
        }

        // update adapter
        friendRequestsAdapter.notifyDataSetChanged()

        super.onPostExecute(result)
    }

    override fun doInBackground(vararg p0: Unit?): List<UserPublicProfile>? {
        val rest = RestFactory.instance

        return rest.getUserFriendRequests(userId)
    }

}

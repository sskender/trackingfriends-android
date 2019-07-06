package com.example.friendslocation.tasks

import android.os.AsyncTask
import com.example.friendslocation.adapter.FriendsAdapter
import com.example.friendslocation.dao.UserListDao
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory

class LoadFriendsTask(
    private val userId: String,
    private val friendsAdapter: FriendsAdapter
) :
    AsyncTask<Unit, Unit, List<UserPublicProfile>?>() {

    override fun doInBackground(vararg p0: Unit?): List<UserPublicProfile>? {
        val rest = RestFactory.instance

        return rest.getUserFriends(userId)
    }

    override fun onPostExecute(result: List<UserPublicProfile>?) {

        if (result != null) {
            UserListDao.publicProfileList = result as MutableList<UserPublicProfile>
        }

        // update adapter
        friendsAdapter.notifyDataSetChanged()

        super.onPostExecute(result)
    }
}

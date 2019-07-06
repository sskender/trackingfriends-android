package com.example.friendslocation

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.friendslocation.adapter.FriendRequestsAdapter
import com.example.friendslocation.config.AppConstants
import com.example.friendslocation.dao.UserDataDao
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory
import kotlinx.android.synthetic.main.activity_friend_requests.*

class FriendRequestsActivity : AppCompatActivity() {

    // here is stored profile of a currently logged in user
    // use it as reference for each api call
    private lateinit var loggedInUser: UserPublicProfile

    // adapter
    private lateinit var friendRequestsAdapter: FriendRequestsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_requests)


        // grab currently logged in user from activity extra
        loggedInUser = intent.extras?.get(AppConstants.USER_PROFILE_INTENT_EXTRA) as UserPublicProfile


        // set adapter
        friendRequestsRecyclerView.layoutManager = LinearLayoutManager(this)
        friendRequestsAdapter = FriendRequestsAdapter(loggedInUser)
        friendRequestsRecyclerView.adapter = friendRequestsAdapter


        // load friend requests
        LoadFriendRequestsTask().execute()
    }


    /**
     * Load friends task
     */
    inner class LoadFriendRequestsTask :
        AsyncTask<Unit, Unit, List<UserPublicProfile>?>() {

        override fun onPostExecute(result: List<UserPublicProfile>?) {

            if (result != null) {
                UserDataDao.userPublicProfilesList = result as MutableList<UserPublicProfile>
            }

            // update adapter
            friendRequestsAdapter.notifyDataSetChanged()

            super.onPostExecute(result)
        }

        override fun doInBackground(vararg p0: Unit?): List<UserPublicProfile>? {
            val rest = RestFactory.instance

            return rest.getUserFriendRequests(loggedInUser.userId)
        }

    }

}

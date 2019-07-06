package com.example.friendslocation

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.friendslocation.adapter.FriendsAdapter
import com.example.friendslocation.config.AppConstants
import com.example.friendslocation.dao.UserDataDao
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory
import kotlinx.android.synthetic.main.activity_friends.*

class FriendsActivity : AppCompatActivity() {

    // here is stored profile of a currently logged in user
    // use it as reference for each api call
    private lateinit var loggedInUser: UserPublicProfile

    // adapter
    private lateinit var friendsAdapter: FriendsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)


        // grab currently logged in user from activity extra
        loggedInUser = intent.extras?.get(AppConstants.USER_PROFILE_INTENT_EXTRA) as UserPublicProfile


        // set adapter
        friendsRecyclerView.layoutManager = LinearLayoutManager(this)
        friendsAdapter = FriendsAdapter(loggedInUser)
        friendsRecyclerView.adapter = friendsAdapter


        // load friends
        // TODO save this to local database
        LoadFriendsTask().execute()


        // open map button
        showMapButton.setOnClickListener {
            var i: Intent = Intent(this, MapActivity::class.java)
            startActivity(i)
        }

    }


    /**
     * Load friends task
     */
    inner class LoadFriendsTask :
        AsyncTask<Unit, Unit, List<UserPublicProfile>?>() {

        override fun doInBackground(vararg p0: Unit?): List<UserPublicProfile>? {
            val rest = RestFactory.instance

            return rest.getUserFriends(loggedInUser.userId)
        }

        override fun onPostExecute(result: List<UserPublicProfile>?) {

            if (result != null) {
                UserDataDao.friendsList = result as MutableList<UserPublicProfile>
            }

            // update adapter
            friendsAdapter.notifyDataSetChanged()

            super.onPostExecute(result)
        }

    }

}

package com.example.friendslocation

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.friendslocation.adapter.SearchUsersAdapter
import com.example.friendslocation.config.AppConstants
import com.example.friendslocation.dao.UserDataDao
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory
import kotlinx.android.synthetic.main.activity_seach_users.*

class SearchUsersActivity : AppCompatActivity() {

    // here is stored profile of a currently logged in user
    // use it as reference for each api call
    private lateinit var loggedInUser: UserPublicProfile

    // adapter
    private lateinit var searchUsersAdapter: SearchUsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seach_users)


        // grab currently logged in user from activity extra
        loggedInUser = intent.extras?.get(AppConstants.USER_PROFILE_INTENT_EXTRA) as UserPublicProfile


        // set adapter
        searchUsersRecyclerView.layoutManager = LinearLayoutManager(this)
        searchUsersAdapter = SearchUsersAdapter(loggedInUser)
        searchUsersRecyclerView.adapter = searchUsersAdapter


        // trigger search button
        searchUsersButton.setOnClickListener {
            val friendId: String = searchUsersEditText.text.toString()

            LoadSearchUsersTask().execute(friendId)
        }

    }


    /**
     * Search users task
     */
    inner class LoadSearchUsersTask :
        AsyncTask<String, Unit, List<UserPublicProfile>?>() {

        override fun doInBackground(vararg p0: String): List<UserPublicProfile>? {
            val rest = RestFactory.instance

            return rest.searchUsersForFriends(loggedInUser.userId, p0[0])
        }

        override fun onPostExecute(result: List<UserPublicProfile>?) {

            if (result != null) {
                UserDataDao.userPublicProfilesList = result as MutableList<UserPublicProfile>
            }

            // update adapter
            searchUsersAdapter.notifyDataSetChanged()

            super.onPostExecute(result)
        }

    }

}

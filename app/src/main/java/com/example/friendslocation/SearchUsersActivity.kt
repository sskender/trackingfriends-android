package com.example.friendslocation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.friendslocation.adapter.SearchUsersAdapter
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.misc.AppConstants
import com.example.friendslocation.tasks.SearchUsersTask
import kotlinx.android.synthetic.main.activity_seach_users.*

class SearchUsersActivity : AppCompatActivity() {

    // here is stored profile of a currently logged in user
    // use it as reference for each api call
    private lateinit var userPublicProfile: UserPublicProfile

    // adapter
    private lateinit var searchUsersAdapter: SearchUsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seach_users)


        // grab currently logged in user from activity extra
        userPublicProfile = intent.extras?.get(AppConstants.USER_PROFILE_INTENT_EXTRA) as UserPublicProfile


        // set adapter
        searchUsersRecyclerView.layoutManager = LinearLayoutManager(this)
        searchUsersAdapter = SearchUsersAdapter(userPublicProfile)
        searchUsersRecyclerView.adapter = searchUsersAdapter


        // trigger search button
        searchUsersButton.setOnClickListener {
            val friendId: String = searchUsersEditText.text.toString()

            SearchUsersTask(userPublicProfile.userId, searchUsersAdapter).execute(friendId)
        }

    }

}

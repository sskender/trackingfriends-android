package com.example.friendslocation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.friendslocation.adapter.FriendRequestsAdapter
import com.example.friendslocation.config.AppConstants
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.tasks.LoadFriendRequestsTask
import kotlinx.android.synthetic.main.activity_friend_requests.*

class FriendRequestsActivity : AppCompatActivity() {

    // here is stored profile of a currently logged in user
    // use it as reference for each api call
    private lateinit var userPublicProfile: UserPublicProfile

    // adapter
    private lateinit var friendRequestsAdapter: FriendRequestsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_requests)


        // grab currently logged in user from activity extra
        userPublicProfile = intent.extras?.get(AppConstants.USER_PROFILE_INTENT_EXTRA) as UserPublicProfile


        // set adapter
        friendRequestsRecyclerView.layoutManager = LinearLayoutManager(this)
        friendRequestsAdapter = FriendRequestsAdapter(userPublicProfile)
        friendRequestsRecyclerView.adapter = friendRequestsAdapter


        // load friend requests
        LoadFriendRequestsTask(userPublicProfile.userId, friendRequestsAdapter).execute()
    }

}

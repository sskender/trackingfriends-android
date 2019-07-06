package com.example.friendslocation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.friendslocation.adapter.FriendsAdapter
import com.example.friendslocation.config.AppConstants
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.tasks.LoadFriendsTask
import kotlinx.android.synthetic.main.activity_friends.*

class FriendsActivity : AppCompatActivity() {

    // here is stored profile of a currently logged in user
    // use it as reference for each api call
    private lateinit var userPublicProfile: UserPublicProfile

    // adapter
    private lateinit var friendsAdapter: FriendsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)


        // grab currently logged in user from activity extra
        userPublicProfile = intent.extras?.get(AppConstants.USER_PROFILE_INTENT_EXTRA) as UserPublicProfile


        // set adapter
        friendsRecyclerView.layoutManager = LinearLayoutManager(this)
        friendsAdapter = FriendsAdapter(userPublicProfile)
        friendsRecyclerView.adapter = friendsAdapter


        // load friends
        LoadFriendsTask(userPublicProfile.userId, friendsAdapter).execute()

        // TODO save this to local database
    }

}

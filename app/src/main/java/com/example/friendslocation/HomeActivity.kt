package com.example.friendslocation

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.friendslocation.config.AppConstants
import com.example.friendslocation.entity.UserPublicProfile
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    // here is stored profile of a currently logged in user
    // use it as reference for each api call
    private lateinit var loggedInUser: UserPublicProfile


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)


        // assign user profile
        loggedInUser = intent.extras?.get(AppConstants.USER_PROFILE_INTENT_EXTRA) as UserPublicProfile



        buttonMap.setOnClickListener { view ->

            val mapActivityWithExtra = Intent(this, MapActivity::class.java)
            mapActivityWithExtra.putExtra(AppConstants.USER_PROFILE_INTENT_EXTRA, loggedInUser)

            startActivity(mapActivityWithExtra)
        }


        /*
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            var i:Intent = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
        */


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> {
                var i:Intent = Intent(this, LoginActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        when (item.itemId) {
            R.id.nav_findNewFriends -> {
                // handle users search activity

                val searchUsersActivityWithExtra = Intent(this, SearchUsersActivity::class.java)

                searchUsersActivityWithExtra.putExtra(AppConstants.USER_PROFILE_INTENT_EXTRA, loggedInUser)

                startActivity(searchUsersActivityWithExtra)
            }
            R.id.nav_friendRequests -> {
                // handle pending friend requests activity

                val friendRequestsActivityWithExtra = Intent(this, FriendRequestsActivity::class.java)

                friendRequestsActivityWithExtra.putExtra(AppConstants.USER_PROFILE_INTENT_EXTRA, loggedInUser)

                startActivity(friendRequestsActivityWithExtra)
            }
            R.id.nav_myFriends -> {
                // handle user friends activity

                val friendsActivityWithExtra = Intent(this, FriendsActivity::class.java)

                friendsActivityWithExtra.putExtra(AppConstants.USER_PROFILE_INTENT_EXTRA, loggedInUser)

                startActivity(friendsActivityWithExtra)
            }
            R.id.nav_share -> {
                var i:Intent = Intent(this, RegisterActivity::class.java)
                startActivity(i)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
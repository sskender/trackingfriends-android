package com.example.friendslocation.adapter

import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.friendslocation.R
import com.example.friendslocation.dao.UserDataDao
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory

class FriendRequestsAdapter(private val loggedInUser: UserPublicProfile) :
    RecyclerView.Adapter<FriendRequestsAdapter.FriendRequestAdapterHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FriendRequestAdapterHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val userListElement = inflater.inflate(R.layout.friend_request_item_in_list, p0, false)

        return FriendRequestAdapterHolder(userListElement)
    }


    override fun getItemCount(): Int {
        return UserDataDao.userPublicProfilesList.size
    }


    override fun onBindViewHolder(p0: FriendRequestAdapterHolder, p1: Int) {
        val currentFriendRequestPublicProfile: UserPublicProfile = UserDataDao.userPublicProfilesList[p1]

        // grab username from object
        p0.usernameText?.text = currentFriendRequestPublicProfile.username

        // accept friend request
        p0.acceptFriendRequestButton?.setOnClickListener {
            AcceptFriendRequestTask().execute(currentFriendRequestPublicProfile)

            val msg = "Friend accepted!"
            Toast.makeText(p0.itemView.context, msg, Toast.LENGTH_SHORT).show()
        }

        // deny friend request
        p0.denyFriendRequestButton?.setOnClickListener {
            DenyFriendRequestTask().execute(currentFriendRequestPublicProfile)

            val msg = "Friend denied!"
            Toast.makeText(p0.itemView.context, msg, Toast.LENGTH_SHORT).show()
        }

    }


    /**
     * Holder class
     */
    inner class FriendRequestAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var usernameText: TextView? = null
        var acceptFriendRequestButton: Button? = null
        var denyFriendRequestButton: Button? = null

        init {
            usernameText = itemView.findViewById(R.id.friendRequestUsernameTextView)
            acceptFriendRequestButton = itemView.findViewById(R.id.acceptFriendButton)
            denyFriendRequestButton = itemView.findViewById(R.id.denyFriendButton)
        }

    }


    /**
     * Accept friendship task
     */
    inner class AcceptFriendRequestTask : AsyncTask<UserPublicProfile, Unit, Unit?>() {

        override fun doInBackground(vararg p0: UserPublicProfile): Unit? {
            val rest = RestFactory.instance
            val friendId = p0[0].userId

            UserDataDao.userPublicProfilesList.remove(p0[0])

            return rest.acceptFriendRequest(loggedInUser.userId, friendId)
        }

        override fun onPostExecute(result: Unit?) {
            notifyDataSetChanged()

            super.onPostExecute(result)
        }

    }


    /**
     * Deny friendship task
     */
    inner class DenyFriendRequestTask : AsyncTask<UserPublicProfile, Unit, Unit?>() {

        override fun doInBackground(vararg p0: UserPublicProfile): Unit? {
            val rest = RestFactory.instance
            val friendId = p0[0].userId

            UserDataDao.userPublicProfilesList.remove(p0[0])

            return rest.denyFriendRequest(loggedInUser.userId, friendId)
        }

        override fun onPostExecute(result: Unit?) {
            notifyDataSetChanged()

            super.onPostExecute(result)
        }

    }


}

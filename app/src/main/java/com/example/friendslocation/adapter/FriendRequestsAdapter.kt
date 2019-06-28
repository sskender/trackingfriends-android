package com.example.friendslocation.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.friendslocation.R
import com.example.friendslocation.dao.UserPublicProfileList
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.tasks.AcceptFriendRequestTask
import com.example.friendslocation.tasks.DenyFriendRequestTask

class FriendRequestsAdapter(private val userPublicProfile: UserPublicProfile) :
    RecyclerView.Adapter<FriendRequestsAdapter.FriendRequestAdapterHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FriendRequestAdapterHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val userListElement = inflater.inflate(R.layout.friend_request_item_in_list, p0, false)

        return FriendRequestAdapterHolder(userListElement)
    }

    override fun getItemCount(): Int {
        return UserPublicProfileList.list.size
    }

    override fun onBindViewHolder(p0: FriendRequestAdapterHolder, p1: Int) {
        val currentFriendRequestPublicProfile: UserPublicProfile = UserPublicProfileList.list[p1]

        // grab username from object
        p0.usernameText?.text = currentFriendRequestPublicProfile.username

        // accept friend request
        p0.acceptFriendRequestButton?.setOnClickListener {
            AcceptFriendRequestTask(userPublicProfile.userId).execute(currentFriendRequestPublicProfile.userId)
        }

        // deny friend request
        p0.denyFriendRequestButton?.setOnClickListener {
            DenyFriendRequestTask(userPublicProfile.userId).execute(currentFriendRequestPublicProfile.userId)
        }

        // open map on tap
        p0.itemView.setOnClickListener {
            // TODO open map when user tapped
            Log.d("FRIEND", "opening location for friend with id ${currentFriendRequestPublicProfile.userId}")
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
            usernameText = itemView.findViewById(R.id.friendUsernameTextView)
            acceptFriendRequestButton = itemView.findViewById(R.id.acceptFriendButton)
            denyFriendRequestButton = itemView.findViewById(R.id.denyFriendButton)
        }

    }

}

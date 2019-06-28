package com.example.friendslocation.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.friendslocation.R
import com.example.friendslocation.dao.UserPublicProfileList
import com.example.friendslocation.entity.UserPublicProfile

class FriendsAdapter(private val userPublicProfile: UserPublicProfile) :
    RecyclerView.Adapter<FriendsAdapter.FriendAdapterHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FriendAdapterHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val userListElement = inflater.inflate(R.layout.friend_item_in_list, p0, false)

        return FriendAdapterHolder(userListElement)
    }

    override fun getItemCount(): Int {
        return UserPublicProfileList.list.size
    }

    override fun onBindViewHolder(p0: FriendAdapterHolder, p1: Int) {
        val currentFriendRequestPublicProfile: UserPublicProfile = UserPublicProfileList.list[p1]

        // grab username from object
        p0.usernameText?.text = currentFriendRequestPublicProfile.username

        // open map on tap
        p0.itemView.setOnClickListener {
            // TODO open map when user tapped
            Log.d("FRIEND", "opening location for friend with id ${currentFriendRequestPublicProfile.userId}")
        }

    }


    /**
     * Holder adapter
     */
    inner class FriendAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var usernameText: TextView? = null

        init {
            usernameText = itemView.findViewById(R.id.friendRequestUsernameTextView)
        }

    }

}

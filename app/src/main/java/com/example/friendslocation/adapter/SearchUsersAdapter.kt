package com.example.friendslocation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.friendslocation.R
import com.example.friendslocation.dao.UserPublicProfileList
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.tasks.SendFriendRequestTask

class SearchUsersAdapter(private val userId: String) :
    RecyclerView.Adapter<SearchUsersAdapter.SearchUsersAdapterHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchUsersAdapterHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val userListElement = inflater.inflate(R.layout.search_user_item_in_list, p0, false)

        return SearchUsersAdapterHolder(userListElement)
    }

    override fun getItemCount(): Int {
        return UserPublicProfileList.list.size
    }

    override fun onBindViewHolder(p0: SearchUsersAdapterHolder, p1: Int) {
        val currentSearchUserPublicProfile: UserPublicProfile = UserPublicProfileList.list[p1]

        // grab username from object
        p0.usernameText?.text = currentSearchUserPublicProfile.username

        // .itemView.setOnClickListener
        p0.addFriendImageButton?.setOnClickListener {
            // call server
            SendFriendRequestTask(userId).execute(currentSearchUserPublicProfile.userId)

            // TODO change icon when friend request send
        }

    }


    /**
     * Holder class
     */
    class SearchUsersAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var usernameText: TextView? = null
        var addFriendImageButton: ImageView? = null

        init {
            usernameText = itemView.findViewById(R.id.usernameTextView)
            addFriendImageButton = itemView.findViewById(R.id.addFriendImageButton)
        }

    }

}

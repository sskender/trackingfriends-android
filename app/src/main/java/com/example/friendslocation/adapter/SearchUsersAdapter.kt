package com.example.friendslocation.adapter

import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.friendslocation.R
import com.example.friendslocation.dao.UserListDao
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory

class SearchUsersAdapter(private val userPublicProfile: UserPublicProfile) :
    RecyclerView.Adapter<SearchUsersAdapter.SearchUserAdapterHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchUserAdapterHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val userListElement = inflater.inflate(R.layout.search_username_item_in_list, p0, false)

        return SearchUserAdapterHolder(userListElement)
    }

    override fun getItemCount(): Int {
        return UserListDao.publicProfileList.size
    }

    override fun onBindViewHolder(p0: SearchUserAdapterHolder, p1: Int) {
        val currentSearchUserPublicProfile: UserPublicProfile = UserListDao.publicProfileList[p1]

        // grab username from object
        p0.usernameText?.text = currentSearchUserPublicProfile.username

        // send friend request
        p0.addFriendImageButton?.setOnClickListener {
            SendFriendRequestTask(userPublicProfile.userId).execute(currentSearchUserPublicProfile.userId)

            Toast.makeText(p0.itemView.context, "Friend added!", Toast.LENGTH_SHORT).show()

            // disable button after click
            p0.addFriendImageButton?.isClickable = false
        }

    }


    /**
     * Holder class
     */
    inner class SearchUserAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var usernameText: TextView? = null
        var addFriendImageButton: ImageView? = null

        init {
            usernameText = itemView.findViewById(R.id.usernameTextView)
            addFriendImageButton = itemView.findViewById(R.id.addFriendImageButton)
        }

    }


    /**
     * Send friend request task
     */
    inner class SendFriendRequestTask(private val userId: String) : AsyncTask<String, Unit, Unit?>() {

        override fun doInBackground(vararg p0: String): Unit? {
            val rest = RestFactory.instance

            return rest.sendFriendRequest(userId, p0[0])
        }

    }

}

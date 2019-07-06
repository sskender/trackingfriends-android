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
import com.example.friendslocation.dao.UserListDao
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory

class FriendRequestsAdapter(private val userPublicProfile: UserPublicProfile) :
    RecyclerView.Adapter<FriendRequestsAdapter.FriendRequestAdapterHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FriendRequestAdapterHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val userListElement = inflater.inflate(R.layout.friend_request_item_in_list, p0, false)

        return FriendRequestAdapterHolder(userListElement)
    }

    override fun getItemCount(): Int {
        return UserListDao.publicProfileList.size
    }

    override fun onBindViewHolder(p0: FriendRequestAdapterHolder, p1: Int) {
        val currentFriendRequestPublicProfile: UserPublicProfile = UserListDao.publicProfileList[p1]

        // grab username from object
        p0.usernameText?.text = currentFriendRequestPublicProfile.username

        // accept friend request
        p0.acceptFriendRequestButton?.setOnClickListener {
            AcceptFriendRequestTask(userPublicProfile.userId).execute(currentFriendRequestPublicProfile)

            Toast.makeText(p0.itemView.context, "Friend accepted!", Toast.LENGTH_SHORT).show()
        }

        // deny friend request
        p0.denyFriendRequestButton?.setOnClickListener {
            DenyFriendRequestTask(userPublicProfile.userId).execute(currentFriendRequestPublicProfile)

            Toast.makeText(p0.itemView.context, "Friend denied!", Toast.LENGTH_SHORT).show()
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
    inner class AcceptFriendRequestTask(private val userId: String) : AsyncTask<UserPublicProfile, Unit, Unit?>() {

        override fun doInBackground(vararg p0: UserPublicProfile): Unit? {
            val rest = RestFactory.instance
            val friendId = p0[0].userId

            UserListDao.publicProfileList.remove(p0[0])

            return rest.acceptFriendRequest(userId, friendId)
        }

        override fun onPostExecute(result: Unit?) {
            notifyDataSetChanged()

            super.onPostExecute(result)
        }

    }


    /**
     * Deny friendship task
     */
    inner class DenyFriendRequestTask(private val userId: String) : AsyncTask<UserPublicProfile, Unit, Unit?>() {

        override fun doInBackground(vararg p0: UserPublicProfile): Unit? {
            val rest = RestFactory.instance
            val friendId = p0[0].userId

            UserListDao.publicProfileList.remove(p0[0])

            return rest.denyFriendRequest(userId, friendId)
        }

        override fun onPostExecute(result: Unit?) {
            notifyDataSetChanged()

            super.onPostExecute(result)
        }

    }

}

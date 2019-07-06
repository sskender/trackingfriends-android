package com.example.friendslocation.adapter

import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.friendslocation.R
import com.example.friendslocation.dao.UserListDao
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory

class FriendsAdapter(private val userPublicProfile: UserPublicProfile) :
    RecyclerView.Adapter<FriendsAdapter.FriendAdapterHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FriendAdapterHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val userListElement = inflater.inflate(R.layout.friend_item_in_list, p0, false)

        return FriendAdapterHolder(userListElement)
    }

    override fun getItemCount(): Int {
        return UserListDao.publicProfileList.size
    }

    override fun onBindViewHolder(p0: FriendAdapterHolder, p1: Int) {
        val currentFriendRequestPublicProfile: UserPublicProfile = UserListDao.publicProfileList[p1]

        // grab username from object
        p0.usernameText?.text = currentFriendRequestPublicProfile.username

        // open map on tap
        p0.itemView.setOnClickListener {
            // TODO open map when user tapped
            Log.d("FRIEND", "opening location for friend with id ${currentFriendRequestPublicProfile.userId}")
        }

        // delete friend button
        p0.deleteButton?.setOnClickListener {
            DeleteFriendTask(userPublicProfile.userId).execute(currentFriendRequestPublicProfile)

            Toast.makeText(p0.itemView.context, "Unfriended!", Toast.LENGTH_SHORT).show()
        }

    }


    /**
     * Holder adapter
     */
    inner class FriendAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var usernameText: TextView? = null
        var deleteButton: ImageButton? = null

        init {
            usernameText = itemView.findViewById(R.id.friendRequestUsernameTextView)
            deleteButton = itemView.findViewById(R.id.deleteFriendImageButton)
        }

    }


    /**
     * Delete friend task
     */
    inner class DeleteFriendTask(private val userId: String) : AsyncTask<UserPublicProfile, Unit, Unit?>() {

        override fun doInBackground(vararg p0: UserPublicProfile): Unit? {
            val rest = RestFactory.instance
            val friendId: String = p0[0].userId

            UserListDao.publicProfileList.remove(p0[0])

            return rest.deleteFriend(userId, friendId)
        }

        override fun onPostExecute(result: Unit?) {
            notifyDataSetChanged()

            super.onPostExecute(result)
        }

    }

}

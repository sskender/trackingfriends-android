package com.example.friendslocation.adapter

import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.friendslocation.R
import com.example.friendslocation.dao.UserDataDao
import com.example.friendslocation.entity.Location
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory

class FriendsAdapter(private val loggedInUser: UserPublicProfile) :
    RecyclerView.Adapter<FriendsAdapter.FriendAdapterHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FriendAdapterHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val userListElement = inflater.inflate(R.layout.friend_item_in_list, p0, false)

        return FriendAdapterHolder(userListElement)
    }

    override fun getItemCount(): Int {
        return UserDataDao.friendsList.size
    }

    override fun onBindViewHolder(p0: FriendAdapterHolder, p1: Int) {
        val currentFriendRequestPublicProfile: UserPublicProfile = UserDataDao.friendsList[p1]

        // grab username from object
        p0.usernameText?.text = currentFriendRequestPublicProfile.username

        // open map on tap
        p0.itemView.setOnClickListener {
            LoadFriendLocationTask().execute(currentFriendRequestPublicProfile)

            Toast.makeText(p0.itemView.context, "Friend visible on map!", Toast.LENGTH_LONG).show()
        }

        // delete friend button
        p0.deleteButton?.setOnClickListener {
            DeleteFriendTask().execute(currentFriendRequestPublicProfile)

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
     * Load friend's GPS location task
     */
    inner class LoadFriendLocationTask :
        AsyncTask<UserPublicProfile, Unit, Location?>() {

        override fun doInBackground(vararg p0: UserPublicProfile): Location? {
            val rest = RestFactory.instance

            return rest.getFriendsLocation(loggedInUser.userId, p0[0].userId)
        }

        override fun onPostExecute(result: Location?) {
            if (result != null) {
                UserDataDao.friendLocationsList.add(result)
            }

            super.onPostExecute(result)
        }

    }


    /**
     * Delete friend task
     */
    inner class DeleteFriendTask : AsyncTask<UserPublicProfile, Unit, Unit?>() {

        override fun doInBackground(vararg p0: UserPublicProfile): Unit? {
            val rest = RestFactory.instance
            val friendId: String = p0[0].userId

            UserDataDao.friendsList.remove(p0[0])

            return rest.deleteFriend(loggedInUser.userId, friendId)
        }

        override fun onPostExecute(result: Unit?) {
            notifyDataSetChanged()

            super.onPostExecute(result)
        }

    }

}

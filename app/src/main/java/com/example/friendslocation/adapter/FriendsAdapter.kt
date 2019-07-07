package com.example.friendslocation.adapter

import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
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
        val currentFriendPublicProfile: UserPublicProfile = UserDataDao.friendsList[p1]

        // pre-check
        p0.showFriendOnMapCheckBox?.isChecked =
            UserDataDao.trackingFriendsMap.keys.contains(currentFriendPublicProfile.userId)

        // show on map check box
        p0.showFriendOnMapCheckBox?.setOnCheckedChangeListener { compoundButton, b ->

            if (b) {
                // friend's location is being tracked

                UserDataDao.trackingFriendsMap[currentFriendPublicProfile.userId] = currentFriendPublicProfile
                LoadFriendLocationTask().execute(currentFriendPublicProfile)

                val msg = "Friend added to map!"
                Toast.makeText(p0.itemView.context, msg, Toast.LENGTH_LONG).show()
            } else {
                // friend's location is not being tracked anymore

                if (UserDataDao.trackingFriendsMap.containsKey(currentFriendPublicProfile.userId)) {
                    UserDataDao.trackingFriendsMap.remove(currentFriendPublicProfile.userId)
                }

                if (UserDataDao.friendsLocationsMap.containsKey(currentFriendPublicProfile.userId)) {
                    UserDataDao.friendsLocationsMap.remove(currentFriendPublicProfile.userId)
                }

                val msg = "Friend removed from map!"
                Toast.makeText(p0.itemView.context, msg, Toast.LENGTH_LONG).show()
            }

        }

        // show username
        p0.usernameText?.text = currentFriendPublicProfile.username

        // open map
        p0.itemView.setOnClickListener {
            // TODO open map here
        }

        // delete friend button
        p0.deleteButton?.setOnClickListener {
            DeleteFriendTask().execute(currentFriendPublicProfile)

            val msg = "Unfriended!"
            Toast.makeText(p0.itemView.context, msg, Toast.LENGTH_SHORT).show()
        }

    }


    /**
     * Holder adapter
     */
    inner class FriendAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var showFriendOnMapCheckBox: CheckBox? = null
        var usernameText: TextView? = null
        var deleteButton: ImageButton? = null

        init {
            showFriendOnMapCheckBox = itemView.findViewById(R.id.showFriendOnMapCheckBox)
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
                UserDataDao.friendsLocationsMap[result.userId] = result
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

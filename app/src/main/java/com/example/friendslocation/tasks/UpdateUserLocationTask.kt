package com.example.friendslocation.tasks

import android.os.AsyncTask
import com.example.friendslocation.entity.Location
import com.example.friendslocation.net.RestFactory

class UpdateUserLocationTask(private val userId: String) :
    AsyncTask<Unit, Unit, Unit>() {

    override fun doInBackground(vararg params: Unit) {
        val rest = RestFactory.instance

        val location: Location = Location(userId, 0.0, 0.0)

        rest.updateUserLocation(location)
    }

}

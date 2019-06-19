package com.example.friendslocation.tasks

import android.os.AsyncTask
import android.util.Log
import com.example.friendslocation.entity.Location
import com.example.friendslocation.net.RestFactory

class UpdateUserLocationTask(private val userId: String) :
    AsyncTask<Unit, Unit, Unit>() {

    private fun grabCurrentGPSLocation(): Location {

        // TODO grab from gps

        var latitude = (10..100).random().toDouble()
        var longitude = (10..100).random().toDouble()

        Log.d("MY_LOCATION", "lat: $latitude, lon: $longitude")

        return Location(userId, latitude, longitude)
    }

    override fun doInBackground(vararg params: Unit) {
        val rest = RestFactory.instance

        while (true) {

            // craft location
            val location: Location = grabCurrentGPSLocation()

            // send to server
            rest.updateUserLocation(location)

            // sleep some time
            Thread.sleep(5000)
        }

    }

}

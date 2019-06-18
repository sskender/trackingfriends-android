package com.example.friendslocation.tasks

import android.os.AsyncTask
import android.util.Log
import com.example.friendslocation.entity.Location
import com.example.friendslocation.net.RestFactory

class UpdateUserLocationTask : AsyncTask<String, Unit, Unit>() {

    private fun grabCurrentGPSLocation(): Location {

        // TODO grab from gps

        var latitude = (10..100).random().toDouble()
        var longitude = (10..100).random().toDouble()

        Log.d("MY_LOCATION", "lat: $latitude, lon: $longitude")

        return Location("", latitude, longitude)
    }

    override fun doInBackground(vararg params: String) {
        val rest = RestFactory.instance

        while (true) {

            // craft location
            val location: Location = grabCurrentGPSLocation()
            location.userId = params[0]

            // send to server
            rest.updateUserLocation(location)

            // sleep some time
            Thread.sleep(2000)
        }

    }

}

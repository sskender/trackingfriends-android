package com.example.friendslocation.tasks

import android.os.AsyncTask
import com.example.friendslocation.entity.Location
import com.example.friendslocation.net.RestFactory

class UpdateUserLocationTask : AsyncTask<Location, Unit, Unit>() {

    override fun doInBackground(vararg params: Location) {
        val rest = RestFactory.instance
        rest.updateUserLocation(params[0])
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        // TODO return what?
    }

}

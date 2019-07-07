package com.example.friendslocation

import android.Manifest
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import com.example.friendslocation.config.AppConstants
import com.example.friendslocation.dao.UserDataDao
import com.example.friendslocation.entity.Location
import com.example.friendslocation.entity.UserPublicProfile
import com.example.friendslocation.net.RestFactory
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapActivity : FragmentActivity(), OnMapReadyCallback {

    // here is stored profile of a currently logged in user
    // use it as reference for each api call
    private lateinit var loggedInUser: UserPublicProfile

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lastKnownLocation: LatLng? = null
    private lateinit var locationCallback: LocationCallback

    lateinit var map: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mapa)

        // logged in user profile
        loggedInUser = intent.extras?.get(AppConstants.USER_PROFILE_INTENT_EXTRA) as UserPublicProfile

        var mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {

        map = googleMap
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        // request permission for location access

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            AppConstants.MY_PERMISSIONS_REQUEST_FINE_LOCATION
        )


        // last known location

        locationCallback = object : LocationCallback() {

            override fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult == null) {
                    return
                }
                if (lastKnownLocation != null) {
                    return
                }
                // get last known location
                for (location in locationResult.locations) {

                    lastKnownLocation = LatLng(location.latitude, location.longitude)

                    // upload location to server
                    UploadMyLocationTask().execute(lastKnownLocation)

                    // position camera on last known location
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(lastKnownLocation, AppConstants.MAP_ZOOM_RATE))

                    //drawRoute()
                }
            }

        }


        // add markers for each friend

        if (UserDataDao.friendsLocationsMap.isNotEmpty()) {
            for (friendLocation: Location? in UserDataDao.friendsLocationsMap.values) {

                if (friendLocation != null) {
                    var friendMarker = LatLng(friendLocation.latitude, friendLocation.longitude)
                    var friendUsername = UserDataDao.trackingFriendsMap[friendLocation.userId]?.username

                    map.addMarker(
                        MarkerOptions()
                            .position(friendMarker)
                            .title(friendUsername)
                            .icon(
                                BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_MAGENTA
                                )
                            )
                    )
                }
            }
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            AppConstants.MY_PERMISSIONS_REQUEST_FINE_LOCATION -> {

                // if request is cancelled, the result arrays are empty
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED
                    ) {
                        // permission is granted
                        map.isMyLocationEnabled = true
                        map.uiSettings.isMyLocationButtonEnabled = true

                        // setup location change listening
                        val locationRequest = LocationRequest()

                        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                        locationRequest.interval = AppConstants.LOCATION_REQUEST_INTERVAL.toLong()

                        fusedLocationClient.requestLocationUpdates(
                            locationRequest, locationCallback, null
                        )
                    }

                }
                // permission is not granted
                else {
                    finish()
                }

                return
            }

        }
    }


    /**
     * Upload location task
     */
    inner class UploadMyLocationTask : AsyncTask<LatLng, Unit, Location?>() {

        override fun doInBackground(vararg p0: LatLng): Location? {
            val rest = RestFactory.instance
            val location = Location(loggedInUser.userId, p0[0].latitude, p0[0].longitude)

            return rest.updateUserLocation(location)
        }

    }


}

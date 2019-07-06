package com.example.friendslocation

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.example.friendslocation.dao.UserDataDao
import com.example.friendslocation.entity.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapActivity : FragmentActivity(), OnMapReadyCallback {


    // TODO update user's current location loop

    // TODO download friend's locations loop


    lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mapa)

        //ova je linija u kurcu
        var mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment


        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // add markers to map for all locations saved in dao

        for (friendLocation: Location in UserDataDao.locationsList) {

            var friendMarker: LatLng = LatLng(friendLocation.latitude, friendLocation.longitude)

            map.addMarker(
                MarkerOptions()
                    .position(friendMarker)
                    .title("Friend " + friendLocation.userId)
                    .icon(
                        BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_MAGENTA
                        )
                    )
            )

            map.moveCamera(CameraUpdateFactory.newLatLng(friendMarker))

        }

    }

}

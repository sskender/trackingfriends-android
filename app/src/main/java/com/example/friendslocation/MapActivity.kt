package com.example.friendslocation

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapActivity : FragmentActivity(), OnMapReadyCallback {

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

        var maracana: LatLng = LatLng(45.799338, 15.981387)

        map.addMarker(
            MarkerOptions().position(maracana).title("MACARANAA").icon(
                BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_RED
                )
            )
        )
        map.moveCamera(CameraUpdateFactory.newLatLng(maracana))
        Log.d("sss", "ddd")


        maracana = LatLng(40.799338, 15.981387)

        map.addMarker(
            MarkerOptions().position(maracana).title("MACARANAA").icon(
                BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_AZURE
                )
            )
        )
        map.moveCamera(CameraUpdateFactory.newLatLng(maracana))
        Log.d("sss", "ddd")

        /*
        googleMap.setOnMapLoadedCallback {
            var maracana: LatLng=LatLng(45.799338, 15.981387)
            map.addMarker(MarkerOptions().position(maracana).title("MACARANAA").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)))
            map.moveCamera(CameraUpdateFactory.newLatLng(maracana))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(maracana as LatLngBounds, 15))
        }*/
    }
}
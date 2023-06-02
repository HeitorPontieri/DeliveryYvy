package br.senai.sp.jandira.deliveryyvy.models

import com.google.android.gms.maps.model.LatLng

data class Route(
    val startLocation: LatLng,
    val endLocation: LatLng,
    val duration: String,
    val distance: String
)


package br.senai.sp.jandira.deliveryyvy.api.commons

import com.google.gson.annotations.SerializedName

data class DirectionsResponse(
    val routes: List<Route>
)

data class Route(
    val overviewPolyline: Polyline
)

data class Polyline(
    val points: String
)

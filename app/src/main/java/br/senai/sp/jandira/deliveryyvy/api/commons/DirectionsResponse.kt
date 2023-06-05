package br.senai.sp.jandira.deliveryyvy.api.commons

data class DirectionsResponse(
    val routes: List<Route>
)

data class Route(
    val overviewPolyline: Polyline
)

data class Polyline(
    val points: String
)

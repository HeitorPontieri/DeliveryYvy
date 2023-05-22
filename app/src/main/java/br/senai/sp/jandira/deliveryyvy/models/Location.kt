package br.senai.sp.jandira.deliveryyvy.models

data class Location(
    var id: Int? = null,
    var longitude: Double?,
    var latitude: Double?,
    var created_at: String? = null,
    var updated_at: String? = null
)
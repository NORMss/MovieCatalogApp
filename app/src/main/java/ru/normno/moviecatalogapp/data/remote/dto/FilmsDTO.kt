package ru.normno.moviecatalogapp.data.remote.dto

import com.squareup.moshi.Json

data class FilmsDTO(
    @Json(name = "films") val films: List<FilmDTO>
)
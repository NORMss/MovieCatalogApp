package ru.normno.moviecatalogapp.data.remote.dto

import com.squareup.moshi.Json

data class FilmDTO(
    @Json(name = "description") val description: String? = null,
    @Json(name = "genres") val genres: List<String> = emptyList(),
    @Json(name = "id") val id: Int,
    @Json(name = "image_url") val imageUrl: String? = null,
    @Json(name = "localized_name") val localizedName: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "rating") val rating: Double? = null,
    @Json(name = "year") val year: Int? = null,
)
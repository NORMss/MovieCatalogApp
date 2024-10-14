package ru.normno.moviecatalogapp.domain.model

data class Film(
    val description: String? = null,
    val genres: List<String> = emptyList(),
    val id: Long,
    val imageUrl: String? = null,
    val localizedName: String? = null,
    val name: String? = null,
    val rating: Double? = null,
    val year: Int? = null,
)
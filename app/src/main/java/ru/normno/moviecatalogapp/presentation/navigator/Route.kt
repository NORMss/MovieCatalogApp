package ru.normno.moviecatalogapp.presentation.navigator

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Catalog : Route()

    @Serializable
    data class Details(val id: Int) : Route()
}
package ru.normno.moviecatalogapp.presentation.navigator

import kotlinx.serialization.Serializable
import ru.normno.moviecatalogapp.domain.model.Film

@Serializable
sealed class Route {
    @Serializable
    data object Catalog : Route()

    @Serializable
    data class Details(val film: Film) : Route()
}
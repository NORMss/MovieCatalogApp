package ru.normno.moviecatalogapp.presentation.catalog

import ru.normno.moviecatalogapp.domain.model.Film

data class CatalogState(
    val isLoading: Boolean = false,
    val films: List<Film> = emptyList(),
    val filteredFilms: List<Film> = emptyList(),
    val genres: List<String> = emptyList(),
    val selectGenre: String? = null,
    val errorMessage: String? = null,
)
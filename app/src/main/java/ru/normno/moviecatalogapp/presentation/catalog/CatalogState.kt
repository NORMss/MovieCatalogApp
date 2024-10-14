package ru.normno.moviecatalogapp.presentation.catalog

import ru.normno.moviecatalogapp.domain.model.Film

data class CatalogState(
    val isLoading: Boolean = false,
    val films: List<Film> = emptyList(),
    val errorMessage: String? = null,
)
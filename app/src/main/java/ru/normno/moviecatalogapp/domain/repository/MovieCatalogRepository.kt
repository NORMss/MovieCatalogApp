package ru.normno.moviecatalogapp.domain.repository

import ru.normno.moviecatalogapp.domain.model.Film
import ru.normno.moviecatalogapp.util.network.NetworkError
import ru.normno.moviecatalogapp.util.network.Result

interface MovieCatalogRepository {
    suspend fun getMovieCatalog(): Result<List<Film>, NetworkError>
}
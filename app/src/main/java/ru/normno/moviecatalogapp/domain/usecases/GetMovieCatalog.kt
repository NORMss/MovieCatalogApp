package ru.normno.moviecatalogapp.domain.usecases

import ru.normno.moviecatalogapp.data.remote.MovieCatalogApi
import ru.normno.moviecatalogapp.domain.model.Film
import ru.normno.moviecatalogapp.domain.repository.MovieCatalogRepository
import ru.normno.moviecatalogapp.util.network.NetworkError
import ru.normno.moviecatalogapp.util.network.Result

class GetMovieCatalog(
    private val movieCatalogRepository: MovieCatalogRepository,
) {
    suspend operator fun invoke(): Result<List<Film>, NetworkError> {
        return movieCatalogRepository.getMovieCatalog()
    }
}
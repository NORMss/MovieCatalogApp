package ru.normno.moviecatalogapp.data.repository

import com.squareup.moshi.JsonDataException
import okio.IOException
import retrofit2.HttpException
import ru.normno.moviecatalogapp.data.remote.MovieCatalogApi
import ru.normno.moviecatalogapp.data.remote.dto.FilmDTO
import ru.normno.moviecatalogapp.domain.model.Film
import ru.normno.moviecatalogapp.domain.repository.MovieCatalogRepository
import ru.normno.moviecatalogapp.util.network.NetworkError
import ru.normno.moviecatalogapp.util.network.Result
import java.lang.reflect.InvocationTargetException

class MovieCatalogRepositoryImpl(
    private val movieCatalogApi: MovieCatalogApi,
) : MovieCatalogRepository {
    override suspend fun getMovieCatalog(): Result<List<Film>, NetworkError> {
        return try {
            return Result.Success(movieCatalogApi.getFilms().films.map { it.toDomain() })
        } catch (e: IOException) {
            Result.Error(NetworkError.NO_INTERNET)
        } catch (e: HttpException) {
            val networkError = when (e.code()) {
                401 -> NetworkError.UNAUTHORIZED
                403 -> NetworkError.TOO_MANY_REQUESTS
                404 -> NetworkError.API_ERROR
                408 -> NetworkError.REQUEST_TIMEOUT
                409 -> NetworkError.CONFLICT
                413 -> NetworkError.PAYLOAD_TOO_LARGE
                in 500..599 -> NetworkError.SERVER_ERROR
                else -> NetworkError.UNKNOWN
            }
            Result.Error(networkError)
        } catch (e: JsonDataException) {
            Result.Error(NetworkError.SERIALIZATION)
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
            throw e
        } catch (e: Exception) {
            Result.Error(NetworkError.UNKNOWN)
        }
    }

    private fun FilmDTO.toDomain(): Film {
        return Film(
            id = this.id,
            name = this.name,
            localizedName = this.localizedName,
            description = this.description,
            genres = this.genres,
            imageUrl = this.imageUrl,
            rating = this.rating,
            year = this.year
        )
    }
}
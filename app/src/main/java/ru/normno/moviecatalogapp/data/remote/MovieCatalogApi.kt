package ru.normno.moviecatalogapp.data.remote

import retrofit2.http.GET
import ru.normno.moviecatalogapp.data.remote.dto.FilmsDTO

interface MovieCatalogApi {
    @GET("sequeniatesttask/films.json")
    suspend fun getFilms(): FilmsDTO

    companion object {
        const val BASE_URL = "https://s3-eu-west-1.amazonaws.com/"
    }
}
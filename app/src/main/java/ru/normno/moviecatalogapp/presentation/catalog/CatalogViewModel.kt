package ru.normno.moviecatalogapp.presentation.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.normno.moviecatalogapp.domain.model.Film
import ru.normno.moviecatalogapp.domain.usecases.MovieCatalogUseCases
import ru.normno.moviecatalogapp.util.network.onError
import ru.normno.moviecatalogapp.util.network.onSuccess

class CatalogViewModel(
    private val useCases: MovieCatalogUseCases,
) : ViewModel() {
    private val _state = MutableStateFlow(CatalogState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    init {
        viewModelScope.launch {
            getMovieCatalog()
        }
    }

    private fun setFilms(films: List<Film>) {
        _state.update {
            it.copy(
                films = films
            )
        }
    }

    private suspend fun getMovieCatalog() {
        setIsLoading(true)
        useCases.getMovieCatalog()
            .onSuccess { films ->
                setFilms(films)
                crateGenreList()
            }
            .onError { errorMessage ->
                setErrorMessage(errorMessage = errorMessage.name)
            }
        setIsLoading(false)
    }

    private fun setErrorMessage(errorMessage: String) {
        _state.update {
            it.copy(
                errorMessage = errorMessage,
            )
        }
    }

    private fun setIsLoading(isLoading: Boolean) {
        _state.update {
            it.copy(
                isLoading = isLoading,
            )
        }
    }

    private fun crateGenreList() {
        _state.update {
            it.copy(
                genres = _state.value.films.flatMap { it.genres }
                    .map { it.replaceFirstChar { char -> char.uppercaseChar() } }
                    .distinct()
                    .sorted(),
            )
        }
    }

    fun setSelectGenre(genre: String) {
        _state.update {
            it.copy(
                selectGenre = if (_state.value.selectGenre == genre) null else genre
            )
        }
    }
}
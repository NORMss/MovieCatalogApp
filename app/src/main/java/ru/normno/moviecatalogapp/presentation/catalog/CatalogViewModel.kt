package ru.normno.moviecatalogapp.presentation.catalog

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.normno.moviecatalogapp.R
import ru.normno.moviecatalogapp.domain.model.Film
import ru.normno.moviecatalogapp.domain.usecases.MovieCatalogUseCases
import ru.normno.moviecatalogapp.util.network.onError
import ru.normno.moviecatalogapp.util.network.onSuccess
import ru.normno.moviecatalogapp.util.snackbar.SnackbarAction
import ru.normno.moviecatalogapp.util.snackbar.SnackbarController
import ru.normno.moviecatalogapp.util.snackbar.SnackbarEvent

class CatalogViewModel(
    private val useCases: MovieCatalogUseCases,
    private val context: Context,
) : ViewModel() {
    private val _state = MutableStateFlow(CatalogState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    init {
        viewModelScope.launch {
            getMovieCatalog()
        }
    }

    fun setSelectGenre(genre: String) {
        _state.update {
            it.copy(
                selectGenre = if (_state.value.selectGenre == genre) {
                    null
                } else {
                    genre
                }.also { filterFilmsByGenre(it) }
            )
        }
    }

    private fun showErrorSnakbar(errorMessage: String, actionText: String) {
        viewModelScope.launch {
            SnackbarController.sendEvent(
                event = SnackbarEvent(
                    message = errorMessage,
                    action = SnackbarAction(
                        name = actionText,
                        action = {
                            getMovieCatalog()
                        }
                    )
                )
            )
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
                clearErrorMessage()
                setFilms(films)
                crateGenreList()
                filterFilmsByGenre(null)
            }
            .onError { errorMessage ->
                setErrorMessage(errorMessage = errorMessage.name)
                showErrorSnakbar(
                    errorMessage = context.getString(R.string.network_error),
                    actionText = context.getString(R.string.repeat),
                )
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

    private fun clearErrorMessage() {
        _state.update {
            it.copy(
                errorMessage = null,
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

    private fun setFilmsByGenre(films: List<Film>) {
        _state.update {
            it.copy(
                filteredFilms = films,
            )
        }
    }

    private fun filterFilmsByGenre(genre: String?) {
        if (genre?.isBlank() != false) {
            _state.value.films.sortedBy { it.localizedName }
        } else {
            _state.value.films.filter { it.genres.any { it.equals(genre, ignoreCase = true) } }
                .sortedBy { it.localizedName }
        }.let {
            setFilmsByGenre(it)
        }
    }
}
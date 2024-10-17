package ru.normno.moviecatalogapp.presentation.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.launch
import ru.normno.moviecatalogapp.R
import ru.normno.moviecatalogapp.domain.model.Film
import ru.normno.moviecatalogapp.presentation.catalog.component.CatalogTopAppBar
import ru.normno.moviecatalogapp.presentation.catalog.component.FilmsList
import ru.normno.moviecatalogapp.presentation.catalog.component.GenresList
import ru.normno.moviecatalogapp.presentation.catalog.component.ListTitle
import ru.normno.moviecatalogapp.presentation.catalog.component.LoadingBox
import ru.normno.moviecatalogapp.presentation.common.AppSnackbar
import ru.normno.moviecatalogapp.presentation.common.ObserveAsEvents
import ru.normno.moviecatalogapp.ui.theme.AppTheme
import ru.normno.moviecatalogapp.util.snackbar.SnackbarController


@Composable
fun CatalogScreen(
    genres: List<String>,
    selectGenre: String?,
    films: List<Film>,
    countColumn: Int = 2,
    paddingHorizontal: Dp,
    isLoading: Boolean,
    onSelectGenre: (String) -> Unit,
    onClickFilm: (Film) -> Unit,
) {
    val localContext = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    ObserveAsEvents(
        flow = SnackbarController.events,
        key1 = snackbarHostState,
    ) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()

            val result = snackbarHostState.showSnackbar(
                message = event.message,
                actionLabel = event.action?.name,
                duration = SnackbarDuration.Indefinite,
            )
            if (result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }

    Scaffold(
        topBar = {
            CatalogTopAppBar(
                title = stringResource(R.string.films),
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                AppSnackbar(data)
            }
        },
        containerColor = AppTheme.colorScheme.background,
    ) { padding ->
        if (isLoading) {
            LoadingBox(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        } else if (films.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(countColumn),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = paddingHorizontal),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.size.normal),
            ) {
                item(
                    span = { GridItemSpan(this.maxLineSpan) }
                ) { Spacer(modifier = Modifier.height(AppTheme.size.normal)) }
                ListTitle(
                    title = localContext.getString(R.string.genres),
                    scope = this,
                )
                GenresList(
                    genres = genres,
                    selectGenre = selectGenre,
                    onClick = { selectGenre ->
                        onSelectGenre(selectGenre)
                    },
                    parentHorizontalPadding = paddingHorizontal,
                    scope = this,
                )
                item(
                    span = { GridItemSpan(this.maxLineSpan) }
                ) { Spacer(modifier = Modifier.height(AppTheme.size.medium)) }
                ListTitle(
                    title = localContext.getString(R.string.films),
                    scope = this,
                )
                item(
                    span = { GridItemSpan(this.maxLineSpan) }
                ) { Spacer(modifier = Modifier.height(AppTheme.size.normal)) }
                FilmsList(
                    films = films,
                    onClickFilm = { film ->
                        onClickFilm(film)
                    },
                    scope = this,
                )
            }
        }
    }
}

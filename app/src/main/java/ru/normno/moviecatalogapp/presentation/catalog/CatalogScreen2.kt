@file:OptIn(ExperimentalMaterial3Api::class)

package ru.normno.moviecatalogapp.presentation.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.normno.moviecatalogapp.R
import ru.normno.moviecatalogapp.domain.model.Film
import ru.normno.moviecatalogapp.presentation.Dimens.heightSnackbar
import ru.normno.moviecatalogapp.presentation.catalog.component.FilmsList2
import ru.normno.moviecatalogapp.presentation.catalog.component.GenresList2
import ru.normno.moviecatalogapp.presentation.catalog.component.LoadingBox
import ru.normno.moviecatalogapp.presentation.common.ObserveAsEvents
import ru.normno.moviecatalogapp.ui.theme.AppTheme
import ru.normno.moviecatalogapp.ui.theme.Roboto
import ru.normno.moviecatalogapp.util.snackbar.SnackbarController


@Composable
fun CatalogScreen2(
    genres: List<String>,
    selectGenre: String?,
    films: List<Film>,
    countColumn: Int = 2,
    isLoading: Boolean,
    onSelectGenre: (String) -> Unit,
    onClickFilm: (Film) -> Unit,
) {
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
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.films),
                        style = AppTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                    )
                },
                colors = TopAppBarColors(
                    containerColor = AppTheme.colorScheme.primary,
                    scrolledContainerColor = AppTheme.colorScheme.primary,
                    navigationIconContentColor = AppTheme.colorScheme.onPrimary,
                    titleContentColor = AppTheme.colorScheme.onPrimary,
                    actionIconContentColor = AppTheme.colorScheme.onPrimary,
                )
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    modifier = Modifier
                        .padding(horizontal = AppTheme.size.normal)
                        .padding(bottom = AppTheme.size.normal)
                        .heightIn(min = heightSnackbar),
                    contentColor = AppTheme.colorScheme.onPrimary,
                    containerColor = AppTheme.colorScheme.tertiary,
                    shape = AppTheme.shape.tertiaryContainer,
                    action = {
                        data.visuals.actionLabel?.let { actionLabel ->
                            TextButton(
                                onClick = {
                                    data.performAction()
                                },
                            ) {
                                Text(
                                    text = actionLabel,
                                    fontSize = AppTheme.typography.body.fontSize,
                                    fontFamily = Roboto,
                                    fontWeight = FontWeight.Medium,
                                    color = AppTheme.colorScheme.onTertiary
                                )
                            }
                        }
                    }
                ) {
                    Text(
                        text = data.visuals.message,
                        fontSize = 15.sp,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Normal,
                    )
                }
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
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(countColumn),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = AppTheme.size.medium),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.size.normal),
                verticalArrangement = Arrangement.spacedBy(AppTheme.size.medium),
            ) {
                GenresList2(
                    genres = genres,
                    selectGenre = selectGenre,
                    onClick = { selectGenre ->
                        onSelectGenre(selectGenre)
                    },
                    scope = this,
                )
                FilmsList2(
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

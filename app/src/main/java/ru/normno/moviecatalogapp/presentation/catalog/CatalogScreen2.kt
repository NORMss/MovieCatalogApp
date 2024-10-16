@file:OptIn(ExperimentalMaterial3Api::class)

package ru.normno.moviecatalogapp.presentation.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ru.normno.moviecatalogapp.domain.model.Film
import ru.normno.moviecatalogapp.presentation.catalog.component.FilmsList
import ru.normno.moviecatalogapp.presentation.catalog.component.FilmsList2
import ru.normno.moviecatalogapp.presentation.catalog.component.GenresList2
import ru.normno.moviecatalogapp.presentation.catalog.component.LoadingBox
import ru.normno.moviecatalogapp.ui.theme.AppTheme


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
    if (isLoading) {
        LoadingBox(
            modifier = Modifier
                .fillMaxSize()
        )
    } else {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Фильмы",
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
            containerColor = AppTheme.colorScheme.background,
        ) { padding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(countColumn),
                modifier = Modifier
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

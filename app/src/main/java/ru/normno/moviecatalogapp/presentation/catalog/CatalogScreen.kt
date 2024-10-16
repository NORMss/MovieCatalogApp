@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package ru.normno.moviecatalogapp.presentation.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ru.normno.moviecatalogapp.domain.model.Film
import ru.normno.moviecatalogapp.presentation.catalog.component.FilmsList
import ru.normno.moviecatalogapp.presentation.catalog.component.GenresList
import ru.normno.moviecatalogapp.ui.theme.AppTheme

@Composable
fun CatalogScreen(
    genres: List<String>,
    selectGenre: String?,
    films: List<Film>,
    onSelectGenre: (String) -> Unit,
    onClickFilm: (Film) -> Unit,
) {
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
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.size.medium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Text(
                        text = "Жанры",
                        style = AppTheme.typography.titleNormal,
                        color = AppTheme.colorScheme.onBackground,
                    )
                }
            }
            GenresList(
                genres = genres,
                selectGenre = selectGenre,
                onClick = { selectGenre ->
                    onSelectGenre(selectGenre)
                },
                scope = this,
            )
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.size.medium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Text(
                        text = "Фильмы",
                        style = AppTheme.typography.titleNormal,
                        color = AppTheme.colorScheme.onBackground,
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(AppTheme.size.normal),
                )
            }
            FilmsList(
                films = films,
                onClickFilm = { film ->
                    onClickFilm(film)
                },
                columnsCount = 2,
                scope = this,
            )
        }
    }
}

//@Preview(
//    showSystemUi = true,
//    device = Devices.PIXEL_7,
//)
//@Composable
//private fun PreviewCatalogScreen() {
//    CatalogScreen(
//    )
//}
//
//private val filmsPreview = listOf(
//    Film(
//        description = "TestDescription",
//        genres = listOf("Test1", "Test2", "Test3"),
//        id = 123456,
//        imageUrl = "",
//        localizedName = "TestLocal",
//        name = "TestName",
//        rating = 9.9,
//        year = 2023,
//    )
//)
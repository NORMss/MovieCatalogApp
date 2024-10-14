@file:OptIn(ExperimentalMaterial3Api::class)

package ru.normno.moviecatalogapp.presentation.catalog

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.normno.moviecatalogapp.domain.model.Film
import ru.normno.moviecatalogapp.presentation.catalog.component.GenresList
import ru.normno.moviecatalogapp.ui.theme.AppTheme

@Composable
fun CatalogScreen(
    genres: List<String>,
    selectGenre: String?,
    onClick: (String) -> Unit,
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
        Spacer(
            modifier = Modifier
                .height(AppTheme.size.normal)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(AppTheme.size.medium)
        ) {
            GenresList(
                modifier = Modifier
                    .fillMaxWidth(),
                genres = genres,
                selectGenre = selectGenre,
                onClick = { selectGenre ->
                    onClick(selectGenre)
                }
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
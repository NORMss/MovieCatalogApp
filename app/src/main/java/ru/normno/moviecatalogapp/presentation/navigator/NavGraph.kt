package ru.normno.moviecatalogapp.presentation.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.compose.viewmodel.koinViewModel
import ru.normno.moviecatalogapp.domain.model.Film
import ru.normno.moviecatalogapp.presentation.catalog.CatalogScreen
import ru.normno.moviecatalogapp.presentation.catalog.CatalogViewModel
import ru.normno.moviecatalogapp.presentation.detail.DetailScreen
import ru.normno.moviecatalogapp.util.navtype.FilmNavType
import kotlin.reflect.typeOf

@Composable
fun NavGraph(
    startDestination: Route,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<Route.Catalog> {
            val viewModel = koinViewModel<CatalogViewModel>()
            val state = viewModel.state.collectAsState().value
            CatalogScreen(
                genres = state.genres,
                selectGenre = state.selectGenre,
                films = state.filteredFilms,
                onSelectGenre = { selectGenre ->
                    viewModel.setSelectGenre(
                        genre = selectGenre,
                    )
                },
                onClickFilm = { film ->
                    navController.navigate(
                        Route.Details(film)
                    )
                }
            )
        }
        composable<Route.Details>(
            typeMap = mapOf(
                typeOf<Film>() to FilmNavType.FilmType,
            )
        ) { backStackEntry ->
            val film: Film
            backStackEntry.toRoute<Route.Details>().let { catalog ->
                film = catalog.film
            }
            DetailScreen(
                name = film.name,
                imageUrl = film.imageUrl,
                localName = film.localizedName,
                genres = film.genres,
                year = film.year,
                rating = film.rating,
                description = film.description,
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}
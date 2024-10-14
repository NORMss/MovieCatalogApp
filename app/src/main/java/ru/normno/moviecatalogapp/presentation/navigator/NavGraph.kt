package ru.normno.moviecatalogapp.presentation.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.compose.viewmodel.koinViewModel
import ru.normno.moviecatalogapp.presentation.catalog.CatalogScreen
import ru.normno.moviecatalogapp.presentation.catalog.CatalogViewModel

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
                onClick = { selectGenre ->
                    viewModel.setSelectGenre(
                        genre = selectGenre,
                    )
                }
            )
        }
        composable<Route.Details> { backStackEntry ->
            backStackEntry.toRoute<Route.Details>().let { catalog ->

            }
        }
    }
}
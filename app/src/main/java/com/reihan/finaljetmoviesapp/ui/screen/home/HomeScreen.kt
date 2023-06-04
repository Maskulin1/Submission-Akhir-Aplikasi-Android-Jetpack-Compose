package com.reihan.finaljetmoviesapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.reihan.finaljetmoviesapp.di.Injection
import com.reihan.finaljetmoviesapp.model.OrderMovie
import com.reihan.finaljetmoviesapp.ui.ViewModelFactory
import com.reihan.finaljetmoviesapp.ui.common.UiState
import com.reihan.finaljetmoviesapp.ui.components.MovieItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllMovies()
            }
            is UiState.Success -> {
                HomeContent(
                    orderMovie = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    orderMovie: List<OrderMovie>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(110.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(orderMovie) { data ->
            MovieItem(
                photo = data.movie.photo,
                title = data.movie.title,
                requiredMoney = data.movie.requiredMoney,
                modifier = Modifier.clickable {
                    navigateToDetail(data.movie.id)
                }
            )
        }
    }
}
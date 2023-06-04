package com.reihan.finaljetmoviesapp.ui.screen.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.reihan.finaljetmoviesapp.R
import com.reihan.finaljetmoviesapp.di.Injection
import com.reihan.finaljetmoviesapp.ui.ViewModelFactory
import com.reihan.finaljetmoviesapp.ui.common.UiState
import com.reihan.finaljetmoviesapp.ui.components.OrderButton
import com.reihan.finaljetmoviesapp.ui.components.ProductCounter
import com.reihan.finaljetmoviesapp.ui.theme.JetMovieTheme

@Composable
fun DetailScreen(
    movieId: Long,
    viewModel: DetailMovieViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMovieById(movieId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.movie.photo,
                    data.movie.title,
                    data.movie.genre,
                    data.movie.rating,
                    data.movie.duration,
                    data.movie.director,
                    data.movie.platform,
                    data.movie.cast,
                    data.movie.description,
                    data.movie.requiredMoney,
                    data.count,
                    onBackClick = navigateBack,
                    onAddToCart = { count ->
                        viewModel.addToCart(data.movie, count)
                        navigateToCart()
                    }
                )
            }

            is UiState.Error -> {
            }
        }
    }
}

@Composable
fun DetailContent(
    photo: String,
    title: String,
    genre: String,
    rating: String,
    duration: String,
    director: String,
    platform: String,
    cast: String,
    description: String,
    basePoint: Int,
    count: Int,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    var totalPoint by rememberSaveable { mutableStateOf(0) }
    var orderCount by rememberSaveable { mutableStateOf(count) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painter = rememberAsyncImagePainter(photo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(600.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            )
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onBackClick() }
            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Genre: $genre",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Rating: $rating",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Duration: $duration",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Director: $director",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Platform: $platform",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Cast: $cast",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Description:",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(4.dp).background(LightGray))
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ProductCounter(
                1,
                orderCount,
                onProductIncreased = { orderCount++ },
                onProductDecreased = { if (orderCount > 0) orderCount-- },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 16.dp)
            )
            totalPoint = basePoint * orderCount
            OrderButton(
                text = stringResource(R.string.add_to_cart, totalPoint),
                enabled = orderCount > 0,
                onClick = {
                    onAddToCart(orderCount)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    JetMovieTheme {
        DetailContent(
            photo = "https://upload.wikimedia.org/wikipedia/en/9/9b/Blade_Runner_2049_poster.png",
            title = "Blade Runner 2049",
            genre = "Sci-Fi, Thriller",
            rating = "8,2",
            duration = "1h 57m",
            director = "Ridley Scott",
            platform = "Netflix",
            cast = "Ryan Gosling, Harrison Ford, Ana de Armas",
            description = "Set in a dystopian Los Angeles in 2019, blade runner Rick Deckard is called out of retirement to terminate a quartet of replicants who have escaped to Earth seeking their creator for a way to extend their short life spans. A young blade runner's discovery of a long-buried secret leads him to track down former blade runner Rick Deckard, who's been missing for thirty years.",
            7500,
            1,
            onBackClick = {},
            onAddToCart = {}
        )
    }
}

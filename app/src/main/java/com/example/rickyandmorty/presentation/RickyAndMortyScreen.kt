package com.example.rickyandmorty.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.rickyandmorty.ExpandableColumn
import com.example.rickyandmorty.LoadingContent
import com.example.rickyandmorty.SimpleErrorContent

@Composable
fun RickyAndMortyScreen(
    viewModel: RickyAndMortyViewModel = viewModel(factory = RickyAndMortyViewModel.FACTORY)
) {
    val screenState by viewModel.state.collectAsState()
    when (val result = screenState) {
        RickyAndMortyState.Loading -> LoadingContent()
        is RickyAndMortyState.Success -> RickyAndMortyContent(
            result.results,
            modifier = Modifier.padding(12.dp)
        )

        is RickyAndMortyState.Error -> SimpleErrorContent(msgError = result.errorMsg) {
            viewModel.getItems()
        }
    }
    LaunchedEffect(false) {
        viewModel.getItems()
    }
}

@Composable
fun RickyAndMortyContent(
    result: List<RickyAndMortyUiModel>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(result, key = { it.name }) {
            RickyAndMortyItemContent(
                rickyAndMortyUiModel = it,
                modifier = Modifier
                    .width(100.dp)
                    .padding(12.dp)
            )
        }
    }
}

@Composable
fun RickyAndMortyItemContent(
    rickyAndMortyUiModel: RickyAndMortyUiModel,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            RickyAndMortyHeader(
                name = rickyAndMortyUiModel.name,
                image = rickyAndMortyUiModel.image,
                modifier = Modifier.padding(top = 12.dp)
            )
            Spacer(
                modifier = Modifier.padding(8.dp)
            )
            RickyAndMortyDetail(rickyAndMortyUiModel = rickyAndMortyUiModel)
        }
    }
}

@Composable
fun RickyAndMortyHeader(
    name: String,
    image: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        ) {
            Image(
                painter = rememberAsyncImagePainter(image),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text = name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}

@Composable
fun RickyAndMortyDetail(
    rickyAndMortyUiModel: RickyAndMortyUiModel,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    ExpandableColumn(
        onExpandAction = { expanded = !expanded },
        expanded = expanded,
        title = "Ver detalle",
        modifier = modifier.padding(bottom = 8.dp, start = 8.dp)
    ) {
        Column {
            Text(
                text = "Status: ${rickyAndMortyUiModel.status}",
                maxLines = 1
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Species: ${rickyAndMortyUiModel.species}",
                maxLines = 1
            )
            rickyAndMortyUiModel.type.takeIf { it.isNullOrEmpty().not() }?.let {
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Type: ${rickyAndMortyUiModel.type}",
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Gender: ${rickyAndMortyUiModel.gender}",
                maxLines = 1
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Origin: ${rickyAndMortyUiModel.origin}",
                maxLines = 1
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Location: ${rickyAndMortyUiModel.location}",
                maxLines = 1
            )
        }
    }
}
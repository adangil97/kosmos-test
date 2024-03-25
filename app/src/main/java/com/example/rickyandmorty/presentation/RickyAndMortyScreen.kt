package com.example.rickyandmorty.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import com.example.rickyandmorty.RickyAndMortyText
import com.example.rickyandmorty.ShimmerComponent
import com.example.rickyandmorty.SimpleErrorContent

@Composable
fun RickyAndMortyScreen(viewModel: RickyAndMortyViewModel = viewModel()) {
    val screenState by viewModel.state.collectAsState()
    when (val result = screenState) {
        RickyAndMortyState.Loading -> RickyAndMortyLoadingContent(
            modifier = Modifier.padding(12.dp)
        )

        is RickyAndMortyState.Success -> RickyAndMortyListContent(
            result.results,
            modifier = Modifier.padding(12.dp)
        )

        is RickyAndMortyState.Error -> SimpleErrorContent(msgError = result.errorMsg) {
            viewModel.getCharacterList()
        }
    }
    LaunchedEffect(false) {
        viewModel.getCharacterList()
    }
}

@Composable
fun RickyAndMortyLoadingContent(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(20) {
            RickyAndMortyItemLoading(modifier = Modifier.padding(12.dp))
        }
    }
}

@Composable
fun RickyAndMortyItemLoading(
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            ShimmerComponent(
                modifier = Modifier
                    .padding(12.dp)
                    .clip(CircleShape)
                    .size(128.dp)
            )
            Spacer(
                modifier = Modifier.padding(8.dp)
            )
            ShimmerComponent(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .height(25.dp)
            )
            Spacer(
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun RickyAndMortyListContent(
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
    val title by remember {
        derivedStateOf {
            if (expanded) {
                "Ocultar detalle"
            } else {
                "Ver detalle"
            }
        }
    }
    ExpandableColumn(
        onExpandAction = { expanded = !expanded },
        expanded = expanded,
        title = title,
        modifier = modifier.padding(bottom = 8.dp, start = 8.dp)
    ) {
        RickyAndMortyDetailContent(
            rickyAndMortyUiModel = rickyAndMortyUiModel,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
fun RickyAndMortyDetailContent(
    rickyAndMortyUiModel: RickyAndMortyUiModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        RickyAndMortyText(strongText = "Status: ", text = rickyAndMortyUiModel.status)
        Spacer(modifier = Modifier.padding(8.dp))
        RickyAndMortyText(strongText = "Species: ", text = rickyAndMortyUiModel.species)
        rickyAndMortyUiModel.type.takeIf { it.isNullOrEmpty().not() }?.let {
            Spacer(modifier = Modifier.padding(8.dp))
            RickyAndMortyText(strongText = "Type: ", text = it)
        }
        Spacer(modifier = Modifier.padding(8.dp))
        RickyAndMortyText(strongText = "Gender: ", text = rickyAndMortyUiModel.gender)
        Spacer(modifier = Modifier.padding(8.dp))
        RickyAndMortyText(strongText = "Origin: ", text = rickyAndMortyUiModel.origin)
        Spacer(modifier = Modifier.padding(8.dp))
        RickyAndMortyText(strongText = "Location: ", text = rickyAndMortyUiModel.location)
    }
}
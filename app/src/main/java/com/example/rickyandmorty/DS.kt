package com.example.rickyandmorty

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun SimpleErrorContent(
    msgError: String,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = msgError)
            Spacer(modifier = Modifier.padding(8.dp))
            Button(onClick = onRetry) {
                Text(text = "Reintentar")
            }
        }
    }
}

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = modifier
                .size(40.dp)
                .align(Alignment.Center)
        )
    }
}

private const val EXPAND_ANIMATION_TIME = 250

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableColumn(
    modifier: Modifier = Modifier,
    onExpandAction: () -> Unit,
    expanded: Boolean,
    title: String,
    content: @Composable () -> Unit
) {
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(transitionState, label = "")
    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = EXPAND_ANIMATION_TIME)
    }, label = "") {
        if (expanded) 0f else 180f
    }
    Column(
        modifier = modifier
            .clickable { onExpandAction() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ExpandableTitle(
                title = title,
                onClick = onExpandAction,
                modifier = Modifier
                    .weight(1f)
            )
            ExpandableArrow(
                degrees = arrowRotationDegree,
                onClick = onExpandAction
            )
        }
        ExpandableContent(initialVisibility = expanded, content = content)
    }
}

@Composable
fun ExpandableContent(
    initialVisibility: Boolean = false,
    content: @Composable () -> Unit
) {
    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(EXPAND_ANIMATION_TIME)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(EXPAND_ANIMATION_TIME)
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            // Expand from the top.
            shrinkTowards = Alignment.Top,
            animationSpec = tween(EXPAND_ANIMATION_TIME)
        ) + fadeOut(
            // Fade in with the initial alpha of 0.3f.
            animationSpec = tween(EXPAND_ANIMATION_TIME)
        )
    }
    AnimatedVisibility(
        visible = initialVisibility,
        enter = enterTransition,
        exit = exitTransition
    ) {
        content()
    }
}

@Composable
fun ExpandableArrow(
    degrees: Float,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowUp,
                contentDescription = "",
                modifier = Modifier.rotate(degrees),
            )
        }
    )
}

@Composable
fun ExpandableTitle(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
    }
}

private fun Modifier.shimmer(): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}

@Composable
fun ShimmerComponent(modifier: Modifier = Modifier) {
    Box(modifier.shimmer())
}
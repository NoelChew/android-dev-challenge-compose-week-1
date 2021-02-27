package com.example.androiddevchallenge.puppy

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.initialPuppies
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun PuppyList(
    puppies: List<Puppy>,
    modifier: Modifier = Modifier,
    navigateToPuppy: (Puppy) -> Unit
) {
    Box(modifier) {
        val listState = rememberLazyListState()
        val scope = rememberCoroutineScope()
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp),
            modifier = modifier
        ) {

            items(puppies) { puppy -> ClickablePuppy(puppy, navigateToPuppy) }
        }

        val showButton = listState.firstVisibleItemIndex > 0
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(onClick = {
                scope.launch {
                    listState.animateScrollToItem(0)
                }
            }, modifier = Modifier.padding(16.dp))
        }


    }

}

@Composable
fun ScrollToTopButton(onClick: () -> Unit, modifier: Modifier) {
    Button(
        onClick,
        modifier = modifier,
        shape = CircleShape

    ) {
        Text(text = "Top")
    }
}

@Composable
fun ClickablePuppy(puppy: Puppy, navigateToPuppy: (Puppy) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navigateToPuppy(puppy) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Image(
                painter = painterResource(id = puppy.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = puppy.breed)
                Text(text = puppy.name)
            }

        }

    }
}

@Preview
@Composable
fun MyText() {
    Text(text = "123")
}

@ExperimentalAnimationApi
@Preview
@Composable
fun DefaultPreview() {
    PuppyList(
        initialPuppies,
        Modifier.fillMaxHeight(),
        navigateToPuppy = {

        }
    )
}

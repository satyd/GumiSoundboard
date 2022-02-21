package com.levp.gumisoundboard.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingSource
import com.levp.gumisoundboard.*


//@Composable
//fun soundList(sounds: List<Sound>) {
//    LazyColumn(
//        contentPadding = // 1.
//        PaddingValues(horizontal = 16.dp, vertical = 8.dp)
//    )
//    {
//        items(sounds, // 2.
//            key = { sound -> sound.name } // 3.
//        ) { sound ->
//            Text( // 4.
//                sound.name,
//                Modifier // 5.
//                    .clickable( // 6.
//                        onClick = {
//                            viewModel.onItemClick(sound)
//                        },
//                        interactionSource = MutableInteractionSource(),
//                        indication = rememberRipple(bounded = true), // 7.
//                    )
//            )
//        }
//    }
//}

@Composable
fun NewScreen(sounds: ArrayList<Sound>, viewModel: MainViewModel = hiltViewModel()) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(sounds.size) { i ->
            val sound = sounds[i]
            if (i > 0) {
                Spacer(modifier = Modifier.height(4.dp))
            }
            SoundItem(sound) { viewModel.changeCurrentString(sound.path) }
            if (i < sounds.size - 1) {
                Spacer(modifier = Modifier.height(4.dp))
                Divider()
            }
        }
    }
}


@Composable
fun RoarScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Roar View",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoarScreenPreview() {
    RoarScreen()
}
package com.levp.gumisoundboard.ui

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.levp.gumisoundboard.MainScreen
import com.levp.gumisoundboard.R
import com.levp.gumisoundboard.Sound

@Composable
fun SoundItem(soundData: Sound, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(
                bottom = 5.dp, top = 5.dp,
                start = 5.dp, end = 5.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(15.dp),
        elevation = 7.dp
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.surface)
        ) {
            Surface(
                modifier = Modifier.size(45.dp),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colors.surface.copy(
                    alpha = 0.2f
                )
            ) {
                val image = 
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.play_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .height(40.dp)
                        .clip(shape = RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Inside
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = soundData.name,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 22.sp),
                    color = Color.Black
                )
                
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    SoundItem(Sound("Roar 1", "/res/raw/soundboard/Iconic/iconic_roar_1.mp3")) {
//        val mp:MediaPlayer = MediaPlayer.create(this@MainActivity,"/res/raw/soundboard/Iconic/iconic_roar_1.mp3")
//        mp.setDataSource("/res/raw/soundboard/Iconic/iconic_roar_1.mp3")
    }
}
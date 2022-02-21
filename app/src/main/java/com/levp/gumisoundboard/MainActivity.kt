package com.levp.gumisoundboard

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.accompanist.pager.*
import com.levp.gumisoundboard.ui.theme.colorPrimaryDark
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.lang.reflect.Field

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var jobList = ArrayList<Job>()
    private var uiScope = CoroutineScope(Dispatchers.Main)

    //val genViewModelFactory = GenerateViewModelFactory()
    lateinit var viewModel: MainViewModel

    @OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel = hiltViewModel()
            subscribeToModel()
            MainScreen()
        }
    }

    override fun onPause() {
        super.onPause()
        stopAllSounds()
    }
    
    private fun stopAllSounds(){
        jobList.clear()
    }
    
    private fun subscribeToModel() {
        // Observe product data
        viewModel.stringLiveData.observe(this) { str -> createSound(str) }
    }

    private fun createSound(path: String) {
        jobList.add(playSound(path))
    }

    private fun playSound(path: String): Job {
        return viewModel.viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val sound = MediaPlayer.create(this@MainActivity, getResId(path, R.raw::class.java))
                sound.start()
                val playDuration = sound.duration.toLong()
                delay(playDuration)
                sound.stop()
                sound.reset()

            }
        }
    }
}


fun getResId(resName: String, c: Class<*>): Int {
    return try {
        val idField: Field = c.getDeclaredField(resName)
        idField.getInt(idField)
    } catch (e: Exception) {
        e.printStackTrace()
        -1
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun MainScreen() {
    val tabs = listOf(
        TabItem.Iconic,
        TabItem.Wue,
        TabItem.Squeak,
        TabItem.GrowlAndRoar,
        TabItem.Rapid,
        TabItem.Other,
        TabItem.Combo,
        TabItem.Scream,
        TabItem.Weird,
        TabItem.Chattering,
        TabItem.Sing,
        TabItem.Noisy
    )
    TabItem.Iconic
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = { TopBar() },
    ) {
        Column {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}


@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        backgroundColor = Color.Red,
        contentColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    // OR ScrollableTabRow()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        contentColor = Color.Black,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun TabsPreview() {
    val tabs = listOf(
        TabItem.Iconic,
        TabItem.Chattering
    )
    val pagerState = rememberPagerState()
    Tabs(tabs = tabs, pagerState = pagerState)


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun TabsContentPreview() {
    val tabs = listOf(
        TabItem.Iconic,
        TabItem.Chattering
    )
    val pagerState = rememberPagerState()
    TabsContent(tabs = tabs, pagerState = pagerState)
}
package com.levp.gumisoundboard

import androidx.compose.runtime.Composable
import com.levp.gumisoundboard.ui.NewScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {

    object Iconic : TabItem("Iconic", { NewScreen(createList("iconic")) })
    object Wue : TabItem("Wue-wue", { NewScreen(createList("wuew")) })
    object Chattering : TabItem("Chattering", { NewScreen(createList("chtr")) })
    object Combo : TabItem("Combo", { NewScreen(createList("cmb")) })
    object GrowlAndRoar : TabItem("Growl & Roar", { NewScreen(createList("gnr")) })
    object Other : TabItem("Other", { NewScreen(createList("other")) })
    object Rapid : TabItem("Rapid", { NewScreen(createList("rapid")) })
    object Weird : TabItem("Weird", { NewScreen(createList("weird")) })
    object Scream : TabItem("Scream", { NewScreen(createList("scrm")) })
    object Squeak : TabItem("Squeak", { NewScreen(createList("sqk")) })
    object Sing : TabItem("Singing", { NewScreen(createList("sing")) })
    object Noisy : TabItem("Noisy", { NewScreen(createList("noisy")) })
}

fun createIconicList(): ArrayList<Sound> {
    return arrayListOf(
        Sound("Roar 1", "iconic_roar_1"),
        Sound("Roar 2", "iconic_roar_2"),
        Sound("Wue-wue 1", "iconic_wue_wue_1"),
        Sound("Wue-wue 2", "iconic_wue_wue_2"),
        Sound("Wue-wue 3", "iconic_wue_wue_3"),
    )
}

fun createList(tag: String): ArrayList<Sound> {
    val soundNames = pickWithTag(tag)
    val result = ArrayList<Sound>()
    for(soundPath in soundNames)
    {
        val soundName = soundPath.removePrefix(tag+"_").replace("_"," ")
        result.add(Sound(soundName, soundPath))
    }
    return result
}
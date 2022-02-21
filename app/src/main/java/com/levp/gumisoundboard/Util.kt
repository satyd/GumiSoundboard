package com.levp.gumisoundboard

import android.util.Log
import java.lang.reflect.Field

fun listRaw() {
    val fields: Array<Field> = R.raw::class.java.fields
    for (count in fields.indices) {
        Log.i("Raw Asset: ", fields[count].name)
    }
}

fun pickWithTag(tag: String): ArrayList<String> {
    val fields: Array<Field> = R.raw::class.java.fields
    val res = ArrayList<String>()
    for (count in fields.indices) {
        if (fields[count].name.contains(tag))
        {
            val newItem = fields[count].name
            //Log.v("hehe",newItem)
            res.add(newItem)
        }
    }
    return res
}
package com.levp.gumisoundboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    
    val stringLiveData: LiveData<String>
        get() = currentString

    private val currentString = MutableLiveData<String>()
    
    fun changeCurrentString(newStr : String){
        currentString.value = newStr
    }
}
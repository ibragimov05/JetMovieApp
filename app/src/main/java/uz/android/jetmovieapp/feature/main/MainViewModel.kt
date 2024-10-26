package uz.android.jetmovieapp.feature.main

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var currentIndex = mutableIntStateOf(0)

    fun updateIndex(index: Int) {
        currentIndex.intValue = index
    }
}
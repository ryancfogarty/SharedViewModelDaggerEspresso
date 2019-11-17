package com.example.sharedviewmodeldaggerespresso.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

abstract class MainViewModel : ViewModel() {
    abstract val rand: LiveData<Int>
}

class MainViewModelImpl : MainViewModel() {
    override val rand = MutableLiveData<Int>().apply {
        value = Random().nextInt()
    }
}

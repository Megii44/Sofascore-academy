package com.example.homework_5.ui.my_cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyCitiesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is My Cities Fragment"
    }
    val text: LiveData<String> = _text
}
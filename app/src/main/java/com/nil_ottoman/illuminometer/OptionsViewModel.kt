package com.nil_ottoman.illuminometer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class OptionsViewModel : ViewModel() {
    val options: MutableLiveData<Array<String>> by lazy {
        MutableLiveData<Array<String>>()
    }

    //private val originalList: LiveData<List<Item>>() = ...
    //val filteredList: LiveData<List<Item>> = ...
}
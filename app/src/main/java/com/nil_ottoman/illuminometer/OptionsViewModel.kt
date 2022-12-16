package com.nil_ottoman.illuminometer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class OptionsViewModel : ViewModel() {
    val options: MutableLiveData<Array<String>> by lazy {
        MutableLiveData<Array<String>>()
    }

    val isNeededSecondFragment: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(true)
    }
}
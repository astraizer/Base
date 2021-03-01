package com.kd.base.app

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kd.base.base.log.Logger
import com.kd.base.base.viewmodel.BaseViewModel


class ScreenViewModel @ViewModelInject constructor(
    logger: Logger
): BaseViewModel(ScreenViewModel::class.java.simpleName,logger) {

    private val _text = MutableLiveData<String>()
    val text : LiveData<String> = _text

    fun setText(value: String){
        _text.postValue(value)
    }

}
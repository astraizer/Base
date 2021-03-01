package com.kd.base.base.activity

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseViewModelActivity<VM: ViewModel> : BaseActivity(){

    protected lateinit var viewModel: VM
    protected abstract val viewModelClass: Class<VM>

    protected abstract fun bindObservers(viewModel: VM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(viewModelClass)
        bindObservers(viewModel)
    }
}
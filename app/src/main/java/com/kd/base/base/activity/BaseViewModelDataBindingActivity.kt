package com.kd.base.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseViewModelDataBindingActivity<VM: ViewModel, BIND: ViewDataBinding> : BaseViewModelActivity<VM>() {
    protected lateinit var binding: BIND

    protected abstract fun bindViewModel(binding: BIND, viewModel: VM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        bindViewModel(binding, viewModel)
    }
}
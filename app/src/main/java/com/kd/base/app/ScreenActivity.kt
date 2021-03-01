package com.kd.base.app

import com.kd.base.R
import com.kd.base.base.activity.BaseActivity
import com.kd.base.base.activity.BaseViewModelActivity
import com.kd.base.base.activity.BaseViewModelDataBindingActivity
import com.kd.base.databinding.ActivityScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenActivity : BaseViewModelDataBindingActivity<ScreenViewModel,ActivityScreenBinding>(){
    override val viewModelClass: Class<ScreenViewModel>
        get() = ScreenViewModel::class.java

    override val layoutId: Int
        get() = R.layout.activity_screen

    override fun bindViewModel(binding: ActivityScreenBinding, viewModel: ScreenViewModel) {
        binding.viewModel = viewModel
        viewModel.setText("Testing")
    }

    override fun bindObservers(viewModel: ScreenViewModel) {
    }


}
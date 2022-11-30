package com.jk.main

import androidx.lifecycle.ViewModelProvider
import com.jk.basis.BR
import com.jk.basis.R
import com.jk.basis.databinding.ActivityMainBinding
import com.jk.comm.JKBaseActivity
import com.jk.main.factory.MainFactory
import com.jk.main.viewmodel.MainViewModel

class MainActivity : JKBaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun onBindLayout(): Int {
        return R.layout.activity_main
    }

    override fun onBindVariableId(): Int {
        return BR.viewModel
    }

    override fun onBindViewModelFactory(): ViewModelProvider.Factory {
        return MainFactory.instance
    }

    override fun onBindViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun initViewObservable() {
    }

    override fun initView() {
    }

    override fun initData() {
        mBinding.mainTest.text = "测试"
    }



}
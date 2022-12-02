package com.jk.main

import androidx.lifecycle.ViewModelProvider
import com.jk.basis.BR
import com.jk.basis.R
import com.jk.basis.databinding.FragmentMainBinding
import com.jk.comm.base.JKBaseFragment
import com.jk.main.factory.MainFactory
import com.jk.main.viewmodel.MainFragmentViewModel

/**
 * description ：
 * project name：JKBasis
 * author : www.lijin@foxmail.com
 * creation date: 2022/12/2 09:54
 * @version 1.0
 */
class MainFragment : JKBaseFragment<FragmentMainBinding, MainFragmentViewModel>() {

    override fun onBindLayout(): Int {
        return R.layout.fragment_main
    }

    override fun onBindViewModelFactory(): ViewModelProvider.Factory {
        return MainFactory.instance
    }

    override fun onBindViewModel(): Class<MainFragmentViewModel> {
        return MainFragmentViewModel::class.java
    }

    override fun onBindVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewObservable() {

    }


}
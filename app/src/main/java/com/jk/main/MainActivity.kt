package com.jk.main

import androidx.lifecycle.ViewModelProvider
import com.jk.basis.BR
import com.jk.basis.R
import com.jk.basis.databinding.ActivityMainBinding
import com.jk.comm.base.JKBaseActivity
import com.jk.comm.immersion.StatusBarCompat
import com.jk.comm.util.JKUtil
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

    /**
     * 观察者回调
     */
    override fun initViewObservable() {
    }

    override fun firstLayout() {
        super.firstLayout()
        //设置灰度的方法应在setContentView之前
        //JKUtil.Instance.setViewGray(window.decorView)
    }

    override fun initView() {
        updateStatusBarCompat()
    }

    override fun initData() {

    }

    /**
     * 点击事件的初始化
     */
    override fun initClick() {
        super.initClick()
        initClick({
            when (it) {
                mBinding.mainTvOne -> updateStatusBarCompat()
                mBinding.mainTvTwo -> startFragment()
            }
        }, mBinding.mainTvOne,mBinding.mainTvTwo)
    }

    private var isStatusBarToWhite = false

    /**
     * 更改沉浸模式
     */
    private fun updateStatusBarCompat() {
        isStatusBarToWhite = if (isStatusBarToWhite) {
            //设置为其他颜色 则默认获取了状态栏目高度
            StatusBarCompat.setStatusBarColor(
                this, resources.getColor(R.color.color_f5f5f5),
                false
            )
            mBinding.mainTvOne.text = "更改沉浸模式（白）"
            false
        } else {
            //设置状态栏沉浸
            StatusBarCompat.setImmersionStateMode(this)
            mBinding.mainTvOne.text = "更改沉浸模式（沉浸）"
            //适配状态栏高度
            StatusBarCompat.setStatusBarHeight(this, mBinding.mainActionBarView)
            true
        }
    }

    /**
     * fragment示例
     */
    private fun startFragment() {
        val fm = supportFragmentManager
        val mainFragment = MainFragment()
        fm.beginTransaction().add(R.id.fragment_layout,mainFragment).commit()
    }


}
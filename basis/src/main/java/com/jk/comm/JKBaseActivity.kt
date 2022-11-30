package com.jk.comm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.jk.comm.model.BaseModel
import com.jk.comm.viewmodel.BaseViewModel

open abstract class JKBaseActivity<V : ViewDataBinding, VM : BaseViewModel<BaseModel>> : AppCompatActivity() {

    lateinit var mBinding: V
    lateinit var mViewModel: VM
    private var viewModelId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectLayout()
        initView()
        initData()
    }

    private fun injectLayout() {
        initViewDataBinding()
        initBaseViewObservable()
        initViewObservable()
    }

    /**
     * 数据和视图的绑定
     */
    private fun initViewDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, onBindLayout())
        viewModelId = onBindVariableId()
        mViewModel = createViewModel()
        mBinding.setVariable(viewModelId!!, mViewModel)
        this.lifecycle.addObserver(mViewModel)
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this, onBindViewModelFactory()).get(onBindViewModel())
    }


    abstract fun onBindLayout(): Int
    abstract fun onBindVariableId(): Int
    abstract fun onBindViewModelFactory(): ViewModelProvider.Factory
    abstract fun onBindViewModel(): Class<VM>
    abstract fun initViewObservable()

    abstract fun initView()
    abstract fun initData()

    /**
     * 一些在viewModel中的执行事件
     */
    private fun initBaseViewObservable() {

    }
}
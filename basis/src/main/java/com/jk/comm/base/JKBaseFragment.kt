package com.jk.comm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jk.comm.model.BaseModel
import com.jk.comm.viewmodel.BaseViewModel

/**
 * description ：
 * project name：JKBasis
 * author : www.lijin@foxmail.com
 * creation date: 2022/11/30 11:36
 * @version 1.0
 */
abstract class JKBaseFragment<V : ViewDataBinding, VM : BaseViewModel<BaseModel>> : Fragment() {

    lateinit var mBinding: V
    lateinit var mViewModel: VM
    private var viewModelId: Int? = 0
    lateinit var rootView : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = injectLayout(inflater, container)
        return rootView
    }

    fun injectLayout(inflater: LayoutInflater?, container: ViewGroup?): View {
        mBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), onBindLayout(), container, false)
        initViewDataBinding()
        initBaseViewObservable()
        initViewObservable()
        return mBinding.root
    }

    /**
     * 数据和视图的绑定
     */
    private fun initViewDataBinding() {
        viewModelId = onBindVariableId()
        mViewModel = createViewModel()
        mBinding.setVariable(viewModelId!!, mViewModel)
        this.lifecycle.addObserver(mViewModel)
    }

    /**
     * 一些在viewModel中的执行事件
     */
    private fun initBaseViewObservable() {

    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this, onBindViewModelFactory()).get(onBindViewModel())
    }


    abstract fun onBindLayout(): Int
    abstract fun onBindViewModelFactory(): ViewModelProvider.Factory
    abstract fun onBindViewModel(): Class<VM>
    abstract fun onBindVariableId(): Int
    abstract fun initViewObservable()

}
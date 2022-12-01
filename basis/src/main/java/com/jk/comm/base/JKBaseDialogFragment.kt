package com.jk.comm.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.jk.comm.R
import com.jk.comm.model.BaseModel
import com.jk.comm.util.RxBindingUtils
import com.jk.comm.viewmodel.BaseViewModel

/**
 * description ：
 * project name：JKBasis
 * author : www.lijin@foxmail.com
 * creation date: 2022/11/30 11:37
 * @version 1.0
 */
abstract class JKBaseDialogFragment<V : ViewDataBinding, VM : BaseViewModel<BaseModel>> : DialogFragment() {

    lateinit var mBinding: V
    lateinit var mViewModel: VM
    var viewModelId: Int = 0
    lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireActivity(), R.style.StyleDialogFragment)
        dialog.setCanceledOnTouchOutside(true)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = initializeLayout(container)
        RxBindingUtils.preventRepeatedClick(rootView) { v -> edHideSoft(v) }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //我们需要将窗口默认置于底部
        val window = dialog!!.window
        window!!.decorView.setPadding(0, 0, 0, 0);
        val attributes = window.attributes
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT
        attributes.dimAmount = 0.4f
        attributes.gravity = Gravity.BOTTOM
        window.attributes = attributes
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    /**
     * 关闭软键盘 一般为editext 只在dialog未 dismiss时有效 如果需要在点击外部区域收起软键盘  可以使用SoftKeyBoard监听软键盘的弹出 在销毁是使用toggleSoftInput去回收
     * 目前没想到好的方式去解决getWindowToken为null的问题
     * @param view
     */
    open fun edHideSoft(view: View) {
        val manager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    private fun initializeLayout(container: ViewGroup?): View {
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
        mBinding.setVariable(viewModelId, mViewModel)
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


    abstract fun initView()
    abstract fun initData()
}
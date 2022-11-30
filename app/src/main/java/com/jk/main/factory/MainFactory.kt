package com.jk.main.factory

import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.lifecycle.ViewModel
import com.jk.main.viewmodel.MainViewModel
import android.annotation.SuppressLint
import kotlin.jvm.Volatile
import java.lang.IllegalArgumentException

/**
 * description ：
 * project name：JKBasis
 * author : www.lijin@foxmail.com
 * creation date: 2022/11/30 14:58
 *
 * @version 1.0
 */
class MainFactory : NewInstanceFactory() {

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            MainFactory()
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
package com.jk.comm.util

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.view.View

/**
 * description ：
 * project name：JKBasis
 * author : www.lijin@foxmail.com
 * creation date: 2022/12/1 11:43
 * @version 1.0
 */
class JKUtil {

    //单例(双重校验锁)
    companion object {
        val Instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            JKUtil()
        }
    }

    /**
     * 将View设置为灰态
     */
    fun setViewGray(view : View){
        val paint = Paint()
        val cm  = ColorMatrix()
        cm.setSaturation(0f)
        paint.colorFilter = ColorMatrixColorFilter(cm)
        view.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
    }
}
package com.jk.comm.util;

import android.view.View;
import android.widget.AdapterView;

import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxAdapterView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import kotlin.Unit;

public class RxBindingUtils {

    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static final int MORE_MIN_CLICK_DELAY_TIME = 300;
    private static long lastClickTime;


    /**
     * 防止重复点击
     *
     * @param target 目标view
     * @param listener 监听器
     */
    public static void preventRepeatedClick(final View target, final View.OnClickListener listener) {
        RxView.clicks(target).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Consumer<Unit>() {
            @Override
            public void accept(Unit unit) throws Exception {
                if (listener != null) {
                    listener.onClick(target);
                }
            }
        });
    }


    /**
     * 防止重复点击
     *
     * @param target 目标view
     * @param listener 监听器
     */
    public static void preventRepeatedClick(final AdapterView target, final AdapterView.OnItemClickListener listener) {
        RxAdapterView.itemClicks(target).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                if (listener != null) {
                    listener.onItemClick(target,target.getChildAt(integer),integer,target.getChildAt(integer).getId());
                }
            }
        });
    }


    /**
     * 防止重复点击
     * @return  true 执行  false 重复的点击
     */
    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
            lastClickTime = curClickTime;
        }

        return flag;
    }


    /**
     * 防止重复点击
     * @return  true 执行  false 重复的点击
     */
    public static boolean isMoreFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MORE_MIN_CLICK_DELAY_TIME) {
            flag = true;
            lastClickTime = curClickTime;
        }
        return flag;
    }

}

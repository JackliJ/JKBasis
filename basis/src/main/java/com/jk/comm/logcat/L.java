/*
 * copywrite 2015-2020 深圳市万米德网络技术有限公司
 * 不能修改和删除上面的版权声明
 * 此代码属于技术开发组编写，在未经允许的情况下不得传播复制
 * ${file_name}
 * @Date ${date}${time}
 * @Author ${user}
 * ${filecomment}
 * ${package_declaration}
 *
 * ${typecomment}
 * ${type_declaration}
 */

package com.jk.comm.logcat;

import android.text.TextUtils;
import android.util.Log;


import com.jk.comm.config.Config;

import java.net.UnknownHostException;


public class L {
    private static final String TAG = "TanBao";
    private static final String DEFAULT_TAG_E = "error";

    public static void d(String msg) {
        if (Config.isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (Config.isDebug) {
            Log.v(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (Config.isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (Config.isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void e(Exception e) {
        if (Config.isDebug) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    /**
     * 以级别为 e 的形式输出LOG信息和Throwable
     */
    public static void e(String tag, String msg, Throwable tr) {
        if (Config.isDebug) {
            Log.e(tag, (TextUtils.isEmpty(msg) ? DEFAULT_TAG_E : msg)
                            + (tr instanceof UnknownHostException ? "\n" + tr.getMessage() : "") /*系统Log类自动排除UnknownHostException的输出*/
                    , tr);
        }
    }

    /**
     * 以级别为 e 的形式输出LOG信息和Throwable
     */
    public static void w(String tag, String msg, Throwable tr) {
        if (Config.isDebug) {
            Log.w(tag, (TextUtils.isEmpty(msg) ? DEFAULT_TAG_E : msg)
                            + (tr instanceof UnknownHostException ? "\n" + tr.getMessage() : "") /*系统Log类自动排除UnknownHostException的输出*/
                    , tr);
        }
    }


    public static void e(String msg, Throwable tr) {
		if (Config.isDebug) {
            Log.e(TAG, msg,tr);
		}
    }


    public static void w(String msg, Throwable tr) {
		if (Config.isDebug) {
            Log.w(TAG, msg,tr);
		}
    }


    public static void e(Throwable tr) {
		if (Config.isDebug) {
            Log.e(TAG, "",tr);
		}
    }

}

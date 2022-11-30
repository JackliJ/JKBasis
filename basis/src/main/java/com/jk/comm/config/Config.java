

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

package com.jk.comm.config;


import com.jk.comm.BuildConfig;

public final class Config {

    /**
     * 是否是测试环境，测试环境为true，正式环境false
     */
    public static boolean isDebug = BuildConfig.ISDEBUG;


}

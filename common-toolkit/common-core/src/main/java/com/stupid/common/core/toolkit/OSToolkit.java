package com.stupid.common.core.toolkit;

/**
 * 操作系统工具类
 */
public class OSToolkit {

    /**
     * 获取CPU核数
     */
    public static int getProcessors(){
        return Runtime.getRuntime().availableProcessors();
    }
}

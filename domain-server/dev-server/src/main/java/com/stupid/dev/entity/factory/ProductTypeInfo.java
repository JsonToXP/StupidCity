package com.stupid.dev.entity.factory;

import lombok.Data;

/**
 * 产品分类信息
 */
@Data
public class ProductTypeInfo {

    /** 门禁机 摄像头 继电器 烟感报警器 */
    private String topType;

    /**
     * 门禁机
     *  普通门禁机
     *  抓拍门禁机
     * 摄像头
     *  普通摄像头
     *  高空抛物摄像头
     *  人脸识别摄像头
     *  消防占道摄像头
     *  消控中心摄像头
     * 继电器
     *  普通继电器
     *  网络继电器
     * 烟感报警器
     *  普通烟感器
     *  网络烟感器
     **/
    private String secondType;
}

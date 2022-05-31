package com.stupid.dev.entity.factory;

import lombok.Data;

/**
 * 安装位置
 */
@Data
public class AreaInfo {

    /** 社区id */
    private String communityId;
    /** 小区id */
    private String uptownId;
    /** 楼栋id */
    private String bldgId;
    /** 单元id */
    private String unitId;
    /** 最小区域id */
    private String areaId;

}

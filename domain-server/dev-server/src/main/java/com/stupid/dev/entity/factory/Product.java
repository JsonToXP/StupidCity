package com.stupid.dev.entity.factory;

import lombok.Data;

/**
 * 产品信息
 */
@Data
public class Product {

    /** 产品类型 门禁机 摄像头 继电器 */
    private ProductTypeInfo productTypeInfo;

    /** 厂商 多度 慧居 海康 大华 研超 臻云 */
    private CompanyInfo companyInfo;

    /** 协议 socket ws http mqtt */
    private ProtocolInfo protocolInfo;

    /** 配置信息 */
    private ConfigurationInfo configurationInfo;

    /** 设备信息 */
    private DeviceInfo deviceInfo;

    /** 安装位置 */
    private AreaInfo areaInfo;

}

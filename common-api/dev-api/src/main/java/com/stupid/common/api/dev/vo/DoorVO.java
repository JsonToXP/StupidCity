package com.stupid.common.api.dev.vo;
import com.stupid.common.api.core.pojo.ParamValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DoorVO {

    @NotBlank(message = "门禁机ID不能为空",
            groups = {ParamValidationGroup.Update.class})
    private Long id;

    @NotBlank(message = "门禁机名称不能为空",
            groups = {ParamValidationGroup.Insert.class,
                    ParamValidationGroup.Update.class})
    private String name;

    @NotBlank(message = "设备号不能为空",
            groups = {ParamValidationGroup.Insert.class,
                    ParamValidationGroup.Update.class})
    private String devCode;

    private String state;

    /** 最后心跳时间 */
    private String activeTime;

    @NotBlank(message = "创建人不能为空",
            groups = {ParamValidationGroup.Insert.class,
                    ParamValidationGroup.Update.class})
    private String createUser;

    private String createTime;

    /** 附加表信息 */
    @NotBlank(message = "附加信息不能为空",
            groups = {ParamValidationGroup.Insert.class,
                    ParamValidationGroup.Update.class})
    private String remark;
}

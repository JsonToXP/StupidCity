package com.stupid.dev.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DoorExtras {

    private Long id;
    private String remark;
}

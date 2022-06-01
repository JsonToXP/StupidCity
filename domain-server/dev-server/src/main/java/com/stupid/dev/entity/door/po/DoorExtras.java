package com.stupid.dev.entity.door.po;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DoorExtras {

    private Long id;
    private String remark;
}

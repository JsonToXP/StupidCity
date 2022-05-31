package com.stupid.user.infrastructure.toolkit;

import com.stupid.common.api.user.vo.UserVO;
import com.stupid.user.entity.po.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IBeanConvert {

    @Mappings({
            @Mapping(target = "state",ignore = true),
            @Mapping(target = "sex",expression = "java((short)(userVO.getSex().equals(\"女\")?0:1))")
    })
    UserInfo userVO2PO(UserVO userVO);

    @Mappings({
            @Mapping(target = "sex",expression = "java(userInfo.getSex()==0?\"女\":\"男\")"),
            @Mapping(target = "state",expression = "java(userInfo.getState()==0?\"正常\":\"异常\")")
    })
    UserVO userPO2VO(UserInfo userInfo);
}

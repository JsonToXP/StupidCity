package com.stupid.community.infrastructure.toolkit;

import com.stupid.common.api.area.vo.CommunityVO;
import com.stupid.community.entity.po.CommunityInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBeanConvert {

    CommunityInfo communityVO2PO(CommunityVO communityVO);

    CommunityVO communityPO2VO(CommunityInfo communityInfo);
}

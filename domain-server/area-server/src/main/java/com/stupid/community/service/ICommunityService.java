package com.stupid.community.service;

import com.stupid.common.api.area.vo.CommunityVO;

public interface ICommunityService {

    String saveCommunity(CommunityVO communityVO);

    CommunityVO queryCommunityInfo(Long id);
}

package com.stupid.community.service.impl;

import com.stupid.common.api.area.vo.CommunityVO;
import com.stupid.community.dao.repository.CommunityRepository;
import com.stupid.community.entity.po.CommunityInfo;
import com.stupid.community.infrastructure.toolkit.IBeanConvert;
import com.stupid.community.service.ICommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 社区服务
 */
@Service
@Slf4j
public class CommunityServiceImpl implements ICommunityService {

    @Autowired
    private IBeanConvert beanConvert;

    @Autowired
    private CommunityRepository communityRepository;

    @Override
    public String saveCommunity(CommunityVO communityVO) {
        CommunityInfo communityInfo = beanConvert.communityVO2PO(communityVO);
        int row = communityRepository.saveCommunity(communityInfo);
        return row==1?"success":"fail";
    }

    @Override
    public CommunityVO queryCommunityInfo(Long id) {
        CommunityInfo communityInfo = communityRepository.queryCommunityInfo(id);
        CommunityVO communityVO = beanConvert.communityPO2VO(communityInfo);
        return communityVO;
    }
}

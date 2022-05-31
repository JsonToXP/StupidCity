package com.stupid.community.dao.repository;

import com.stupid.community.dao.mapper.ICommunityInfoMapper;
import com.stupid.community.entity.po.CommunityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommunityRepository {

    @Autowired
    private ICommunityInfoMapper communityInfoMapper;

    /**
     * 保存社区
     */
    public int saveCommunity(CommunityInfo communityInfo){
        int row = communityInfoMapper.insertCommunityInfo(communityInfo);
        return row;
    }

    /**
     * 根据id查询社区
     */
    public CommunityInfo queryCommunityInfo(Long id){
        CommunityInfo communityInfo = communityInfoMapper.selectOneById(id);
        return communityInfo;
    }
}

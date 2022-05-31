package com.stupid.community.dao.mapper;

import com.stupid.community.entity.po.CommunityInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ICommunityInfoMapper {

    /**
     * 新增社区
     */
    @Insert("insert into community_info\n" +
            "(name)\n" +
            "VALUES\n" +
            "(#{name})")
    int insertCommunityInfo(CommunityInfo communityInfo);

    /**
     * 查询社区
     */
    @Select("select id,name\n" +
            "from community_info\n" +
            "where id = #{id}")
    CommunityInfo selectOneById(Long id);
}

package com.stupid.user.dao.mapper;

import com.stupid.user.entity.po.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IUserInfoMapper {

    @Insert("insert into user_info\n" +
            "(name,account,age,sex,birthday,create_user)\n" +
            "VALUES\n" +
            "(#{name},#{account},#{age},#{sex},#{birthday},#{createUser})")
    int insertUserInfo(UserInfo userInfo);

    /**
     * 根据账户查询用户信息
     */
    @Select("select id,name,account,age,sex,birthday,state,create_user as createUser\n" +
            "from user_info\n" +
            "where account = #{account}")
    UserInfo selectOneByAccount(String account);
}

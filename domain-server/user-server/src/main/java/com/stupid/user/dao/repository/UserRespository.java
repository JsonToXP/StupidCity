package com.stupid.user.dao.repository;

import com.stupid.user.dao.mapper.IUserInfoMapper;
import com.stupid.user.entity.po.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRespository {

    @Autowired
    private IUserInfoMapper userInfoMapper;

    public int saveUser(UserInfo userInfo){
        int row = userInfoMapper.insertUserInfo(userInfo);
        return row;
    }

    public UserInfo queryOneByAccount(String account){
        UserInfo userInfo = userInfoMapper.selectOneByAccount(account);
        return userInfo;
    }
}

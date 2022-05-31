package com.stupid.user.service.impl;

import com.stupid.common.api.user.vo.UserVO;
import com.stupid.user.dao.repository.UserRespository;
import com.stupid.user.entity.po.UserInfo;
import com.stupid.user.infrastructure.toolkit.IBeanConvert;
import com.stupid.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private IBeanConvert beanConvert;


    /**
     * 保存用户
     */
    @Override
    public String saveUser(UserVO userVO) {
        UserInfo userInfo = beanConvert.userVO2PO(userVO);
        int row = userRespository.saveUser(userInfo);
        return row==1?"success":"fail";
    }

    /**
     * 根据账号查询用户
     */
    @Override
    public UserVO queryUserInfo(String account) {
        UserInfo userInfo = userRespository.queryOneByAccount(account);
        UserVO userVO = beanConvert.userPO2VO(userInfo);
        return userVO;
    }
}

package com.stupid.user.service;

import com.stupid.common.api.user.vo.UserVO;

public interface IUserService {

    String saveUser(UserVO userVO);

    UserVO queryUserInfo(String account);
}

package com.stupid.user.controller;

import com.stupid.common.api.core.vo.ResponseObject;
import com.stupid.common.api.user.vo.UserVO;
import com.stupid.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 保存用户
     */
    @PostMapping("save")
    public ResponseObject<String> saveUser(@RequestBody UserVO userVO){
        String resp = userService.saveUser(userVO);
        return new ResponseObject<>(resp);
    }

    /**
     * 根据账号查询用户用户
     */
    @GetMapping("query/{account}")
    public ResponseObject<UserVO> queryUserInfo(@PathVariable String account){
        log.info("queryUserInfo req : {}",account);
        UserVO resp = userService.queryUserInfo(account);
        return new ResponseObject<>(resp);
    }
}

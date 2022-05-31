package com.stupid.dev.feign;

import com.stupid.common.api.core.vo.ResponseObject;
import com.stupid.common.api.user.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "user-server",path = "user")
public interface IUserFeign {

    @GetMapping("query/{account}")
    ResponseObject<UserVO> queryUserInfo(@PathVariable(name = "account") String account);
}

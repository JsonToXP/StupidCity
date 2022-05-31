package com.stupid.dev.feign;

import com.stupid.common.api.core.vo.ResponseObject;
import com.stupid.common.api.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeignReposity {

    @Autowired
    private IUserFeign userFeign;

    /**
     * 查询用户信息
     */
    public UserVO queryUserInfoFromUserServer(String account){

        ResponseObject<UserVO> resp = userFeign.queryUserInfo(account);
        if(resp!=null&&resp.getCode()==0){
            return resp.getData();
        }
        return null;
    }
}

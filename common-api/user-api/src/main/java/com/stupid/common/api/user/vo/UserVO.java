package com.stupid.common.api.user.vo;

import lombok.Data;

@Data
public class UserVO {

    private Long id;
    private String name;
    private String account;
    private String sex;
    private Short age;
    private String birthday;
    private String state;
    private String createUser;

}

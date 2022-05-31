package com.stupid.user.entity.po;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserInfo {

    private Long id;
    private String name;
    private String account;
    private Short sex;
    private Short age;
    private String birthday;
    private String createUser;
    private Short state;
    private Timestamp createTime;

}

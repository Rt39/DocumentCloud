package com.hzqing.admin.domain.system;

import lombok.Data;

import java.util.List;


@Data
public class UserInfo {

    private int id;

    private String name;

    private String username;

    private String password;

    private String[] resources;

    private String[] roles;

    private String token;

    /**
     * 用户头像
     */
    private String img;

}

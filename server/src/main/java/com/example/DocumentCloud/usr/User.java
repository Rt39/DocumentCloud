package com.hzqing.admin.domain.system;

import com.hzqing.admin.domain.base.Base;
import lombok.Data;


@Data
public class User extends Base {

    private int id;

    private String name;

    private String username;

    private String password;

    private String email;

    private String phone;

    /**
     * 用户头像
     */
    private String img;


    /**
     * 备注说明
     */
    private String remark;

}

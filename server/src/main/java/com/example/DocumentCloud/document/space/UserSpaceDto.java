package com.hzqing.admin.dto.space;

import com.hzqing.admin.domain.space.UserSpace;
import lombok.Data;

@Data
public class UserSpaceDto extends UserSpace {
    /**
     * 用户名称
     */
    private String fullName;
}

package com.hzqing.admin.dto.space;

import com.hzqing.admin.domain.space.Space;
import lombok.Data;


@Data
public class SpaceDto extends Space {
    /**
     * 空间成员操作权限  1 浏览者 2 编辑者 3 管理员
     */
    private Integer privilege;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 更新人名称
     */
    private String updateName;
}

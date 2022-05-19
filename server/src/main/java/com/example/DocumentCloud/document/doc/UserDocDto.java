package com.hzqing.admin.dto.doc;

import com.hzqing.admin.domain.doc.UserDoc;
import lombok.Data;


@Data
public class UserDocDto extends UserDoc {
    /**
     * 用户名称
     */
    private String fullName;
}

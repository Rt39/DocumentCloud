package com.hzqing.admin.dto.doc;

import com.hzqing.admin.domain.doc.Content;
import com.hzqing.admin.domain.doc.Doc;
import lombok.Data;

import java.util.List;


@Data
public class DocDto extends Doc {
    /**
     * 空间成员操作权限  1 浏览者 2 编辑者 3 管理员
     */
    private Integer privilege;

    /**
     * 作者或者创建名称
     */
    private String createName;

    /**
     * 文档内容
     */
    private List<Content> contents;
}

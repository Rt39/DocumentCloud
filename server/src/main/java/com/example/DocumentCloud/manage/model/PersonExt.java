package com.autumn.manage.model;

/**
 * @program: PersonExt
 * @description:
 * @author: 秋雨
 * @createTime: 2020-07-22 16:45
 **/
public class PersonExt extends Person {
    private String corporationName;
    private String typeName;

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

package com.generator.constant;

import java.util.ArrayList;
import java.util.List;

public enum TplEnum {

    MAPPER("mapper","mapper.vm","0"),
    MAPPER_XML("mapperXml","mapperXml.vm","0"),
    ENTITY("entity","entity.vm","0"),
    SERIVCE("serivce","serivce.vm","0"),
    SERIVCE_IMPL("serivceImpl","serivceImpl.vm","0"),
    CONTROLLER("controller","controller.vm","0"),
    DTO("dto","dto.java.vm","1"),
    VO("vo","vo.java.vm","1"),
    LISTHTML("listHtml","list.html.vm","1"),
    EDITHTML("editHtml","edit.html.vm","1"),
    LISTJS("listJs","list.js.vm","1"),
    EDITJS("editJs","edit.js.vm","1");



    private String lable;
    private String value;
    private String type;


    public static List<String> getTplList(String type) {
        List<String> list = new ArrayList<>();
        for (TplEnum _enum : TplEnum.values()) {
            if(_enum.getType().equals(type)){
                list.add(_enum.getLable());
            }
        }
        return list;
    }

    /**
     * 方法描述: 枚举转换
     *
     * @param lable
     */
    public static TplEnum parseOf(String lable) {
        for (TplEnum item : values()) {
            if (item.getLable().equals(lable)) {
                return item;
            }
        }
        return null;
    }

    TplEnum(String lable, String value, String type) {
        this.lable = lable;
        this.value = value;
        this.type = type;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

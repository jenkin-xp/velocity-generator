package com.generator.constant;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public enum JdbcTypeEnum {

    STRING("java.lang.String","VARCHAR2","String"),
    INTEGER("java.lang.Integer","NUMBER","Integer"),
    FLOAT("java.lang.Integer","FLOAT","Integer"),
    CLOB("java.lang.String","CLOB","Integer"),
    DATE("java.util.Date","DATE","Date");

    private String javaType;
    private String jdbcType;
    private String name;


    public static List<JSONObject> getList() {
        List<JSONObject> list = new ArrayList<>();
        for (JdbcTypeEnum _enum : JdbcTypeEnum.values()) {
            JSONObject jo = new JSONObject();
            jo.put("javaType",_enum.getJavaType());
            jo.put("jdbcType",_enum.getJdbcType());
            list.add(jo);
        }
        return list;
    }

    /**
     * 方法描述: 枚举转换
     *
     * @param jdbcType key
     * @return ResCode ResCode
     */
    public static JdbcTypeEnum parseOf(String jdbcType) {
        for (JdbcTypeEnum item : values()) {
            if (item.getJdbcType().equals(jdbcType)) {
                return item;
            }
        }
        return null;
    }

    JdbcTypeEnum(String javaType, String jdbcType, String name) {
        this.javaType = javaType;
        this.jdbcType = jdbcType;
        this.name = name;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

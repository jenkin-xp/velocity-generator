package com.generator.model;

import java.io.Serializable;

/**
 * <p>
 * 表信息
 * </p>
 *
 * @author lether
 * @since 2017-11-16
 */
public class ColumnDo implements Serializable{

    private static final long serialVersionUID = 1L;


    private String name;
    private String comment;
    private String jdbcType;
    private String javaType;
    private String tableName;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return "ColumnDo{" +
				"name='" + name + '\'' +
				", comment='" + comment + '\'' +
				", jdbcType='" + jdbcType + '\'' +
				", javaType='" + javaType + '\'' +
				", tableName='" + tableName + '\'' +
				'}';
	}
}

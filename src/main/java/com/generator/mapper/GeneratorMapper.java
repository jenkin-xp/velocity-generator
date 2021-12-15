package com.generator.mapper;

import com.generator.model.ColumnDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 用户信息表 Mapper 接口
 * </p>
 *
 * @author lether
 * @since 2017-11-17
 */
public interface GeneratorMapper {

    @Select("SELECT TABLE_NAME, TABLE_COMMENT " +
            "FROM information_schema.TABLES " +
            "WHERE table_schema = #{tableSchema, jdbcType=VARCHAR} " +
            "ORDER BY TABLE_NAME")
    List<Map<String,String>> findListTable(@Param("tableSchema") String tableSchema);

    List<ColumnDo> findListColumn(String[] tableNames);

}

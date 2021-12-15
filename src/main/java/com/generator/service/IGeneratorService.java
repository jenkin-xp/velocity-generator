package com.generator.service;

import com.generator.model.ColumnDo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author lether
 * @since 2017-11-17
 */
public interface IGeneratorService {

    List<Map<String,String>> findListTable(String tableSchema);
    List<ColumnDo> findListColumn(String tableName);

}

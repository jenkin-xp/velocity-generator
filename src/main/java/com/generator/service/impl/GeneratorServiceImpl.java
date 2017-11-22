package com.generator.service.impl;

import com.generator.constant.JdbcTypeEnum;
import com.generator.mapper.GeneratorMapper;
import com.generator.model.ColumnDo;
import com.generator.service.IGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author lether
 * @since 2017-11-17
 */
@Service
public class GeneratorServiceImpl implements IGeneratorService {

    @Autowired
    GeneratorMapper mapper;

    @Override
    public List<Map<String,String>> findListTable() {
        return mapper.findListTable();
    }

    @Override
    public List<ColumnDo> findListColumn(String tableName) {

        String[] tables = tableName.split("\\.");

        List<ColumnDo> listCol = mapper.findListColumn(tables);
        for (int i = 0; i < listCol.size(); i++) {
            ColumnDo columnDo =  listCol.get(i);
            JdbcTypeEnum em = JdbcTypeEnum.parseOf(columnDo.getJdbcType());
            if(null != em){
                columnDo.setJavaType(em.getJavaType());
            }
        }
        return listCol;
    }
}

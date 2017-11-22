package com.generator.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.generator.constant.TplEnum;
import com.generator.dto.GeneratorDto;

public class MybatisPlusGenerator {


    public static void generatorTpl(GeneratorDto generatorDto){
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(generatorDto.getOutputDir());//这里写你自己的java目录
        gc.setFileOverride(true);//是否覆盖
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor(generatorDto.getAuthor());
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.ORACLE);
        dsc.setTypeConvert(new OracleTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                return super.processTypeConvert(fieldType);
            }
        });
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("demo");
        dsc.setPassword("demo");
        dsc.setUrl("jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //strategy.setTablePrefix(new String[]{"_"});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        String[] tables = generatorDto.getTableNames().split("\\.");
        strategy.setInclude(tables);
        // 自定义 service 父类
        strategy.setSuperServiceClass("com.demo.core.component.service.IService");
        // 自定义 service 实现类父类
        strategy.setSuperServiceImplClass("com.demo.core.component.service.ServiceImpl");
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);
        String parantPkg = generatorDto.getPackageDir();
        pc.setEntity(parantPkg + ".model");
        pc.setMapper(parantPkg + ".mapper");
        pc.setXml(parantPkg + ".mapper.xml");
        pc.setService(parantPkg + ".service");
        pc.setServiceImpl(parantPkg + ".service.impl");
        pc.setController(parantPkg + ".controller");
        mpg.setPackageInfo(pc);

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setController(null);//模块如果设置 空 OR Null 将不生成该模块。
        tc.setMapper(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        tc.setXml(null);
        tc.setEntity(null);
        String[] tpls = generatorDto.getTpls().split("\\.");
        for (String tpl : tpls) {
            TplEnum tplem = TplEnum.parseOf(tpl);
            switch (tplem){
                case MAPPER:
                    tc.setMapper("/templates/mapper.java.vm");
                    break;
                case MAPPER_XML:
                    tc.setXml("/templates/mapper.xml.vm");
                    break;
                case SERIVCE:
                    tc.setService("/templates/service.java.vm");
                    break;
                case SERIVCE_IMPL:
                    tc.setServiceImpl("/templates/serviceImpl.java.vm");
                    break;
                case ENTITY:
                    tc.setEntity("/templates/entity.java.vm");
                    break;
                case CONTROLLER:
                    tc.setController("/templates/controller.java.vm");
                    break;
            }
            
        }
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();
    }


}

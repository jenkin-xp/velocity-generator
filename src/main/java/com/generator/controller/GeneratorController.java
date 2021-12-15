package com.generator.controller;


import com.generator.config.DataSource;
import com.generator.constant.JdbcTypeEnum;
import com.generator.constant.TplEnum;
import com.generator.dto.*;
import com.generator.model.ColumnDo;
import com.generator.service.IGeneratorService;
import com.generator.util.MybatisPlusGenerator;
import com.generator.util.VelocityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GeneratorController {

    @Autowired
    private IGeneratorService iGeneratorService;

    @Autowired
    private DataSource dataSource;

    @Value("${table.schema}")
    private String tableSchema;

    @GetMapping("/table")
    public BusResult baseTable(){
        return BusResult.build(BusCode.SUCCESS,iGeneratorService.findListTable(tableSchema));
    }

    @GetMapping("/filed")
    public BusResult tableCol(@RequestParam String names){
        List<ColumnDo> list = iGeneratorService.findListColumn(names);
        return BusResult.build(BusCode.SUCCESS,list);
    }

    @GetMapping("/template")
    public BusResult template(){
        return BusResult.build(BusCode.SUCCESS, TplEnum.getTplList("0"));
    }

    @GetMapping("/fieldType")
    public BusResult fieldType(){
        return BusResult.build(BusCode.SUCCESS, JdbcTypeEnum.getList());
    }

    @PostMapping("/generator")
    public BusResult generator(@RequestBody GeneratorDto dto){
        MybatisPlusGenerator.generatorTpl(dto, dataSource);
        return BusResult.build(BusCode.SUCCESS);
    }


    @PostMapping("/generatorDtoOrVo")
    public BusResult generatorDtoOrVo(@RequestBody GeneratorDto dto){
        VelocityUtil.createModel(dto);
        return BusResult.build(BusCode.SUCCESS);
    }

    @PostMapping("/generatorHtml")
    public BusResult generatorHtml(@RequestBody GeneratorDto dto){
        VelocityUtil.createModel(dto);
        return BusResult.build(BusCode.SUCCESS);
    }



}

package com.generator.util;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.generator.constant.JdbcTypeEnum;
import com.generator.constant.TplEnum;
import com.generator.dto.GeneratorDto;
import com.generator.model.ColumnDo;
import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class VelocityUtil {


    public static void createModel(GeneratorDto dto){
        VelocityUtil util = new VelocityUtil();
        VelocityEngine ve = util.initVe();
        String[] tpls = dto.getTpls().split("\\.");
        VelocityContext ctx = util.buildCtx(dto);
        for (int i = 0; i < tpls.length; i++) {
            String tplStr = tpls[i];
            TplEnum tplEm = TplEnum.parseOf(tplStr);
            String tempValue = tplEm.getValue();
            tempValue = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL,tempValue);
            String dirStr = "";
            if(tplEm.equals(TplEnum.DTO) || tplEm.equals(TplEnum.VO)){
                dirStr = dto.getPackageDir().replaceAll("\\.","\\\\") + "\\" + tplStr + "\\";
            }

            String fileName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL,dto.getName());
            String getOutFile = dto.getOutputDir() + "\\" +dirStr +  fileName + tempValue.substring(0,tempValue.lastIndexOf(".vm"));
            Template tpl = ve.getTemplate("/templates/" +tplEm.getValue());
            util.merge(tpl,ctx,getOutFile);
        }
        System.out.println("success...");
    }


    private VelocityEngine initVe(){
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty("input.encoding", "UTF-8");
        ve.setProperty("output.encoding", "UTF-8");
        ve.init();
        return ve;
    }


    private VelocityContext buildCtx(GeneratorDto dto){

        String jsonStr = dto.getJsonStr();

        if(Strings.isNullOrEmpty(jsonStr)){
            return null;
        }

        List<ColumnDo> list = JSONArray.parseArray(jsonStr,ColumnDo.class);

        Set<String> pkg = new HashSet<>();

        List<Map<String,String>> listmap = new ArrayList<>();
        for (ColumnDo columnDo : list) {
            pkg.add(columnDo.getJavaType());//?????????

            Map<String,String> map = new HashMap<>();
            String fieldName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,columnDo.getName());
            map.put("fieldName",fieldName);
            String methodName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,columnDo.getName());
            map.put("methodName",methodName);
            map.put("comment",columnDo.getComment());

            JdbcTypeEnum jdem = JdbcTypeEnum.parseOf(columnDo.getJdbcType());
            map.put("javaType",jdem.getName());
            listmap.add(map);
        }

        VelocityContext ctx = new VelocityContext();

        ctx.put("package",dto.getPackageDir()+"."+dto.getTpls());
        ctx.put("importPackages",pkg);
        ctx.put("comment",dto.getComments());
        ctx.put("author",dto.getAuthor());

        Date nowTime=new Date();
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ctx.put("date",time.format(nowTime));

        String modelname = dto.getName();
        modelname = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL,modelname);
        ctx.put("entity",modelname);
        ctx.put("fieldList",listmap);

        return ctx;
    }


    private void merge(Template template, VelocityContext ctx, String outputFile) {
        try {
            File file = new File(outputFile);
            if (!file.getParentFile().exists()) {
                // ??????????????????????????????????????????????????????
                if (!file.getParentFile().mkdirs()) {
                    return;
                }
            }
            FileOutputStream fos = new FileOutputStream(outputFile);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, ConstVal.UTF8));
            template.merge(ctx, writer);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        String jsonStr = "[{\"comment\":\"????????????id\",\"javaType\":\"java.lang.String\",\"jdbcType\":\"VARCHAR2\",\"name\":\"CATEGORY_ID\",\"tableName\":\"COURSE_CATEGORY\"},{\"comment\":\"??????????????????\",\"javaType\":\"java.lang.String\",\"jdbcType\":\"VARCHAR2\",\"name\":\"CATEGORY_NAME\",\"tableName\":\"COURSE_CATEGORY\"},{\"comment\":\"????????????\",\"javaType\":\"java.util.Date\",\"jdbcType\":\"DATE\",\"name\":\"CREATE_TIME\",\"tableName\":\"COURSE_CATEGORY\"},{\"comment\":\"sdfsfsfsf\",\"name\":\"COURSE_LEVEL\",\"javaType\":\"java.lang.Integer\"}]";
//
//        String modelname = "user";
//        modelname = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL,modelname);
//
//
//
//        List<ColumnDo> list = JSONArray.parseArray(jsonStr,ColumnDo.class);
//
//        Set<String> pkg = new HashSet<>();
//
//        List<Map<String,String>> listmap = new ArrayList<>();
//        for (ColumnDo dto : list) {
//            System.out.println("columnDo.toString() = " + dto.toString());
//            pkg.add(dto.getJavaType());//?????????
//
//            Map<String,String> map = new HashMap<>();
//            String fieldName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,dto.getName());
////            System.out.println("fieldName = " + fieldName);
//            map.put("fieldName",fieldName);
//            String methodName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,dto.getName());
//            map.put("methodName",methodName);
//            map.put("comment",dto.getComment());
//            map.put("javaType",dto.getJavaType());
//            listmap.add(map);
//        }

        String a = "com.aa";
        String bb = a.replaceAll("\\.","\\\\");
        System.out.println("bb = " + bb);


    }
}

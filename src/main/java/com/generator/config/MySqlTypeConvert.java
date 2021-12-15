package com.generator.config;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.select.BranchBuilder;
import com.baomidou.mybatisplus.generator.config.converts.select.Selector;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

/**
 * @Description 功能概述
 * @Author xp
 * @Date 2021/12/15 18:59
 * @Version V1.0
 **/
public class MySqlTypeConvert implements ITypeConvert {
    
    public static final MySqlTypeConvert INSTANCE = new MySqlTypeConvert();

    public MySqlTypeConvert() {
    }

    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
        return use(fieldType).test(containsAny(new CharSequence[]{"char", "text", "json", "enum"}).then(DbColumnType.STRING)).test(contains("bigint").then(DbColumnType.LONG)).test(containsAny(new CharSequence[]{"tinyint(1)", "bit"}).then(DbColumnType.BOOLEAN)).test(contains("int").then(DbColumnType.INTEGER)).test(contains("decimal").then(DbColumnType.BIG_DECIMAL)).test(contains("clob").then(DbColumnType.CLOB)).test(contains("blob").then(DbColumnType.BLOB)).test(contains("binary").then(DbColumnType.BYTE_ARRAY)).test(contains("float").then(DbColumnType.FLOAT)).test(contains("double").then(DbColumnType.DOUBLE)).test(containsAny(new CharSequence[]{"date", "time", "year"}).then((t) -> toDateType(config, t))).or(DbColumnType.STRING);
    }

    Selector<String, IColumnType> use(String param) {
        return new Selector(param.toLowerCase());
    }

    BranchBuilder<String, IColumnType> containsAny(CharSequence... values) {
        return BranchBuilder.of((s) -> {
            CharSequence[] var2 = values;
            int var3 = values.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                CharSequence value = var2[var4];
                if (s.contains(value)) {
                    return true;
                }
            }

            return false;
        });
    }

    BranchBuilder<String, IColumnType> contains(CharSequence value) {
        return BranchBuilder.of((s) -> s.contains(value));
    }
    
    public static IColumnType toDateType(GlobalConfig config, String type) {
        byte var3;
        switch(config.getDateType()) {
            case ONLY_DATE:
                return DbColumnType.DATE;
            case SQL_PACK:
                var3 = -1;
                switch(type.hashCode()) {
                    case 3076014:
                        if (type.equals("date")) {
                            var3 = 0;
                        }
                        break;
                    case 3560141:
                        if (type.equals("time")) {
                            var3 = 2;
                        }
                        break;
                    case 3704893:
                        if (type.equals("year")) {
                            var3 = 1;
                        }
                }

                switch(var3) {
                    case 0:
                    case 1:
                        return DbColumnType.DATE_SQL;
                    case 2:
                        return DbColumnType.TIME;
                    default:
                        return DbColumnType.TIMESTAMP;
                }
            case TIME_PACK:
                var3 = -1;
                switch(type.hashCode()) {
                    case 3076014:
                        if (type.equals("date")) {
                            var3 = 0;
                        }
                        break;
                    case 3560141:
                        if (type.equals("time")) {
                            var3 = 1;
                        }
                        break;
                    case 3704893:
                        if (type.equals("year")) {
                            var3 = 2;
                        }
                }

                switch(var3) {
                    case 2:
                        return DbColumnType.YEAR;
                    default:
                        return DbColumnType.DATE;
                }
            default:
                return DbColumnType.STRING;
        }
    }
}

/*
 * Copyright (c) 2020-2030 丰尚·深圳
 * 不能修改和删除上面的版权声明
 * 此代码属于丰尚智慧农牧科技有限公司部门编写，在未经允许的情况下不得传播复制
 */
package com.fs.zhnm.edge.data.center;

import cn.hutool.setting.dialect.Props;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 请加入注释
 *
 * @author chenqilin
 * @date 2022-04-27 13:56:50
 */
public class CodeGenerator {
    public static String SERIALVERSIONUID="39af423b3e3b719e02c68b3305c90ef23bdca1db";
    private static final Props PROPS = new Props("generator.properties");

    public static void main(String[] args) {
//        generatorOne();
        generatorTwo();

    }

/*    public static void generatorOne() {
        FastAutoGenerator.create(PROPS.getStr("url"), PROPS.getStr("username"), PROPS.getStr("password"))
                .globalConfig(builder -> {
                    builder.author("jay") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir(PROPS.getStr("targetDir")); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(PROPS.getStr("package")) // 设置父包名
                            .moduleName(PROPS.getStr("fatherPackageName")) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, PROPS.getStr("targetDir"))); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    String[] tables = PROPS.getStr("tables").split(",");
//                    builder.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                    for (String table : tables) {
                        builder.addInclude(table); // 设置需要生成的表名
                    }
                    builder.entityBuilder().enableLombok();
                    builder.controllerBuilder().enableRestStyle();
                    builder.mapperBuilder()
                            .convertMapperFileName(entityName -> entityName + "Dao")
                            .convertXmlFileName(entityName -> entityName + "Mapper");
                    builder.serviceBuilder()
                            .convertServiceFileName((entityName -> entityName + "Service"))
                            .convertServiceImplFileName(entityName -> entityName + "ServiceImpl");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }*/

    public static void generatorTwo() {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(PROPS.getStr("targetDir"));

        gc.setAuthor(PROPS.getStr("author"));
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        //UserServie
        gc.setServiceName("%sService");	//去掉Service接口的首字母I
        gc.setServiceImplName("%sServiceImpl");	//去掉Service接口的首字母I
        gc.setXmlName("%sMapper");
        gc.setMapperName("%sDao");

//        gc.setIdType(IdType.ID_WORKER); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(PROPS.getStr("url"));
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(PROPS.getStr("username"));
        dsc.setPassword(PROPS.getStr("password"));
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(PROPS.getStr("fatherPackageName")); //模块名
        //包  com.test
        pc.setParent(PROPS.getStr("package"));
        //包  com.test.controller
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("dao");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();

        strategy.setInclude(PROPS.getStr("tables").split(",")); //根据该表明自动生成响应的文件以及文件夹
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
//        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);


        // 6、执行
        mpg.execute();

    }
}

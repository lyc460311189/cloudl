package com.dwise.workeasy.codegen.mybatisplus.generator.utils;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.dwise.workeasy.codegen.mybatisplus.generator.model.GenInfo;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 代码生成
 *
 * @author: lvjingran
 */
public class MybatisGenUtils {

    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (UtilCollection.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    /**
     * 代码生成
     *
     * @param genInfo
     */
    public static void codeCreate(GenInfo genInfo) {
        System.out.println("-------开始生成代码-------");
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setFileOverride(genInfo.getOverrideExistFile());
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        //输入路径
        gc.setOutputDir(genInfo.getProjectPath());
        //作者
        gc.setAuthor(genInfo.getAuthor());
        gc.setOpen(false);
        gc.setDateType(DateType.ONLY_DATE);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(genInfo.getUrl());
        dsc.setDriverName(genInfo.getDriverName());
        dsc.setUsername(genInfo.getUserName());
        dsc.setPassword(genInfo.getPassword());
        mpg.setDataSource(dsc);

        //自定义配置,必须配置,不然报错
        mpg.setCfg(new InjectionConfig() {
            @Override
            public void initMap() {
                //空的
            }
        });

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);

        pc.setController(genInfo.getGenController() ? genInfo.getControllerPackageName() : "TTT");
        pc.setService(genInfo.getGenService() ? genInfo.getServicePackageName() : "TTT");
        pc.setServiceImpl(genInfo.getGenService() ? genInfo.getServiceImplPackageName() : "TTT");

        //配置dao包名
        pc.setEntity(genInfo.getEntityPackageName());
        pc.setMapper(genInfo.getMapperPackageName());
        pc.setXml(genInfo.getXmlPackageName());
        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass("com.dwise.workeasy.controller.BaseController");
        strategy.setSuperEntityClass(Model.class);
        // 此处可以修改为您的表前缀
        strategy.setTablePrefix(new String[]{genInfo.getTablePrefix()});
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.no_change);
        strategy.setEntityTableFieldAnnotationEnable(true);

        showTables(dsc, genInfo.getDbName());

        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

        destory(gc);

        System.out.println("");
        System.out.println("-------代码生成完成-------");
    }


    /**
     * 删除不必要的代码
     */
    public static void destory(GlobalConfig globalConfig) {
        String outputDir = globalConfig.getOutputDir() + "/TTT";
        UtilCollection.deleteDir(new File(outputDir));
    }

    /**
     * 显示所有表名,这里查询了注释
     *
     * @param dsc    数据源配置
     * @param dbName 数据库名
     */
    public static void showTables(DataSourceConfig dsc, String dbName) {
        try {
            ResultSet resultSet = dsc.getConn().createStatement().executeQuery("select TABLE_NAME as tableName,TABLE_COMMENT as tableComment from information_schema.`TABLES` where TABLE_SCHEMA = '" + dbName + "'");
            while (resultSet.next()) {
                String tableName = resultSet.getString(1);
                System.err.println(tableName);
            }
            System.err.println("---------------------------");
            System.err.println("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

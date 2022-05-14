package com.dwise.workeasy.codegen.mybatisplus.generator;

import com.dwise.workeasy.codegen.mybatisplus.generator.model.GenInfo;

/**
 * 生成代码示例
 *
 * @author: lvjingran
 */
public class CodeCreate {

    public static void main(String[] args) {
        GenInfo genInfo = GenInfo.build();

        //1、设置数据库连接
        String url = "jdbc:mysql://localhost:3306/time_hx_1223?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true";
        String driverName = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "123456ok**";
        String dbName = "time_hx_1223";
        genInfo.setUrl(url);
        genInfo.setDriverName(driverName);
        genInfo.setUserName(userName);
        genInfo.setPassword(password);
        genInfo.setDbName(dbName);



        //2、生成代码 数据库连接层代码
        genInfo.setAuthor("liyuchun")
                //设置模块路径 F:\idea_workplace\bishe
                .setProjectPath("D:\\Dwise\\cloud\\stockService")
                //设置controller包名
                .setControllerPackageName("com.lyc.test.controller")
                //设置service包名
                .setServicePackageName("com.lyc.test.service")
                //设置dao包名
                .setEntityPackageName("com.lyc.test.model")
                .setMapperPackageName("com.lyc.test.mapper")
                .setXmlPackageName("")
                //设置是否生成controller
                .setGenController(false)
                //设置是否生成service
                .setGenService(false)
                //设置是否覆盖已存在文件
                .setOverrideExistFile(true)
                //生成
                .over();
    }

}

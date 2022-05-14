package com.dwise.workeasy.codegen.mbg.plugin;

import com.dwise.workeasy.codegen.mbg.model.CodegenVO;
import com.dwise.workeasy.codegen.util.Util;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class CodegenPlugin extends PluginAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CodegenPlugin.class);

    private String serviceTargetProject;

    private String serviceTargetPackage;

    private String controllerTargetProject;

    private String controllerTargetPackage;

    private String javaModelName;


    public CodegenPlugin() { }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        this.serviceTargetProject = super.properties.getProperty("serviceTargetProject","");
        this.serviceTargetPackage = super.properties.getProperty("serviceTargetPackage", "");
        this.controllerTargetProject = super.properties.getProperty("controllerTargetProject", "");
        this.controllerTargetPackage = super.properties.getProperty("controllerTargetPackage", "");
    }


    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void initialized(IntrospectedTable table) {
        this.javaModelName = Util.getJavaModelName(table.getAliasedFullyQualifiedTableNameAtRuntime());
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        CodegenVO vo = new CodegenVO();

        addControllerVariable(vo);

        addModelVariable(vo);

        addServiceVariable(vo);

        addMapperVariable(vo);

        generate(vo);

        return null;
    }

    private void addMapperVariable(CodegenVO vo) {
        StringBuilder sb = new StringBuilder();
        String mapperPackage = super.getContext().getJavaClientGeneratorConfiguration().getTargetPackage();
        sb.append(mapperPackage).append(".").append(javaModelName).append("Mapper");
        vo.setMapperReference(sb.toString());
        sb.setLength(0);

        sb.append(javaModelName).append("Mapper");
        vo.setMapperName(sb.toString());

        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        vo.setMapperVarName(sb.toString());
    }

    private void addServiceVariable(CodegenVO vo) {
        vo.setServicePackage(serviceTargetPackage);

        StringBuilder sb = new StringBuilder();
        sb.append(serviceTargetPackage).append(".").append(javaModelName).append("Service");
        vo.setServiceReference(sb.toString());
        sb.setLength(0);

        sb.append(javaModelName).append("Service");
        vo.setServiceName(sb.toString());
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        vo.setServiceVarName(sb.toString());

        sb.append("Impl");
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        vo.setServiceImplName(sb.toString());

        sb.setLength(0);
        sb.append(serviceTargetPackage).append(".").append("impl");
        vo.setServiceImplPackage(sb.toString());
    }

    private void addModelVariable(CodegenVO vo) {
        vo.setModelName(javaModelName);

        StringBuilder sb = new StringBuilder();
        sb.append(javaModelName);
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        vo.setModelVarName(sb.toString());
        sb.append("s");
        vo.setModelVarsName(sb.toString());

        String targetPackage = super.getContext().getJavaModelGeneratorConfiguration().getTargetPackage();
        sb.setLength(0);
        sb.append(targetPackage).append(".").append(javaModelName);
        vo.setModelReference(sb.toString());
    }

    private void addControllerVariable(CodegenVO vo) {
        StringBuilder sb = new StringBuilder();
        sb.append(javaModelName).append("Controller");
        vo.setControllerName(sb.toString());


        vo.setControllerPackage(controllerTargetPackage);
    }

    private void generate(CodegenVO vo){
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "templates");
//        cfg.setDirectoryForTemplateLoading(new File("/where/you/store/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        generate(cfg, vo, "controller.ftl", getControllerFilePath());

        generate(cfg, vo, "service.ftl", getServiceFilePath());

        generate(cfg, vo, "service-impl.ftl", getServiceImplFilePath());


    }

    private void generate(Configuration cfg, CodegenVO vo,String ftlPath,String outputFilePath) {
        File file = new File(outputFilePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            if (logger.isWarnEnabled()) {
                logger.warn("filePath:{} is not exist!", parentFile.getAbsolutePath());
            }
            return;
        }
        Writer out = null;
        try {
            file.createNewFile();
            Template template = cfg.getTemplate(ftlPath);

            if (!file.canWrite()) {
                throw new RuntimeException("文件不可写:" + file.getPath());
            }
            out = new FileWriter(file);

            template.process(vo,out);

        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(),e);
            }
        }finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    private String getServiceFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(serviceTargetProject);
        if (!serviceTargetProject.endsWith(File.separator)) {
            sb.append(File.separator);
        }
        String packagePath = serviceTargetPackage.replace(".", File.separator);
        sb.append(packagePath);
        if (!packagePath.endsWith(File.separator)) {
            sb.append(File.separator);
        }
        sb.append(javaModelName).append("Service").append(".java");

        return sb.toString();
    }

    private String getServiceImplFilePath() {
        StringBuilder sb = new StringBuilder();

        sb.append(serviceTargetProject);
        if (!serviceTargetProject.endsWith(File.separator)) {
            sb.append(File.separator);
        }
        String packagePath = serviceTargetPackage.replace(".", File.separator);
        sb.append(packagePath);
        if (!packagePath.endsWith(File.separator)) {
            sb.append(File.separator);
        }
        sb.append("impl").append(File.separator);
        sb.append(javaModelName).append("Service").append("Impl").append(".java");

        return sb.toString();
    }



    private String getControllerFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(controllerTargetProject);
        if (!controllerTargetProject.endsWith(File.separator)) {
            sb.append(File.separator);
        }
        String packagePath = controllerTargetPackage.replace(".", File.separator);
        sb.append(packagePath);
        if (!packagePath.endsWith(File.separator)) {
            sb.append(File.separator);
        }
        sb.append(javaModelName).append("Controller").append(".java");
        return sb.toString();
    }

}

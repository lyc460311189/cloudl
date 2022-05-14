package com.dwise.workeasy.codegen.mbg.model;

import java.io.Serializable;

public class CodegenVO implements Serializable {

    private String controllerPackage;

    private String controllerName;

    private String modelReference;

    private String modelName;

    private String modelVarName;

    private String modelVarsName;

    private String servicePackage;

    private String serviceReference;

    private String serviceName;

    private String serviceVarName;

    private String serviceImplName;

    private String serviceImplPackage;

    private String mapperReference;

    private String mapperName;

    private String mapperVarName;

    public String getServiceImplPackage() {
        return serviceImplPackage;
    }

    public void setServiceImplPackage(String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getModelReference() {
        return modelReference;
    }

    public void setModelReference(String modelReference) {
        this.modelReference = modelReference;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelVarName() {
        return modelVarName;
    }

    public void setModelVarName(String modelVarName) {
        this.modelVarName = modelVarName;
    }

    public String getModelVarsName() {
        return modelVarsName;
    }

    public void setModelVarsName(String modelVarsName) {
        this.modelVarsName = modelVarsName;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getServiceReference() {
        return serviceReference;
    }

    public void setServiceReference(String serviceReference) {
        this.serviceReference = serviceReference;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceVarName() {
        return serviceVarName;
    }

    public void setServiceVarName(String serviceVarName) {
        this.serviceVarName = serviceVarName;
    }

    public String getMapperReference() {
        return mapperReference;
    }

    public void setMapperReference(String mapperReference) {
        this.mapperReference = mapperReference;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getMapperVarName() {
        return mapperVarName;
    }

    public void setMapperVarName(String mapperVarName) {
        this.mapperVarName = mapperVarName;
    }
}

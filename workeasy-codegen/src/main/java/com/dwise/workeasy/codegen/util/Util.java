package com.dwise.workeasy.codegen.util;

public class Util {

    public static String getJavaModelName(String tableName) {
        if (tableName == null || "".equals(tableName)) {
            throw new RuntimeException("tableName is empty!");
        }
        tableName = tableName.toLowerCase();
        StringBuilder modelName = new StringBuilder(tableName);

        modelName.setCharAt(0, Character.toUpperCase(modelName.charAt(0)));

        while (true) {
            int index = modelName.indexOf("_");
            if (index == -1 || index == modelName.length() - 1) {
                break;
            }
            modelName.deleteCharAt(index);
            modelName.setCharAt(index, Character.toUpperCase(modelName.charAt(index)));
        }
        return modelName.toString();
    }
}

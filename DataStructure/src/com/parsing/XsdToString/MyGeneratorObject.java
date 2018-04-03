package com.parsing.XsdToString;

import java.util.HashMap;
import java.util.Map;

public class MyGeneratorObject {
    private static String dataClassName;
    private static String fromPart;
    private static String fromType;
    private static String path;
    private static String packageName;
    private static Map<String, String> complexElement = new HashMap<String, String>();

    /**
     * @param dataClassName     - String    название класса в котором теперь Enum
     * @param fromPart          - String    название базового элемента для которого пойдёт создание
     * @param fromType          - String    тип базового элемента для которого пойдёт создание
     * @param path              - String    путь до места
     * @param packageName       - String    название пакета
     * @param complexElement    - Map\String, String\   вспомогательное, все массивы и сложные типы.
     * */    
    public MyGeneratorObject(String dataClassName, String fromPart, String fromType, String path, String packageName, Map<String, String> complexElement){
        this.dataClassName = dataClassName;
        this.fromPart = fromPart;
        this.fromType = fromType;
        this.path = path;
        this.packageName = packageName;
        this.complexElement = complexElement;
    }

    public String getDataClassName() {
        return dataClassName;
    }

    public String getFromPart() {
        return fromPart;
    }
    
    public static String getPath() {
        return path;
    }

        public static String getPackageName() {
            return packageName;
        }

    public Map<String, String> getComplexElement() {
        return complexElement;
    }

    public static void setFromPart(String fromPart) {
        MyGeneratorObject.fromPart = fromPart;
    }

    public static String getFromType() {
        return fromType;
    }

    public static void setFromType(String fromType) {
        MyGeneratorObject.fromType = fromType;
    }
}

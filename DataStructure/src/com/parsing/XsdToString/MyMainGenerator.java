package com.parsing.XsdToString;

public class MyMainGenerator {
    private static MyEnumGenerator enumGenerator = new MyEnumGenerator();
    private static MyClientGenerator clientGenerator;
    
    public MyMainGenerator() {
        super();
    }
    
    public static void main(String[] args) {
        String path = "C:\\Users\\vassina\\Desktop\\project\\AOCOnlinePShEP\\AOCPShEpWebService\\src\\beans\\dataTypes\\newDataTypes_2";
        String packageName = "beans.dataTypes.newDataTypes_2";
        MyGeneratorObject genObj = enumGenerator.generateDataStructure(path, packageName);
        if (genObj != null)
            clientGenerator = new MyClientGenerator(genObj);
        clientGenerator.setFromPart("CheckResponseType");
        clientGenerator.setFromType("CheckResponse");
        clientGenerator.generateClass();
        
    }
}

package com.parsing.XsdToString;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.charset.Charset;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyEnumGenerator {
    private static boolean firstCycleDone = false;
    private static Map<String, String> rootElem = new HashMap<String, String>();
    
    public MyEnumGenerator() {
        super();
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\vassina\\Desktop\\project\\AOCOnlinePShEP\\AOCPShEpWebService\\src\\beans\\dataTypes\\newDataTypes_2";
        String packageName = "beans.dataTypes.newDataTypes_2"; // newDataTypes_2  newTestData_1
        generateDataStructure(path, packageName);
    }
    
    /** Function of generating EnumClasses
     * 
     * @param path          path to need Directory including Xml generated types
     * @rapam packageName   packageName for new file of Enum Data
     * 
     * */
    public static MyGeneratorObject generateDataStructure(String path, String packageName){

        File file = new File(path);
        System.out.println("Start generate class for Directory\"" + file.getAbsolutePath() + "\"");
        String classData = "";
        boolean isJavaGood = false;
        if (!file.isDirectory()) {System.out.println("Wrong path, it's not a Directory, generating process stopped."); return null; }
        for (File f : file.listFiles())
            if (f.getName().contains(".java")) isJavaGood = true;
        if (!isJavaGood) {System.out.println("There not found .java files, generating process stopped."); return null; }
        for (File f : file.listFiles())
            if (!f.isDirectory()    && f.getName().contains(".java") 
                                    && !f.getName().contains("package-info")
                                    && !f.getName().contains("ObjectFactory")
                                    && !f.getName().contains(file.getName())){
                try {
                    parseClass(f.getName(), readFile(f.getAbsolutePath(), StandardCharsets.UTF_8));
                } catch (IOException e) {
                    System.out.println("//Error reading file:\n" + "// f.getName()\n");
                }
            }
        firstCycleDone = true;
        for (File f : file.listFiles())
            if (!f.isDirectory()    && f.getName().contains(".java") 
                                    && !f.getName().contains("package-info")
                                    && !f.getName().contains("ObjectFactory") 
                                    && !f.getName().contains(file.getName())){
                try {
                    //System.out.println(f.getName());
                    classData += parseClass(f.getName(), readFile(f.getAbsolutePath(), StandardCharsets.UTF_8));
                } catch (IOException e) {
                    System.out.println("//Error reading file:\n" + "// f.getName()\n");
                } catch (Exception e) {
                    System.out.println("//Error reading file:\n" + "// f.getName()\n");
                }
            }
        /*System.out.println("    arraysSet:"); 
        for (Map.Entry entry: arraysSet.entrySet())
            System.out.println(entry.getKey() + " " + entry.getValue());*/
        
        complexElement = sortByValue(complexElement);
        /*System.out.println("    complexElement:");
        for (Map.Entry entry: complexElement.entrySet())
            System.out.println(entry.getKey());*/
        
        for (Map.Entry entry: complexElement.entrySet())
            rootElem.put(entry.getKey().toString().split(" ")[0], entry.getValue().toString().split(" ")[0]);
        
        String newClass = getHeadFile(packageName, file);
        newClass += classData;
        newClass += getEndFile();
        try {
            File newFile = new File(file.getAbsolutePath() +"\\" + file.getName() + ".java");
            FileWriter fileWriter = new FileWriter(newFile);
            fileWriter.write(newClass);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Generation file \"" + file.getAbsolutePath() +"\\" + file.getName() + ".java"+ "\"\n was success...");
        
       MyGeneratorObject genObject = new MyGeneratorObject(file.getName(), null, null, path, packageName, complexElement);
       System.out.println("You can choose one of this rootComplex element for further client generation");
       for (Map.Entry entry: rootElem.entrySet())
           System.out.print("'" + entry.getKey() + "', ");
       return genObject;
    }
    
    private static String getHeadFile(String packageName, File directory){
        String headFile =   "package " + packageName + ";\n\n";
        for (Map.Entry entry: extentions.entrySet())
            headFile += entry.getValue();
        headFile +=         "\n" +
                            "/**\n" +
                            " * This code generated from .java files in directory\n" +
                            " * " + directory.getAbsolutePath() + "\n" +
                            " * ------------------------------------------------------\n";
        for (File f : directory.listFiles())
            if (!f.isDirectory()    && f.getName().contains(".java") 
                                    && !f.getName().contains("package-info")
                                    && !f.getName().contains("ObjectFactory"))
                headFile += " *    " + f.getName() + "\n";                    
        headFile +=         " * \n" +
                            " * Generated from 'MyEnumGenerator'\n" +
                            " * created by Vassin Andrey 2017 (c)\n" +
                            " */\n" +
                            "public class " + directory.getName() + " {\n" +
                            "    private static final String[] complexElement = {\n"+
        generateComplexElemenetList() +
                            "\n";
        return headFile;
    }
    private static String getEndFile(){
        String endFile = "";
        for (Map.Entry entry: extentionFunc.entrySet())
            endFile += entry.getValue();
        endFile += "\n}";
        return endFile;
    }
    
    private static String readFile(String path, Charset encoding) throws IOException {
      byte[] encoded = Files.readAllBytes(Paths.get(path));
      return new String(encoded, encoding);
    }
     
    private static String rootElement;
    private static Map<String, String> propertiesMap;
    private static List<MyField> fields;
    private static Map<String, String> extentions = new HashMap<String, String>();
    private static Map<String, String> extentionFunc = new HashMap<String, String>();
    private static Map<String, String> arraysSet = new HashMap<String, String>();
    private static Map<String, String> complexElement = new HashMap<String, String>();
    private static String parseClass(String fileName, String data) {
        try {
            int point = find(data, "public class");  
            if (point == -1) return "Error in definitly structure of that file not Found 'public class'."; 
                //point = find(data, "public static class");  
            propertiesMap = new HashMap<String, String>();
            fields = new ArrayList<MyField>();
            rootElement = null;
            
            //if (firstCycleDone) System.out.println("    " + fileName + ": " + point);            
            //if (firstCycleDone) if (fileName.contains("Address")) System.out.println("    " + fileName + ": \n" + data);
        
            String XmlFulPart = data.substring(0, point);
            String classPart = data.substring(point);
        
            setGlobalXMLParam(data);
            setFields(classPart);
        
            if (XmlFulPart.contains("@XmlType")) rootElement = propertiesMap.get("XmlType");
            if (rootElement == null) if (XmlFulPart.contains("@XmlRootElement")) rootElement = propertiesMap.get("XmlRootElement");
            if (rootElement == null) {
                String str = data.substring(point, point + find(data.substring(point), "\n"));
                str = str.substring(13, find(str, " {"));
                rootElement = str;
            }
            //if (firstCycleDone) System.out.println("root: " + rootElement);
            return createEnumObject(fileName);
        } catch (Exception e){
            e.printStackTrace();
            return "//Some error in definitly structure of that file\n//" + fileName + "\n";
        }
    }
    
    private static void setGlobalXMLParam(String data) throws Exception {
        try {
        //System.out.println(data);
        int point = find(data, "public class");
        if (point == -1) point = find(data, "public static class");  
        int pointXml1 = find(data, "@XmlAccessorType");     // @XmlAccessorType
        int pointXml2 = find(data, "@XmlType");             // @XmlType
        int pointXml3 = find(data, "@XmlRootElement");      // @XmlRootElement
        //System.out.println(pointXml1 +" "+ pointXml2 + " " + pointXml3);
        if (pointXml3 == -1) pointXml3 = point;
        if (pointXml2 == -1) pointXml2 = pointXml3;
        if (pointXml1 == -1) pointXml1 = pointXml2;
        
        if (pointXml1 >  pointXml2 || pointXml2 > pointXml3 || pointXml1 >  pointXml3)  throw new Exception("Wrong type XML data.");
        
        //System.out.println("  " + pointXml1 +" "+ pointXml2 + " " + pointXml3);
        String XmlAccessorType = data.substring(pointXml1, pointXml2);
        String XmlType = data.substring(pointXml2, pointXml3);
        String XmlRootElement = data.substring(pointXml3, point);
                                     
        {
            MyProperty tempProp = new MyProperty(null, null);    
            tempProp = getProperty(XmlAccessorType, "name");
            if (tempProp.getKey() != null) propertiesMap.put("XmlAccessorType", tempProp.getValue());
        }
        {
            MyProperty tempProp;    
            tempProp = getProperty(XmlType, "name");
            if (tempProp.getKey() != null) propertiesMap.put("XmlType", tempProp.getValue());
        }
        {
            MyProperty tempProp;    
            tempProp = getProperty(XmlRootElement, "name");
            if (tempProp.getKey() != null) propertiesMap.put("XmlRootElement", tempProp.getValue());
        }
        } catch (Exception e){
            e.printStackTrace();
            //System.out.println(data);
            throw new Exception();
        }
    }
    private static void setFields(String classPart) throws Exception {
        String localClassPart = classPart;
        try {            
        while (localClassPart.contains("protected ")){
            int nextWrite = find(localClassPart, ";") + 1;
            int firstElemXml = find(localClassPart, "@XmlElement");
            int firstElemPr = find(localClassPart, "protected ");
            int firstElem = firstElemPr;
            if (nextWrite == -1) nextWrite = find(localClassPart, "/**");
            
            //System.out.println("     ------>>>Xml " + firstElemXml + " : Pr " + firstElemPr + " : ");
            boolean isXmlElem = false;
            if (firstElemPr == -1) return;
            if (firstElemXml != -1) if (firstElemXml < firstElemPr && firstElemPr < nextWrite){
                String val = localClassPart.substring(firstElemXml, nextWrite);
                if (getProperty(val, "name").getValue() != null) isXmlElem = true;
            }
            if (isXmlElem) firstElem = firstElemXml;
            
            //System.out.println("     ------>>>" + firstElem + " : " + nextWrite);
            String val = localClassPart.substring(firstElem, nextWrite);
            //System.out.println("val : " + val);
            String typeVal = "";
            String moreFieldName = "";
            
            if (val.contains("@XmlSchemaType")) 
                typeVal = getProperty(val.substring(find(val, "@XmlSchemaType")), "name").getValue();
            else 
                typeVal = delSpace(val.substring(find(val, "protected ")).split(" ")[1]);
            
            if (isXmlElem) {
                moreFieldName = getProperty(val.substring(0, find(val, "protected ")), "name").getValue();
                //System.out.println(" <val>           " + moreFieldName);
                //System.out.println(" <XML>        " + val.split("\n")[0]);
                //System.out.println(" <XML2>       " + val.substring(0, find(val, "protected ")));
            }
            else {
                moreFieldName = delMetaSymbol(delSpace(val.split(" ")[2]));
                moreFieldName = moreFieldName.substring(0, 1).toUpperCase() + moreFieldName.substring(1);
            }
           
            //System.out.println("result:         " + moreFieldName + " " + typeVal);
            fields.add(new MyField(moreFieldName, null, typeVal));
            //fields.add(new MyField((getProperty(val, "name").getValue() != null ? getProperty(val, "name").getValue() : moreFieldName), null, typeVal));
            localClassPart = localClassPart.substring(nextWrite);
        }
        } catch (Exception e){
            e.printStackTrace();
            //System.out.println(localClassPart);
            throw new Exception();
        }
    }
  
    /** get out property of type
     *      "@XmlRootElement(name = "CheckRequest")"
     *      "****************@paramName = \"ParamValue\"*"
     *      "@XmlType(name = "FIO", propOrder = { "surName", "name", "middleName" })"
     *      "^.*("+propertyName+")\\s=\\s(\"([a-zA-Z_]\\w*)\"|{\\s(\"([a-zA-Z_]\\w*),?\")*\\s})"
     *          .*(\\w*)\\s=\"\\s(\\w*)\"
     * Xml-teg   "^<([a-z]+)([^>]+)*(?:>(.*)<\\/\\1>|\\s+\\/>)$"
     * */
    private static MyProperty getProperty(String str, String propertyName){
        MyProperty prop = new MyProperty(null, null);
        String Name="";
        List<String> Arguments = new ArrayList<String>();
        //System.out.println(" <Str>" + str);
        {
            Pattern CompleteFunction = Pattern.compile("^.*("+propertyName+")\\s=\\s\"([a-zA-Z_]\\w*)\".*");
            Pattern Parameter = Pattern.compile("[a-zA-Z_]\\w*");
            Matcher CFMatcher = CompleteFunction.matcher(str);
            if (CFMatcher.find()) {
                Name = CFMatcher.group(1);
                Matcher PMatcher = Parameter.matcher(CFMatcher.group(1));
                while (PMatcher.find()) {
                    Arguments.add(PMatcher.group());
                }
                PMatcher = Parameter.matcher(CFMatcher.group(2));
                while (PMatcher.find()) {
                    Arguments.add(PMatcher.group());
                }
            }
        }
        // если нет просто элемента, тогда будем искать последовательность
        if (Arguments.size() == 0){
            Pattern CompleteFunction = Pattern.compile("^.*("+propertyName+")\\s=\\s[\"|\\{]+([a-zA-Z_]\\w*)[\"|\\}]+"); // "^.*("+propertyName+")\\s=\\s\"([a-zA-Z_]\\w*)\""
            Pattern Parameter = Pattern.compile("[a-zA-Z_]\\w*");
            Matcher CFMatcher = CompleteFunction.matcher(str);
            if (CFMatcher.find()) {
                    Name = CFMatcher.group(1);
                    Matcher PMatcher = Parameter.matcher(CFMatcher.group(0));
                    while (PMatcher.find()) {
                            Arguments.add(PMatcher.group());
                    }
            }
            
        }
        // если нет ещё и последовательности, тогда попробуем найти тип name.type
        if (Arguments.size() == 0){
            Pattern CompleteFunction = Pattern.compile("^.*\\(([a-zA-Z_]\\w*)[.]{1}([a-zA-Z_]\\w*)\\)"); // "^.*("+propertyName+")\\s=\\s\"([a-zA-Z_]\\w*)\""
            Pattern Parameter = Pattern.compile("[a-zA-Z_]\\w*");
            Matcher CFMatcher = CompleteFunction.matcher(str);
            if (CFMatcher.find()) {
                    Name = CFMatcher.group(1);
                    Matcher PMatcher = Parameter.matcher(CFMatcher.group(1));
                    while (PMatcher.find()) {
                            Arguments.add(PMatcher.group());
                    }
                    PMatcher = Parameter.matcher(CFMatcher.group(2));
                    while (PMatcher.find()) {
                            Arguments.add(PMatcher.group());
                    }
            }
        }        
        if (Arguments.size() >= 2){
            prop = new MyProperty(Arguments.get(0).toString(), Arguments.get(1).toString());
            //System.out.println("prop: " + prop.getKey() + " " + prop.getValue());
        }
        return prop; 
    }
    
    private static class MyProperty{
        private String key;
        private String value;
        
        public MyProperty(String key, String value){
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
    private static class MyField{
        private String name;
        private Object value;
        private String type;
        
        public MyField(String name){
            this(name, null);
        }
        public MyField(String name, Object value){
            this(name, value, null);
        }
        public MyField(String name, Object value, String type){
            this.name = name;
            this.value = value;
            this.type = type;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
    
    private static String createEnumObject(String fileName) throws Exception {
        String object = "";
        
        if (rootElement != null && fields.size() > 0){
            object += generateNotation(fileName);
            object += generateStruct();
        }
        else object = "//Not initiate object or there no elements";
        
        return object;
    }
    private static String generateNotation(String fileName){
        String notation =   "\n" +
                            "/**\n" +
                            " * This code generated from .java file\n" +
                            " * " + fileName + "\n" +
                            " * ------------------------------------------------------\n" +
                            " * <p>Enum class for complex @Xml type.\n" + 
                            " * \n";
        for (Map.Entry val: propertiesMap.entrySet())
            notation += " * @" + val.getKey() + "(name = \"" + val.getValue() + "\")\n";          
        for (MyField mf : fields)
            notation += " * @rapam\t" + mf.getName() + "\t\ttypeOf(" + mf.getType() + ")\n";          
        notation +=         " * \n" +
                            " * Generated from 'MyEnumGenerator'\n" +
                            " */\n";
        
        return notation;
    }
    private static String generateStruct() throws Exception {
        String struct =
        "    public enum " + rootElement + "Data {\n" +
        generateFields() +
        "\n" +
        "        private boolean isSimple;\n" +
        "        private boolean isArray;\n"+
        "\n" +
        "        private " + rootElement + "Data() {\n" +
        "            this.isSimple = true;\n" +
        "            this.isArray = false;\n" +
        "        }\n" +
        "        private " + rootElement + "Data(boolean isSimple) {\n" +
        "            this(isSimple, false);\n" +
        "        }\n" +
        "        private " + rootElement + "Data(boolean isSimple, boolean isArray) {\n" +
        "            this.isSimple = isSimple;\n" +
        "            this.isArray = isArray;\n" +
        "        }\n" +
        "\n" +
        "        public abstract Object get(" + rootElement + " obj);\n" +
        "\n" +
        "        public abstract " + rootElement + " set(" + rootElement + " obj, Object value);\n" +
        "\n" +
        "        public boolean isIsSimple() {\n" +
        "            return isSimple;\n" +
        "        }\n" +
        "        public boolean isIsArray() {\n" +
        "            return isArray;\n" +
        "        }\n" +
        "    }\n\n";
        return struct;
    }
    private static String generateComplexElemenetList(){
        String str = "";
        for (Map.Entry entry : complexElement.entrySet()){
            str += "        \"" + entry.getKey() + "\",\n";
            //System.out.println("-" + entry.getKey());
        }
        return (str.length() > 0 ? str.substring(0, str.length() - 2) + "};\n" : str);
    }
    private static String generateFields() throws Exception {
        String fieldsCode = "";
        for (MyField mf : fields){
            //if (isComplextype(mf.getType()) && mf.getType().equals("Object")) mf.setType("List<Object>");
            if (firstCycleDone) System.out.println("  ---> " + mf.getName() + " " + mf.getType() + ", comlex: " + isComplextype(mf.getType()));
            if (isComplextype(mf.getType()) && mf.getType().contains("List<")) arraysSet.put(rootElement + " " + mf.getType(), mf.getType());
            if (isComplextype(mf.getType()) && firstCycleDone) {
                //System.out.println("\"        " + rootElement + " " + mf.getName() + " " + mf.getType() + ((mf.getType().contains("List<")) ? " true" : " false"));
                complexElement.put(rootElement + " " + mf.getName() + " " + mf.getType() + ((mf.getType().contains("List<")) ? " true" : " false"),
                                   rootElement + " " + mf.getName() + " " + mf.getType() + ((mf.getType().contains("List<")) ? " true" : " false")); // (arraysSet.containsValue(mf.getType())) ? " true" : " false")

                
            }
            fieldsCode += "        " + mf.getName() + (isComplextype(mf.getType()) ? (arraysSet.containsKey(mf.getName() + " " + mf.getType())) ? " (false, true)" : " (false)": "") + " {\n" +
                          generateAccessors(mf.getName(), rootElement, mf.getType()) +
                          "        },\n";
        }
        return fieldsCode.substring(0, fieldsCode.length() - 2) + ";\n";
    }
    private static String generateAccessors(String paramName, String fromPart, String valueType) throws Exception {
        if (!valueType.contains("List")) return createGetter(paramName, fromPart, valueType) + createSetter(paramName, fromPart, valueType);
        return createGetter(paramName, fromPart, valueType) + createListSetter(paramName, fromPart, valueType);
    }
    private static String createGetter(String paramName, String fromPart, String valueType) throws Exception {
        String geter = "";
        try {
            geter =     "            public Object get(" + fromPart + " obj) {\n" +
                        "                return obj." + (valueType.equals("boolean") ? "is" : "get") + paramName.substring(0, 1).toUpperCase() + paramName.substring(1, paramName.length()) + "();\n" +
                        "            }\n";
        } catch (Exception e){
            throw new Exception();
        }
        return geter;
    }
    private static String createSetter(String paramName, String fromPart, String valueType) throws Exception {
        String seter = "";
        try{
            seter =     "            public " + fromPart + " set(" + fromPart + " obj, Object value) {\n" +
                        "                obj.set" + paramName.substring(0, 1).toUpperCase() + paramName.substring(1, paramName.length()) + "(" + createSetValueType(valueType) + ");\n" +
                        "                return obj;\n" +
                        "            }\n";
        } catch (Exception e){
            throw new Exception();
        }
        return seter;
    }
    private static String createListSetter(String paramName, String fromPart, String valueType) throws Exception {
        String seter = "";
        try {
            seter =     "            public " + fromPart + " set(" + fromPart + " obj, Object value) {\n" +
                        "                obj.get" + paramName + "().add((" + valueType.substring(5, valueType.length() - 1) + ")value);\n" +
                        "                return obj;\n" +
                        "            }\n";
        } catch (Exception e){
            throw new Exception();
        }
        return seter;
    }
    
    private static String createSetValueType(String valueType){
        if (valueType.toLowerCase().equals("string")) return "String.valueOf(value)";
        if (valueType.toLowerCase().equals("bigdecimal")){
            extentions.put("BigDecimal", "import java.math.BigDecimal;\n");
            return "(" + valueType + ")value";
        }
        if (valueType.toLowerCase().equals("double")) return "Double.valueOf(String.valueOf(value))";
        if (valueType.toLowerCase().equals("date") || 
            valueType.toLowerCase().equals("xmlgregoriancalendar") ||
            valueType.toLowerCase().equals("datetime")){
            extentions.put("XMLGregorianCalendar", "import javax.xml.datatype.XMLGregorianCalendar;\n");
            extentions.put("DatatypeConfigurationException", "import javax.xml.datatype.DatatypeConfigurationException;\n");
            extentions.put("DatatypeFactory", "import javax.xml.datatype.DatatypeFactory;\n");
            extentions.put("ParseException", "import java.text.ParseException;\n");
            extentions.put("SimpleDateFormat", " import java.text.SimpleDateFormat;\n");
            extentions.put("Calendar", "import java.util.Calendar;\n");
            extentions.put("Date", "import java.util.Date;\n");
            extentionFunc.put("b", ""+
            "    public static XMLGregorianCalendar b(String value) {\n" + 
            "        Date d = null; //dd.MM.yyyy\n" + 
            "        try {\n" + 
            "            d = new SimpleDateFormat(\"yyyy-MM-dd\").parse(value.toString());\n" + 
            "        } catch (ParseException e) {\n" + 
            "           {}\n"+
            "        }\n" + 
            "        Calendar c = Calendar.getInstance();\n" + 
            "        c.setTime(d);\n" + 
            "        XMLGregorianCalendar x = null;\n" + 
            "        try {\n" + 
            "            x = DatatypeFactory.newInstance().newXMLGregorianCalendar();\n" + 
            "            x.setDay(c.get(Calendar.DAY_OF_MONTH));\n" + 
            "            x.setMonth(c.get(Calendar.MONTH) + 1);\n" + 
            "            x.setYear(c.get(Calendar.YEAR));\n" + 
            "        } catch (DatatypeConfigurationException e) {\n" + 
            "            e.printStackTrace();\n" + 
            "        }\n" + 
            "        return x;\n" + 
            "    }\n");
            return "(XMLGregorianCalendar)b(value.toString())";
        }
        if (valueType.toLowerCase().equals("boolean")) return "Boolean.valueOf(value.toString())";
        return "(" + valueType + ")value";
    }
    private static boolean isComplextype(String valueType){
        if (valueType.toLowerCase().equals("string")) return false;
        if (valueType.toLowerCase().equals("date")) return false;
        if (valueType.toLowerCase().equals("xmlgregoriancalendar")) return false;
        if (valueType.toLowerCase().equals("datetime")) return false;
        if (valueType.toLowerCase().equals("boolean")) return false;
        if (valueType.toLowerCase().equals("int")) return false;
        if (valueType.toLowerCase().equals("integer")) return false;
        if (valueType.toLowerCase().equals("double")) return false;
        if (valueType.toLowerCase().equals("bigdecimal")) return false;
        if (valueType.toLowerCase().equals("object")) return false;
        return true;
    }
    
     /** Find iterator searching StringSequence in String
     * 
     * @param str           the line in which you want to search
     * @param searchElem    interesting you stringSequence
     * @return Integer      numIterator of finded element, return '-1'
     *                        if element not found.
     * */
    private static int find (String str, String searchElem){
        boolean isFind = false;
        for (int i = 0; i < str.length() - searchElem.length() + 1; i++){
            if (str.substring(i, i + searchElem.length()).equals(searchElem)) isFind = true;
            if (isFind) return i;
        }        
        return -1;
    } 
    /** Find iterator searching StringSequence in String
     * 
     * @param str           the line in which you want to search
     * @param searchElem    interesting you stringSequence
     * @param count         count of the entry for the element to be found
     * @return Integer      numIterator of finded element, return '-1'
     *                        if element not found.
     * */
    private static int find (String str, String searchElem, int count){
        int count_ = 0;
        int increase = 0;
        int val = -1;
        for (int i = 0; i < count; i++){
            val = find (str, searchElem);
            if (val == -1) return -1;
            count_++;
            str = str.substring(val + searchElem.length());
            //increase += searchElem.length();
        }     
        increase = searchElem.length() * (count_ - 1);
        return val + increase;
    }   
    private static String delSpace(String string){
        while (string.substring(0, 1).equals(" ") || string.substring(0, 1).equals("\n")) string = string.substring(1);
        return string;
    }
    private static String delMetaSymbol(String string){
        string = string.replaceAll("\n", "");
        string = string.replaceAll(";", "");
        return string;
    }
    private static int searchSymbol(String string, String searchSeq){
        int count = 0;
        while (string.contains(searchSeq)){
            count++;
            string = string.substring(find(string, searchSeq)+1);
            if (string.length() < 0) return count;
        }
        return count;
    }
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
           List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
           Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
               public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                   return (o1.getValue()).compareTo( o2.getValue() );
               }
           });

           Map<K, V> result = new LinkedHashMap<K, V>();
           for (Map.Entry<K, V> entry : list) {
               result.put(entry.getKey(), entry.getValue());
           }
           return result;
    }
}

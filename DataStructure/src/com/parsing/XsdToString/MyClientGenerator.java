package com.parsing.XsdToString;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyClientGenerator {
    private static String dataClassName;
    private static String fromPart;
    private static String fromType;
    private static String path;
    private static String packageName;
    private static Map<String, String> complexElement = new HashMap<String, String>();
    
    public MyClientGenerator(MyGeneratorObject genObject){
        this.dataClassName = genObject.getDataClassName();
        this.fromPart = genObject.getFromPart();
        this.path = genObject.getPath();
        this.packageName = genObject.getPackageName();
        this.complexElement = genObject.getComplexElement();
    }
    public MyClientGenerator(String dataClassName, String fromPart, String path, String packageName, Map<String, String> complexElement){
        this.dataClassName = dataClassName;
        this.fromPart = fromPart;
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
        MyClientGenerator.fromPart = fromPart;
    }

    public static String getFromType() {
        return fromType;
    }
    
    public static void setFromType(String fromType) {
        MyClientGenerator.fromType = fromType;
    }
    
    public static void main(String[] args) {
        complexElement.put("Addresses Address false", "Addresses Address false");
        complexElement.put("CheckRequest Statuses true", "CheckRequest Statuses true");
        complexElement.put("CheckRequest Founders true", "CheckRequest Founders true");
        complexElement.put("CheckRequest Persons true", "CheckRequest Persons true");
        complexElement.put("CheckRequest Organizations true", "CheckRequest Organizations true");
        complexElement.put("CheckRequest LeaderPersons true", "CheckRequest LeaderPersons true");
        complexElement.put("CheckRequest Addresses true", "CheckRequest Addresses true");
        complexElement.put("CheckResponse DataResponse false", "CheckResponse DataResponse false");
        complexElement.put("Founder Address false", "Founder Address false");
        complexElement.put("Founders Founder false", "Founders Founder false");
        complexElement.put("LeaderPerson FIO false", "LeaderPerson FIO false");
        complexElement.put("LeaderPerson Document false", "LeaderPerson Document false");
        complexElement.put("LeaderPersons LeaderPerson false", "LeaderPersons LeaderPerson false");
        complexElement.put("Organizations Organization false", "Organizations Organization false");
        complexElement.put("Person FIO false", "Person FIO false");
        complexElement.put("Person Document false", "Person Document false");
        complexElement.put("Person Address false", "Person Address false");
        complexElement.put("Persons Person false", "Persons Person false");
        complexElement.put("Statuses Status false", "Statuses Status false");
        
        //generateRootStruct("CheckDataTypes", "CheckRequest");
        //generateRootObjectStruct("CheckDataTypes", "CheckRequest");
        System.out.println(generateRootObjectStruct_2("CheckDataTypes", "CheckRequest", "CheckRequest"));
        //System.out.println(generateRootStruct("CheckDataTypes", "CheckRequest"));
    }
    
    // нужна переработка
    public static void generateClass(){
        if (path == null) {             System.out.println("Error generation: Path not found!"); return; }
        if (dataClassName == null) {    System.out.println("Error generation: DataClassName not found!"); return; }
        if (fromPart == null) {         System.out.println("Error generation: FromPart not found!"); return; }
        if (packageName == null) {      System.out.println("Error generation: packageName not found!"); return; }
        if (complexElement.size() == 0) {    System.out.println("Error generation: ComplexElement not found!"); return; }

        createUtils();
        
        File file = new File(path);
        System.out.println("\nStart generate class for Directory\"" + file.getAbsolutePath() + "\"");
        //String classData = "";
        boolean isJavaGood = false;
        if (!file.isDirectory()) {System.out.println("Wrong path, it's not a Directory, generating process stopped."); return; }
        for (File f : file.listFiles())
            if (f.getName().contains(".java")) isJavaGood = true;
        
        String newClass = getHeadFile(packageName, file);
        newClass += generateRootObjectStruct_2(dataClassName, fromPart, fromType);
        newClass += "\n\n";
        newClass += generateRootStruct(dataClassName, fromPart, fromType);
        newClass += getEndFile();
        
        try {
            File newFile = new File(file.getAbsolutePath() +"\\" + file.getName() + "Client.java");
            FileWriter fileWriter = new FileWriter(newFile);
            fileWriter.write(newClass);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(newClass);
        System.out.println("Generation file \"" + file.getAbsolutePath() +"\\" + file.getName() + "Client.java"+ "\"\n was success...");
    }

    private static void createUtils(){
        createMyNodeJava();
        createMyFieldJava();
        createMyNodeParser();
    }
    private static void createMyNodeJava(){
        if (path == null) return;
        String myNodeClass = "package " + packageName + ".utils;\n" + 
        "\n" + 
        "import java.util.ArrayList;\n" + 
        "import java.util.HashMap;\n" + 
        "import java.util.Iterator;\n" + 
        "import java.util.List;\n" + 
        "import java.util.Map;\n" + 
        "\n" + 
        "import org.w3c.dom.Element;\n" + 
        "import org.w3c.dom.Node;\n" + 
        "\n" + 
        "public class MyNode implements Iterable<MyNode> {\n" + 
        "        private String name;\n" + 
        "        private Object value;\n" + 
        "        private String currentString;\n" + 
        "        private List<MyNode> childs;\n" + 
        "        private int N = 0;\n" + 
        "        \n" + 
        "        public MyNode(String name) {\n" + 
        "            this(name, null);\n" + 
        "        }\n" + 
        "        public MyNode(String name, Object value) {\n" + 
        "            this(name, value, null);\n" + 
        "        }\n" + 
        "        public MyNode(String name, Object value, String string) {\n" + 
        "            this.name = name;\n" + 
        "            this.value = value;\n" + 
        "            this.currentString = string;\n" + 
        "            childs = new ArrayList<MyNode>();\n" + 
        "        }\n" + 
        "        public MyNode(Element element){\n" + 
        "            this.name = getValidName(element.getNodeName());\n" + 
        "            this.value = element.getNodeValue();\n" + 
        "            childs = new ArrayList<MyNode>();\n" + 
        "            for (int i = 0; i < element.getChildNodes().getLength(); i++) {\n" + 
        "                if (!element.getChildNodes().item(i).getNodeName().contains(\"#text\"))\n" + 
        "                addChild(new MyNode(element.getChildNodes().item(i)));\n" + 
        "            }\n" + 
        "        }\n" + 
        "        public MyNode(Node node){\n" + 
        "            this.name = getValidName(node.getNodeName());\n" + 
        "            this.value = node.getNodeValue();\n" + 
        "            childs = new ArrayList<MyNode>();\n" + 
        "            for (int i = 0; i < node.getChildNodes().getLength(); i++) {\n" + 
        "                if (node.getChildNodes().item(i).getNodeName().contains(\"#text\") && node.getChildNodes().item(i).getNodeValue() != null)\n" + 
        "                    this.value = node.getChildNodes().item(i).getNodeValue();\n" + 
        "                if (!node.getChildNodes().item(i).getNodeName().contains(\"#text\")) \n" + 
        "                    addChild(new MyNode(node.getChildNodes().item(i)));\n" + 
        "            }\n" + 
        "        }\n" + 
        "        public boolean isEmpty(){ return N == 0; }\n" + 
        "        public int size() { return N; }\n" + 
        "        /*private void resize (int max){\n" + 
        "            Node[] temp = (Node[]) new Object[max];\n" + 
        "            for (int i = 0; i < N; i++)\n" + 
        "                temp[i] = childs[i];\n" + 
        "            childs = temp;\n" + 
        "        }*/\n" + 
        "\n" + 
        "        public void setValue(Object value) {\n" + 
        "            this.value = value;\n" + 
        "        }\n" + 
        "\n" + 
        "        public Object getValue() {\n" + 
        "            if (value != null) return this.value;\n" + 
        "            return null;\n" + 
        "        }\n" + 
        "\n" + 
        "        public void addChild(MyNode child) {\n" + 
        "            //if (N == childs.size()) resize(2 * childs.length);\n" + 
        "            this.childs.add(child);\n" + 
        "            N++;\n" + 
        "        }\n" + 
        "        public MyNode getChild(String name){\n" + 
        "            for (MyNode child : childs){\n" + 
        "                    if (child.getName().equals(name)) return child;\n" + 
        "            }\n" + 
        "            return null;\n" + 
        "        }\n" + 
        "        /*public Node delChild(){\n" + 
        "            Node item = childs[--N];\n" + 
        "            childs[N] = null;\n" + 
        "            if (N > 0 && N == childs.length/4) resize(childs.length/2);\n" + 
        "            return item;\n" + 
        "        }*/\n" + 
        "        public MyNode getLast(){\n" + 
        "            return childs.get(N-1);\n" + 
        "        }\n" + 
        "        //public Node[] getChilds(){ return childs; }\n" + 
        "        //public Node getChild(int i){ return childs[i]; }\n" + 
        "\n" + 
        "        public void setName(String name) {\n" + 
        "            this.name = name;\n" + 
        "        }\n" + 
        "\n" + 
        "        public String getName() {\n" + 
        "            return name;\n" + 
        "        }\n" + 
        "\n" + 
        "        public String toString() {\n" + 
        "            return this.name + \": \" + this.value;\n" + 
        "        }\n" + 
        "        \n" + 
        "        public String printNode(){\n" + 
        "            String str = \"<\" + this.name + \">\";\n" + 
        "            \n" + 
        "            if (isEmpty()) str += value;\n" + 
        "            /*if (isEmpty()){\n" + 
        "                if (value != null) str += value;\n" + 
        "                else return \"</\" + this.name + \">\";\n" + 
        "            }*/\n" + 
        "            else {\n" + 
        "                str += \"\\n\";\n" + 
        "                for (MyNode child : this)\n" + 
        "                    str += printNode(child, \"\");\n" + 
        "            }\n" + 
        "            str += \"</\" + this.name + \">\";\n" + 
        "            return str;\n" + 
        "        }\n" + 
        "        private String printNode(MyNode node, String space){\n" + 
        "            space += \"    \";\n" + 
        "            String str = space + \"<\" + node.getName() + \">\";\n" + 
        "            if (node.isEmpty()){\n" + 
        "                if (node.value == null) return space + \"<\" + node.name + \"/>\\n\";\n" + 
        "                else str += node.value;\n" + 
        "                str += \"</\" + node.getName() + \">\\n\";\n" + 
        "            }\n" + 
        "            else { \n" + 
        "                str += \"\\n\";\n" + 
        "                for (MyNode child : node)\n" + 
        "                    str += printNode(child, space);\n" + 
        "                str += space + \"</\" + node.getName() + \">\\n\";\n" + 
        "            }\n" + 
        "            return str;\n" + 
        "        }\n" + 
        "        \n" + 
        "        private int count = 0;\n" + 
        "        public Map<String, String> printMap(){\n" + 
        "            String mapKey = this.name;\n" + 
        "            String mapValue = null;\n" + 
        "            Map<String, String> map = new HashMap<String, String>();\n" + 
        "            map.put(count++ + \"::0::\" + mapKey, mapValue);\n" + 
        "            \n" + 
        "            if (isEmpty()) mapValue += this.value;\n" + 
        "            else {\n" + 
        "                for (MyNode child : this){\n" + 
        "                    map.putAll(printMap(child, 0));\n" + 
        "                }\n" + 
        "            }\n" + 
        "            return map;\n" + 
        "        }\n" + 
        "        private Map<String, String> printMap(MyNode node, int level){\n" + 
        "            Map<String, String> map = new HashMap<String, String>();\n" + 
        "            String mapKey = node.getName();\n" + 
        "            String mapValue = null;\n" + 
        "            if (node.getValue() != null) mapValue = node.getValue().toString();\n" + 
        "            map.put(count++ + \"::\" + level + \"::\" + mapKey, mapValue);\n" + 
        "            if (node.isEmpty()){\n" + 
        "                return map;\n" + 
        "            }\n" + 
        "            else {\n" + 
        "                for (MyNode child : node){\n" + 
        "                    map.putAll(printMap(child, level + 1));\n" + 
        "                }\n" + 
        "            }\n" + 
        "            return map;\n" + 
        "        }\n" + 
        "\n" + 
        "        public Iterator iterator() {\n" + 
        "            return new NodeIterator();\n" + 
        "        }\n" + 
        "\n" + 
        "        public void setCurrentString(String currentString) {\n" + 
        "            this.currentString = currentString;\n" + 
        "        }\n" + 
        "\n" + 
        "        public String getCurrentString() {\n" + 
        "            return currentString;\n" + 
        "        }\n" + 
        "\n" + 
        "        private class NodeIterator implements Iterator<MyNode>{\n" + 
        "            private int i = 0;\n" + 
        "\n" + 
        "            public boolean hasNext() {\n" + 
        "                return i < N;\n" + 
        "            }\n" + 
        "\n" + 
        "            public MyNode next() {\n" + 
        "                return childs.get(i++);\n" + 
        "            }\n" + 
        "\n" + 
        "            public void remove() {\n" + 
        "            }\n" + 
        "        }\n" + 
        "        \n" + 
        "        private String getValidName(String name){\n" + 
        "            name = !name.contains(\":\") ? name : name.split(\":\")[name.split(\":\").length-1];\n" + 
        "            if (name.length() > 1) name = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());\n" + 
        "            else if (name.length() > 0) name = name.toUpperCase();\n" + 
        "            return name;\n" + 
        "        }\n" + 
        "}\n";

        File dirFile = new File(path + "\\utils");
        if (!dirFile.isFile() && !dirFile.isDirectory())
        dirFile.mkdir();
        
        try {
            File newFile = new File(path + "\\utils\\" + "MyNode.java");
            FileWriter fileWriter = new FileWriter(newFile);
            fileWriter.write(myNodeClass);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    private static void createMyFieldJava(){
        if (path == null) return;
        String myNodeClass = "package " + packageName + ".utils;\n" + 
        "\n" + 
        "public class MyField{\n" + 
        "    private String name;\n" + 
        "    private String type;\n" + 
        "    private String parentEl;\n" + 
        "    private boolean array;\n" + 
        "    \n" + 
        "    public MyField(String name, String type, String parentEl, boolean array){\n" + 
        "        this.name = name;\n" + 
        "        this.type = type;\n" + 
        "        this.parentEl = parentEl;\n" + 
        "        this.array = array;\n" + 
        "    }\n" + 
        "\n" + 
        "    public void setName(String name) {\n" + 
        "        this.name = name;\n" + 
        "    }\n" + 
        "\n" + 
        "    public String getName() {\n" + 
        "        return name;\n" + 
        "    }\n" + 
        "\n" + 
        "    public void setType(String type) {\n" + 
        "        this.type = type;\n" + 
        "    }\n" + 
        "\n" + 
        "    public String getType() {\n" + 
        "        return type;\n" + 
        "    }\n" + 
        "\n" + 
        "    public void setParentEl(String parentEl) {\n" + 
        "        this.parentEl = parentEl;\n" + 
        "    }\n" + 
        "\n" + 
        "    public String getParentEl() {\n" + 
        "        return parentEl;\n" + 
        "    }\n" + 
        "\n" + 
        "    public void setArray(boolean array) {\n" + 
        "        this.array = array;\n" + 
        "    }\n" + 
        "\n" + 
        "    public boolean isArray() {\n" + 
        "        return array;\n" + 
        "    }\n" + 
        "    \n" + 
        "}\n";

        File dirFile = new File(path + "\\utils");
        if (!dirFile.isFile() && !dirFile.isDirectory())
        dirFile.mkdir();
        
        try {
            File newFile = new File(path + "\\utils\\" + "MyField.java");
            FileWriter fileWriter = new FileWriter(newFile);
            fileWriter.write(myNodeClass);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    private static void createMyNodeParser(){
        if (path == null) return;
        String myNodeClass = "package " + packageName + ".utils;\n" + 
        "\n" + 
        "\n" +
        "public class MyNodeParser.java{\n" +
        "    /** Parce sting entry to MyNode structure\n" + 
        "     * \n" + 
        "     * */\n" + 
        "    public static MyNode parseDexstra(String string) {\n" + 
        "        string = fetchNormalize(string);\n" + 
        "        MyNode rootNode = new MyNode(\"root\", null, string);        \n" + 
        "        rootNode = parseDextra(rootNode);\n" + 
        "        return rootNode;\n" + 
        "    }\n" + 
        "\n" + 
        "    private static MyNode parseDextra(MyNode root) {\n" + 
        "        if (root.getCurrentString().length() == 0) return root;\n" + 
        "        root.setCurrentString(delSpace(root.getCurrentString()));\n" + 
        "        int left = root.getCurrentString().indexOf(\"<\");\n" + 
        "        int right = root.getCurrentString().indexOf(\">\", left);\n" + 
        "        if (left == 0 &&\n" + 
        "            root.getCurrentString().substring(left, left + 2).equals(\"</\")) {\n" + 
        "            return root;\n" + 
        "        }\n" + 
        "        if (left == 0) {\n" + 
        "            boolean isEnd = false;\n" + 
        "            String endName = \"\";\n" + 
        "            do {\n" + 
        "                if (root.getCurrentString().length() == 0) return root;\n" + 
        "                if (isEmptyNode(root.getCurrentString().substring(left, right + 1))) {\n" + 
        "                    endName = root.getCurrentString().substring(left, right+1);\n" + 
        "                } else {\n" + 
        "                if (isEndNode(root.getCurrentString().substring(left, right + 1)) && root.getName().equals(getClearName(root.getCurrentString().substring(left, right + 1)))){\n" + 
        "                    break;\n" + 
        "                }\n" + 
        "                String nameChild = getClearName(root.getCurrentString().substring(left + 1, right));\n" + 
        "                MyNode child = new MyNode(nameChild, null, root.getCurrentString());\n" + 
        "                child.setCurrentString(root.getCurrentString().substring(right + 1));\n" + 
        "                child = parseDextra(child);\n" + 
        "                root.addChild(child);\n" + 
        "                root.setCurrentString(child.getCurrentString());\n" + 
        "                    \n" + 
        "                left = root.getCurrentString().indexOf(\"<\");\n" + 
        "                right = root.getCurrentString().indexOf(\">\", left);\n" + 
        "                endName = root.getCurrentString().substring(left, right+1);\n" + 
        "            }\n" + 
        "                isEnd = root.getName().equals(getClearName(endName));\n" + 
        "                \n" + 
        "                root.setCurrentString(root.getCurrentString().substring(right + 1));\n" + 
        "                left = root.getCurrentString().indexOf(\"<\");\n" + 
        "                right = root.getCurrentString().indexOf(\">\", left);\n" + 
        "            } while (!(isEndNode(endName) && isEnd));\n" + 
        "            return root;\n" + 
        "        } else{\n" + 
        "            root.setValue(root.getCurrentString().substring(0, left));\n" + 
        "            root.setCurrentString(root.getCurrentString().substring(left));\n" + 
        "        }\n" + 
        "        left = root.getCurrentString().indexOf(\"<\");\n" + 
        "        right = root.getCurrentString().indexOf(\">\", left);\n" + 
        "\n" + 
        "        if (!root.getName().equals(getClearName(root.getCurrentString().substring(left, right+1)))){\n" + 
        "            root.setCurrentString(root.getCurrentString().substring(right + 1));\n" + 
        "            root = parseDextra(root);\n" + 
        "        }\n" + 
        "        return root;\n" + 
        "    }\n" + 
        "\n" + 
        "    private static String fetchNormalize(String response) {\n" + 
        "        response = response.replaceAll(\"&lt;\", \"<\");\n" + 
        "        response = response.replaceAll(\"&gt;\", \">\");\n" + 
        "        return response;\n" + 
        "    }\n" + 
        "    private static String getClearName(String str) {\n" + 
        "        str = str.replace(\"<\", \"\");\n" + 
        "        str = str.replace(\">\", \"\");\n" + 
        "        str = str.replace(\"/\", \"\");\n" + 
        "        str = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());\n" + 
        "        return str;\n" + 
        "    }\n" + 
        "    private static boolean isEndNode(String str){\n" + 
        "        return (str.contains(\"</\") && str.contains(\">\"));\n" + 
        "    }\n" + 
        "    private static boolean isEmptyNode(String str){\n" + 
        "        return (str.contains(\"<\") && str.contains(\"/>\"));\n" + 
        "    }\n" + 
        "    private static String delSpace(String string){\n" + 
        "        while (string.substring(0, 1).equals(\" \") || string.substring(0, 1).equals(\"\\n\")) string = string.substring(1);\n" + 
        "        return string;\n" + 
        "    }\n" +
        "}";

        File dirFile = new File(path + "\\utils");
        if (!dirFile.isFile() && !dirFile.isDirectory())
        dirFile.mkdir();
        
        try {
            File newFile = new File(path + "\\utils\\" + "MyNodeParser.java.java");
            FileWriter fileWriter = new FileWriter(newFile);
            fileWriter.write(myNodeClass);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String getHeadFile(String packageName, File directory){
        String headFile =   "package " + packageName + ";\n\n";
        //for (Map.Entry entry: extentions.entrySet())
            //headFile += entry.getValue();
        headFile +=         "import " + packageName + ".utils.*;\n";
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
                            "public class " + directory.getName() + "Client {\n" +
                            "\n";
        return headFile;
    }
    private static String getEndFile(){
        return "\n}";
    }

    /** function for generate Object to String structure client
     * 
     * @param dataClassName -   класс сгенерированных enum
     * @param fromPart      -   root элем для которого сейчас всё строим
     * */
    private static String generateRootStruct(String dataClassName, String fromPart, String fromType){
        String rootElName;
        String child;
        
        Map<String, String> functionsMap = new HashMap<String, String>();
        System.out.println("Complex dataType count: " + complexElement.size() + ", rootElement: " + fromPart);
        
        /*complexElement = sortByValue(complexElement);
        for (Map.Entry entry : complexElement.entrySet())
            System.out.println(entry.getKey().toString());*/
        
        for (Map.Entry entry : complexElement.entrySet()){
            String val = entry.getKey().toString();
            MyField mf = new MyField(val.split(" ")[1], val.split(" ")[2], val.split(" ")[0], val.split(" ")[3].contains("true"));
            if (mf.getParentEl().equals(fromPart)){
                rootElName = mf.getParentEl();
                child = mf.getName();
                
                if (fromPart != null && rootElName != null){
                    //System.out.println(" -->> generateSetObjectArray(" + dataClassName + ", " + fromPart + ", " + rootElName + ", " + child + ")");
                    if (mf.isArray()) {
                        Map.Entry entry__ = generateSetArray_2(dataClassName, fromPart, new MyField( fromPart, fromPart, "root", false), mf);
                        functionsMap.put(rootElName + ">" + child + ">" + entry__.getKey().toString(), entry__.getValue().toString());
                    } else {
                        Map.Entry entry__ = generateSetComplexType_2(dataClassName, fromPart, new MyField( fromPart, fromPart, "root", false), mf);
                        functionsMap.put(rootElName + ">" + child + ">" + entry__.getKey().toString(), entry__.getValue().toString());
                    }
                }
                /*for (Map.Entry entry_ : complexElement.entrySet()){
                    String val_ = entry_.getKey().toString();
                    MyField mf_ = new MyField(val.split(" ")[1], val.split(" ")[2], val.split(" ")[0], val.split(" ")[3].contains("true"));
                    if (val_.split(" ")[0].equals(rootElName)){
                        child = val_.split(" ")[1];
                        System.out.println("   " + mf_);
                        if (fromPart != null && rootElName != null && child != null){
                            Map.Entry entry__ = generateSetArray(dataClassName, fromPart, rootElName, child);
                            functionsMap.put(rootElName + ">" + child + ">" + entry__.getKey().toString(), entry__.getValue().toString());
                        }
                    }
                }*/
            }
        }
        //String rootElement = "CheckRequest";
        String code = 
            "    public static MyNode createRootObject(" + fromPart + " parent){\n" +
            "        String rootElement = \"" + fromType + "\";" + 
            "        MyNode parentNode = new MyNode(rootElement);\n" + 
            "        for (" + dataClassName + "." + fromPart + "Data val : " + dataClassName + "." + fromPart + "Data.values())\n" +
            "            if (val.isIsSimple()) parentNode.addChild(new MyNode(val.toString(), val.get(parent)));\n";
        for (Map.Entry entry : functionsMap.entrySet()){
             code +=
                 "        if (parent.get" + entry.getKey().toString().split(">")[1] + "() != null) {\n" +
                 "            //MyNode parentNode = new MyNode(\"" + entry.getKey().toString().split(">")[0] + "\");\n" +
                 "            parentNode = " + entry.getKey().toString().split(">")[2] + "\n" + //setFoundersArray(foundersNode, request, \"Founders\", \"Founder\");\n" +
                 "            //newNode.addChild(parentNode);\n" +
                 "        }\n";
        }
        code += 
            "    return parentNode;\n" +
            "    }\n\n";
        for (Map.Entry entry : functionsMap.entrySet())
            code += entry.getValue().toString();
        return code;
    }
    
    private static Map.Entry<String, String> generateSetArray(String dataClassName, String fromPart, String parent, String child){
        String addComplexTypeFunc = "";
        String setArrayName = "set" + parent + "Array(parentNode, parent, \"" + parent + "\", \"" + child + "\");";
        String setArray = 
            "    private static MyNode set" + parent + "Array(MyNode node, " + fromPart + " object, String parent, String child){\n" + 
            //"        MyNode parentNode = new MyNode(\"" + parent + "\");\n" +
            "        if (object.get" + parent + "() != null)\n" + 
            "            if (object.get" + parent + "().get" + child + "().size() > 0)\n" + 
            "                for (" + child + " f : object.get" + parent + "().get" + child + "()){\n" + 
            "                    MyNode childNode = new MyNode(child);\n" + 
            "                    for (" + dataClassName + "." + child + "Data val : " + dataClassName + "." + child + "Data.values())\n" + 
            "                    if (val.isIsSimple())childNode.addChild(new MyNode(val.toString(), val.get(f)));\n";
        Map<String, String> arrayMap = new HashMap<String, String>();
        Map<String, String> complexMap = new HashMap<String, String>();
        for (Map.Entry entry : complexElement.entrySet()){
            String val = entry.getKey().toString();
            if (val.split(" ")[0].equals(child))
                if (val.split(" ")[3].equals("true")) { // это массив
                    Map.Entry entry_ = generateSetArray(dataClassName, parent, val.split(" ")[0], val.split(" ")[1]);
                    arrayMap.put(entry_.getKey().toString(), entry_.getValue().toString());
                } else {// это complexType
                    Map.Entry entry_ = generateSetComplexType(dataClassName, val.split(" ")[0], val.split(" ")[1]);
                    complexMap.put(entry_.getKey().toString(), entry_.getValue().toString());
                }
        }
        for (Map.Entry entry : arrayMap.entrySet()){
            setArray += "                    " + entry.getKey() + "\n";
            addComplexTypeFunc += entry.getValue() + "\n";
        }
        for (Map.Entry entry : complexMap.entrySet()){
            setArray += "                    " + entry.getKey() + "\n";
            addComplexTypeFunc += entry.getValue() + "\n";
        }
        setArray +=
            "                    node.addChild(childNode);\n" +
            "                }\n" + 
            //"        node.addChild(parentNode);\n" + 
            "        return node;\n" + 
            "    }\n\n";
        setArray += addComplexTypeFunc;
        Map<String, String> map = new HashMap<String, String>();
        map.put(setArrayName, setArray);
        return map.entrySet().iterator().next();
    }
    private static Map.Entry<String, String> generateSetComplexType(String dataClassName, String fromPart, String child){
        String addComplexTypeFunc = "";
        String setComplexTypeName = "setComplex" + child + "type(childNode, f, \"" + child + "\");";
        String setComplexType = 
            "    private static MyNode setComplex" + child + "type(MyNode node, " + fromPart + " object, String child){\n" +
            "        if (object.get" + child + "() != null) {\n" +
            "            MyNode childNode = new MyNode(\"" + child + "\");\n" +
            "            " + child + " add = object.get" + child + "();\n" +
            "            for (" + dataClassName + "." + child + "Data val : " + dataClassName + "." + child + "Data.values())\n" +
            "                if (val.isIsSimple()) childNode.addChild(new MyNode(val.toString(), val.get(add)));\n";
        Map<String, String> arrayMap = new HashMap<String, String>();
        Map<String, String> complexMap = new HashMap<String, String>();
        for (Map.Entry entry : complexElement.entrySet()){
            String val = entry.getKey().toString();
            if (val.split(" ")[0].equals(child))
                if (val.split(" ")[3].equals("true")) { // это массив
                    Map.Entry entry_ = generateSetArray(dataClassName, child, val.split(" ")[0], val.split(" ")[1]);
                    arrayMap.put(entry_.getKey().toString(), entry_.getValue().toString());
                } else {// это complexType
                    Map.Entry entry_ = generateSetComplexType(dataClassName, val.split(" ")[0], val.split(" ")[1]);
                    complexMap.put(entry_.getKey().toString(), entry_.getValue().toString());
                }
        }
        for (Map.Entry entry : arrayMap.entrySet()){
            setComplexType += "            " + entry.getKey() + "\n";
            addComplexTypeFunc += entry.getValue() + "\n";
        }
        for (Map.Entry entry : complexMap.entrySet()){
            setComplexType += "            " + entry.getKey() + "\n";
            addComplexTypeFunc += entry.getValue() + "\n";
        }
        setComplexType +=
            "        node.addChild(childNode);\n" +
            "        }\n" +
            "        return node;\n" +
            "    }\n";
        setComplexType += addComplexTypeFunc;
        Map<String, String> map = new HashMap<String, String>();
        map.put(setComplexTypeName, setComplexType);
        return map.entrySet().iterator().next();
     }

    private static Map.Entry<String, String> generateSetArray_2(String dataClassName, String fromPart, MyField parentField, MyField childField){
        String addComplexTypeFunc = "";
        String setArrayName = "set" + childField.getName() + "For" + parentField.getName() + "Array(parentNode, parent); /*\"" + childField.getName() + "\" */";
        String dataClassType = childField.getType();
        if (childField.isArray()) dataClassType = childField.getType().substring(5, childField.getType().length()-1); 
        String parentDataClassType = parentField.getType();
        if (parentField.isArray()) dataClassType = parentField.getType().substring(5, parentField.getType().length()-1); 
              
        String setArray = 
            "    private static MyNode set" + childField.getName() + "For" + parentField.getName() + "Array(MyNode node, " + parentDataClassType + " object){\n" +
            //"        MyNode parentNode = new MyNode(\"" + parent + "\");\n" +
            "        if (object.get" + childField.getName() + "() != null)\n" + 
            "            if (object.get" + childField.getName() + "().size() > 0)\n" + 
            "                for (" + dataClassType + " parent : object.get" + childField.getName() + "()){\n" + 
            "                    MyNode childNode = new MyNode(\"" + childField.getName() + "\");\n" + 
            "                    for (" + dataClassName + "." + dataClassType + "Data val : " + dataClassName + "." + dataClassType + "Data.values())\n" + 
            "                    if (val.isIsSimple())childNode.addChild(new MyNode(val.toString(), val.get(parent)));\n";
        Map<String, String> arrayMap = new HashMap<String, String>();
        Map<String, String> complexMap = new HashMap<String, String>();
        for (Map.Entry entry : complexElement.entrySet()){
            String val = entry.getKey().toString();
            MyField mf = new MyField(val.split(" ")[1], val.split(" ")[2], val.split(" ")[0], val.split(" ")[3].contains("true"));
            if (val.split(" ")[0].equals(dataClassType))
                if (mf.isArray()) { // это массив
                    Map.Entry entry_ = generateSetArray__2(dataClassName, childField.getName(), childField, mf);
                    arrayMap.put(entry_.getKey().toString(), entry_.getValue().toString());
                } else {// это complexType
                    Map.Entry entry_ = generateSetComplexType_2(dataClassName, childField.getName(), childField, mf);
                    complexMap.put(entry_.getKey().toString(), entry_.getValue().toString());
                }
        }
        for (Map.Entry entry : arrayMap.entrySet()){
            setArray += "                    " + entry.getKey() + "\n";
            addComplexTypeFunc += entry.getValue() + "\n";
        }
        for (Map.Entry entry : complexMap.entrySet()){
            setArray += "                    " + entry.getKey() + "\n";
            addComplexTypeFunc += entry.getValue() + "\n";
        }
        setArray +=
            "                    node.addChild(childNode);\n" +
            "                }\n" + 
            //"        node.addChild(parentNode);\n" + 
            "        return node;\n" + 
            "    }\n";
        setArray += addComplexTypeFunc;
        Map<String, String> map = new HashMap<String, String>();
        map.put(setArrayName, setArray);
        return map.entrySet().iterator().next();
    }
    private static Map.Entry<String, String> generateSetArray__2(String dataClassName, String fromPart, MyField parentField, MyField childField){
        String addComplexTypeFunc = "";
        String setArrayName = "set" + childField.getName() + "For" + parentField.getName() + "Array(parentNode, parent); /* \"" + childField.getName() + "\" */";
        String dataClassType = childField.getType();
        if (childField.isArray()) dataClassType = childField.getType().substring(5, childField.getType().length()-1); 
        String parentDataClassType = parentField.getType();
        if (parentField.isArray()) parentDataClassType = parentField.getType().substring(5, parentField.getType().length()-1); 
              
        String setArray = 
            "    private static MyNode set" + childField.getName() + "For" + parentField.getName() + "Array(MyNode node, " + parentDataClassType + " object){\n" +
            //"        MyNode parentNode = new MyNode(\"" + parent + "\");\n" +
            "        if (object.get" + childField.getName() + "() != null)\n" + 
            "            if (object.get" + childField.getName() + "().size() > 0)\n" + 
            "                for (" + dataClassType + " parent : object.get" + childField.getName() + "()){\n" + 
            "                    MyNode parentNode = new MyNode(\"" + childField.getName() + "\");\n" + 
            "                    for (" + dataClassName + "." + dataClassType + "Data val : " + dataClassName + "." + dataClassType + "Data.values())\n" + 
            "                    if (val.isIsSimple())parentNode.addChild(new MyNode(val.toString(), val.get(parent)));\n";
        Map<String, String> arrayMap = new HashMap<String, String>();
        Map<String, String> complexMap = new HashMap<String, String>();
        for (Map.Entry entry : complexElement.entrySet()){
            String val = entry.getKey().toString();
            MyField mf = new MyField(val.split(" ")[1], val.split(" ")[2], val.split(" ")[0], val.split(" ")[3].contains("true"));
            if (val.split(" ")[0].equals(dataClassType))
                if (mf.isArray()) { // это массив
                    Map.Entry entry_ = generateSetArray__2(dataClassName, childField.getName(), childField, mf);
                    arrayMap.put(entry_.getKey().toString(), entry_.getValue().toString());
                } else {// это complexType
                    Map.Entry entry_ = generateSetComplexType_2(dataClassName, childField.getName(), childField, mf);
                    complexMap.put(entry_.getKey().toString(), entry_.getValue().toString());
                }
        }
        for (Map.Entry entry : arrayMap.entrySet()){
            setArray += "                    " + entry.getKey() + "\n";
            addComplexTypeFunc += entry.getValue() + "\n";
        }
        for (Map.Entry entry : complexMap.entrySet()){
            setArray += "                    " + entry.getKey() + "\n";
            addComplexTypeFunc += entry.getValue() + "\n";
        }
        setArray +=
            "                    node.addChild(parentNode);\n" +
            "                }\n" + 
            //"        node.addChild(parentNode);\n" + 
            "        return node;\n" + 
            "    }\n";
        setArray += addComplexTypeFunc;
        Map<String, String> map = new HashMap<String, String>();
        map.put(setArrayName, setArray);
        return map.entrySet().iterator().next();
    }
    private static Map.Entry<String, String> generateSetComplexType_2(String dataClassName, String fromPart, MyField parentField, MyField childField){
        String addComplexTypeFunc = "";
        String setComplexTypeName = "setComplex" + childField.getName() + "For" + parentField.getName() + "type(parentNode, parent); /* \"" + childField.getName() + "\" */";
         String dataClassType = childField.getType();
         if (childField.isArray()) dataClassType = childField.getType().substring(5, childField.getType().length()-1); 
         String parentDataClassType = parentField.getType();
         if (parentField.isArray()) parentDataClassType = parentField.getType().substring(5, parentField.getType().length()-1); 
        String setComplexType = 
            "    private static MyNode setComplex" + childField.getName() + "For" + parentField.getName() + "type(MyNode node, " + parentDataClassType + " object){\n" +
            "        if (object.get" + childField.getName() + "() != null) {\n" +
            "            MyNode parentNode = new MyNode(\"" + childField.getName() + "\");\n" +
            "            " + dataClassType + " parent = object.get" + childField.getName() + "();\n" +
            "            for (" + dataClassName + "." + dataClassType + "Data val : " + dataClassName + "." + dataClassType + "Data.values())\n" +
            "                if (val.isIsSimple()) parentNode.addChild(new MyNode(val.toString(), val.get(parent)));\n";
        Map<String, String> arrayMap = new HashMap<String, String>();
        Map<String, String> complexMap = new HashMap<String, String>();
        for (Map.Entry entry : complexElement.entrySet()){
            String val = entry.getKey().toString();
            MyField mf = new MyField(val.split(" ")[1], val.split(" ")[2], val.split(" ")[0], val.split(" ")[3].contains("true"));
            if (mf.getParentEl().equals(dataClassType))
                if (mf.isArray()) { // это массив
                    Map.Entry entry_ = generateSetArray__2(dataClassName, childField.getName(), childField, mf);
                    arrayMap.put(entry_.getKey().toString(), entry_.getValue().toString());
                } else {// это complexType
                    Map.Entry entry_ = generateSetComplexType_2(dataClassName, childField.getName(), childField, mf);
                    complexMap.put(entry_.getKey().toString(), entry_.getValue().toString());
                }
        }
        for (Map.Entry entry : arrayMap.entrySet()){
            setComplexType += "            " + entry.getKey() + "\n";
            addComplexTypeFunc += entry.getValue() + "\n";
        }
        for (Map.Entry entry : complexMap.entrySet()){
            setComplexType += "            " + entry.getKey() + "\n";
            addComplexTypeFunc += entry.getValue() + "\n";
        }
        setComplexType +=
            "        node.addChild(parentNode);\n" +
            "        }\n" +
            "        return node;\n" +
            "    }\n";
        setComplexType += addComplexTypeFunc;
        Map<String, String> map = new HashMap<String, String>();
        map.put(setComplexTypeName, setComplexType);
        return map.entrySet().iterator().next();
     }

    private static class MyField{
        private String name;
        private String type;
        private String parentEl;
        private boolean array;
        
        public MyField(String name, String type, String parentEl, boolean array){
            this.name = name;
            this.type = type;
            this.parentEl = parentEl;
            this.array = array;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setParentEl(String parentEl) {
            this.parentEl = parentEl;
        }

        public String getParentEl() {
            return parentEl;
        }

        public void setArray(boolean array) {
            this.array = array;
        }

        public boolean isArray() {
            return array;
        }
        
        public String toString(){
            return "name: " + getName() + ", type: " + getType() + ", parent: " + getParentEl() + ", isArray: " + isArray();
        }
    }
    
    /** function for generate String to Object structure client
     * 
     * @param dataClassName -   класс сгенерированных enum
     * @param fromPart      -   root элем для которого сейчас всё строим
     * */
    private static String generateRootObjectStruct_2(String dataClassName, String fromPart, String formType){
        String rootElName;
        String child;
        
        Map<String, String> functionsMap = new HashMap<String, String>();
        
        System.out.println("Complex dataType count: " + complexElement.size() + ", rootElement: " + fromPart);

        /*complexElement = sortByValue(complexElement);
        for (Map.Entry entry : complexElement.entrySet())
            System.out.println(entry.getKey().toString());*/
        for (Map.Entry entry : complexElement.entrySet()){
            String val = entry.getKey().toString();
            MyField mf = new MyField(val.split(" ")[1], val.split(" ")[2], val.split(" ")[0], val.split(" ")[3].contains("true"));
            if (mf.getParentEl().equals(fromPart)){
                rootElName = mf.getParentEl();
                child = mf.getName();
                //System.out.println("complex entry: " + val + " :" + rootElName + ": parent: " + mf.getParentEl());
                //System.out.println(" -->> generateSetObjectArray(" + dataClassName + ", " + fromPart + ", " + mf.toString() + ")");
                        
                if (fromPart != null && rootElName != null){
                    //System.out.println(" -->> generateSetObjectArray(" + dataClassName + ", " + fromPart + ", " + rootElName + ", " + child + ")");
                    Map.Entry entry__ = generateSetObjectArray_2(dataClassName, fromPart, new MyField( fromPart, fromPart, "root", false), mf);
                    functionsMap.put(rootElName + ">" + child + ">" + entry__.getKey().toString(), entry__.getValue().toString());
                
                } /*else { // complex
                        Map.Entry entry__ = generateSetObjectComplexType_2(dataClassName,  child, val.split(" ")[0], val.split(" ")[1]);
                        functionsMap.put(rootElName + ">" + child + ">" + entry__.getKey().toString(), entry__.getValue().toString());
                    }*/
            }
        }
        //String rootElement = "CheckRequest";
        String code = 
            "    /** This converting data function from MyNode element based on {@link MyNode}\n" + 
            "     *                                to {@link " + fromPart + "} you root WebElement\n" + 
            "     *                                \n" + 
            "     * @param node  - source node   typeOf{@link MyNode}\n" + 
            "     * @return " + fromPart + "\n" + 
            "     * */\n" +
            "    public static " + fromPart + " createRoot" + dataClassName + "StringToObject(MyNode node){    \n" + 
            "        " + fromPart + " parent = new " + fromPart + "();\n" + 
            "        MyNode parentNode = node.getChild(\"" + formType + "\");\n" + 
            "        for (" + dataClassName + "." + fromPart + "Data val : " + dataClassName + "." + fromPart + "Data.values()){\n" + 
            "            if (val.isIsSimple()){\n" + 
            "                Object value = null;\n" + 
            "                MyNode destNode = parentNode.getChild(val.toString());\n" + 
            "                if (destNode != null){\n" + 
            "                    value = destNode.getValue();\n" + 
            "                    parent = val.set(parent, value);  \n" + 
            "                }\n" +
            "            }\n" + 
            "        }\n";        
        for (Map.Entry entry : functionsMap.entrySet()){
             code +=
                 "        if (parentNode.getChild(\"" + entry.getKey().toString().split(">")[1] + "\") != null) {\n" + 
                 "            parent = " + entry.getKey().toString().split(">")[2] + "\n" + 
                 "        }\n";
        }
        code += 
            "    return parent;\n" +
            "    }\n\n";
        for (Map.Entry entry : functionsMap.entrySet())
            code += entry.getValue().toString();
        return code;
    }
    
    /** Начально рекурсивная функция */
    private static Map.Entry<String, String> generateSetObjectArray_2(String dataClassName, String fromParent, MyField parentField, MyField childField){
        String addComplexTypeFunc = "";
        //String setArrayName = "setObject" + childField.getName() + "For" + parentField.getName() + "Array(parentNode, parent, \"" + childField.getParentEl() + "\", \"" + childField.getName() + "\");";
        String setArrayName = "setObject" + childField.getName() + "For" + parentField.getName() + "Array(parentNode, parent); /*\"" + childField.getParentEl() + "\", \"" + childField.getName() + "\");*/";
        String dataClassType = childField.getType();
        if (childField.isArray()) dataClassType = childField.getType().substring(5, childField.getType().length()-1); 
        String parentDataClassType = parentField.getType();
        if (parentField.isArray()) dataClassType = parentField.getType().substring(5, parentField.getType().length()-1); 
        String setArray =             
            "    /** set the value of the " + childField.getName() + " {@link " + dataClassType + "} object  for {@link " + childField.getParentEl() + "} parent object\n" + 
            "     * \n" + 
            "     * @param rootNode  - source node   typeOf{@link MyNode}\n" + 
            "     * @param object    - parent Object typeOf{@link " + childField.getParentEl() + "}\n" + 
            "     * @return " + childField.getParentEl() + "\n" + 
            "     * */\n" +
            //"    private static " + parentDataClassType + " setObject" + childField.getName() + "For" + parentField.getName() + "Array(MyNode rootNode, " + parentDataClassType + " object, String parentField, String childField){\n" + 
            "    private static " + parentDataClassType + " setObject" + childField.getName() + "For" + parentField.getName() + "Array(MyNode rootNode, " + parentDataClassType + " object){\n" +
            "        MyNode node = rootNode.getChild(\"" + childField.getName() + "\");\n" +
            "        if (node != null && !node.isEmpty()){ \n" + 
            //"            " + childField.getParentEl() + " arrays = new " + childField.getParentEl() + "();\n" + 
            //"            for (MyNode childNode : node){\n" + 
            "                " + dataClassType + " array = new " + dataClassType + "();\n" + 
            "                for (" + dataClassName + "." + dataClassType + "Data val : " + dataClassName + "." + dataClassType + "Data.values()){\n" + 
            "                    if (val.isIsSimple()){\n" + 
            "                        Object value = null;\n" + 
            "                        MyNode destNode = node.getChild(val.toString());\n" + 
            "                        if (destNode != null){\n" + 
            "                            value = destNode.getValue();\n" + 
            "                            array = val.set(array, value);\n" + 
            "                        }\n" +
            "                    }\n" +
            "                }\n";
        Map<String, String> arrayMap = new HashMap<String, String>();
        Map<String, String> complexMap = new HashMap<String, String>();
        //boolean isArray = false;

        //System.out.println(":::" + childField.toString());
        for (Map.Entry entry : complexElement.entrySet()){
            String val = entry.getKey().toString();
            MyField mf = new MyField(val.split(" ")[1], val.split(" ")[2], val.split(" ")[0], val.split(" ")[3].contains("true"));
            //System.out.println(" <--->" + mf);
            if (mf.getParentEl().equals(dataClassType)){  
                Map.Entry entry__ = generateSetObjectArray__2(dataClassName, childField.getName(), childField, mf, 0);
                arrayMap.put(entry__.getKey().toString(), entry__.getValue().toString());
            }
        }
        for (Map.Entry entry : arrayMap.entrySet()){
            setArray += "                array = " + entry.getKey() + "\n";
            addComplexTypeFunc += entry.getValue() + "\n";
        }
        for (Map.Entry entry : complexMap.entrySet()){
            setArray += "                array = " + entry.getKey() + "\n";
            addComplexTypeFunc += entry.getValue() + "\n";
        }
        if (childField.isArray()) setArray += "                object.get" + childField.getName() + "().add(array);\n"; // если массив
        else         setArray += "                object.set" + childField.getName() + "(array);\n"; // если комплекс
        setArray +=
            //"            }\n" +
            //"            object.set" + childField.getParentEl() + "(arrays);\n" + 
            "        }\n" + 
            "        return object;\n" + 
            "    }\n";
        setArray += addComplexTypeFunc;
        Map<String, String> map = new HashMap<String, String>();
        map.put(setArrayName, setArray);
        return map.entrySet().iterator().next();
    }
    /** Второй и последующие уровни рекурсивного вызова */
    private static Map.Entry<String, String> generateSetObjectArray__2(String dataClassName, String fromParent, MyField parentField, MyField childField, int level){
        String addComplexTypeFunc = "";
        //String setArrayName = "setObject" + childField.getName() + "For" + parentField.getName() + "Array(node, array, \"" + childField.getParentEl() + "\", \"" + childField.getName() + "\");";
        String setArrayName = "setObject" + childField.getName() + "For" + parentField.getName() + "Array(node, array); /*\"" + childField.getParentEl() + "\", \"" + childField.getName() + "\");*/";
        String dataClassType = childField.getType();
        if (childField.isArray()) dataClassType = childField.getType().substring(5, childField.getType().length()-1); 
        String parentDataClassType = parentField.getType();
        if (parentField.isArray()) parentDataClassType = parentField.getType().substring(5, parentField.getType().length()-1); 
        String setArray = 
            //"    private static " + parentDataClassType + " setObject" + childField.getName() + "For" + parentField.getName() + "Array(MyNode rootNode, " + parentDataClassType + " object, String parentField, String childField){\n" + 
            "    /** set the value of the " + childField.getName() + " {@link " + dataClassType + "} object  for {@link " + childField.getParentEl() + "} parent object\n" + 
            "     * \n" + 
            "     * @param rootNode  - source node   typeOf{@link MyNode}\n" + 
            "     * @param object    - parent Object typeOf{@link " + childField.getParentEl() + "}\n" + 
            "     * @return " + childField.getParentEl() + "\n" + 
            "     * */\n" +
            "    private static " + parentDataClassType + " setObject" + childField.getName() + "For" + parentField.getName() + "Array(MyNode rootNode, " + parentDataClassType + " object){\n" +
            "        MyNode node = rootNode.getChild(\"" + childField.getName() + "\");\n" +
            "        if (node != null && !node.isEmpty()){ \n" + 
            //"            " + childField.getParentEl() + " arrays = new " + childField.getParentEl() + "();\n" + 
            //"            for (MyNode childNode : node){\n" + 
            "                " + dataClassType + " array = new " + dataClassType + "();\n" + 
            "                for (" + dataClassName + "." + dataClassType + "Data val : " + dataClassName + "." + dataClassType + "Data.values()){\n" + 
            "                    if (val.isIsSimple()){\n" + 
            "                        Object value = null;\n" + 
            "                        MyNode destNode = node.getChild(val.toString());\n" + 
            "                        if (destNode != null){\n" + 
            "                            value = destNode.getValue();\n" + 
            "                            array = val.set(array, value);\n" + 
            "                        }\n" +
            "                    }\n" +
            "                }\n";
        Map<String, String> arrayMap = new HashMap<String, String>();
        Map<String, String> complexMap = new HashMap<String, String>();
        //boolean isArray = false;

        //System.out.println("\t   " + level + ":::" + childField.toString());
        for (Map.Entry entry : complexElement.entrySet()){
            String val = entry.getKey().toString();
            MyField mf = new MyField(val.split(" ")[1], val.split(" ")[2], val.split(" ")[0], val.split(" ")[3].contains("true"));
            //System.out.println(" <--->" + mf);
            if (mf.getParentEl().equals(dataClassType)){
                //System.out.println("   <enter>" + mf);
                //System.out.println("      --compl>" + mf.toString());
                /*if (val.split(" ")[2].contains("List<"))*/ { // это массив // if (val.split(" ")[3].equals("true")) 
                    //System.out.println("         ---deep>parent: " + val.split(" ")[0] + ", child: " +  val.split(" ")[1]);
                    /*for (Map.Entry entry_ : complexElement.entrySet()){
                        String val_ = entry_.getKey().toString();
                        MyField mf_ = new MyField(val_.split(" ")[1], val_.split(" ")[2], val_.split(" ")[0], val_.split(" ")[3].contains("true"));
                        if (mf_.getParentEl().equals(mf.getName())){*/
                //System.out.println("          <enter2> +" + mf_.toString());
                Map.Entry entry__ = generateSetObjectArray__2(dataClassName, childField.getName(), childField, mf, level++);
                arrayMap.put(entry__.getKey().toString(), entry__.getValue().toString());
                     /*   }
                    }*/
                    //System.out.println("         ---deep>endddddd");
                } /*else {// это complexType
                    Map.Entry entry_ = generateSetObjectComplexType_2(dataClassName,  child, val.split(" ")[0], val.split(" ")[1]);
                    complexMap.put(entry_.getKey().toString(), entry_.getValue().toString());
                }*/
                //System.out.println("      --compl>ennd");
            //System.out.println("   <end> +" + mf.toString());
            }
        }
        for (Map.Entry entry : arrayMap.entrySet()){
            setArray += "                array = " + entry.getKey() + "\n";
            addComplexTypeFunc += entry.getValue() + "\n";
        }
        for (Map.Entry entry : complexMap.entrySet()){
            setArray += "                array = " + entry.getKey() + "\n";
            addComplexTypeFunc += entry.getValue() + "\n";
        }
        if (childField.isArray()) setArray += "                object.get" + childField.getName() + "().add(array);\n"; // если массив
        else         setArray += "                object.set" + childField.getName() + "(array);\n"; // если комплекс
        setArray +=
            //"            }\n" +
            //"            object.set" + childField.getParentEl() + "(arrays);\n" + 
            "        }\n" + 
            "        return object;\n" + 
            "    }\n";
        setArray += addComplexTypeFunc;
        Map<String, String> map = new HashMap<String, String>();
        map.put(setArrayName, setArray);
        return map.entrySet().iterator().next();
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

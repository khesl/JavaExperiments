package com.parsing.SoapToJSon;

public class MyNodeParser{
    /** Parce sting entry to MyNode structure
     *
     * */
    public static MyNode parseDexstra(String string) {
        string = fetchNormalize(string);
        MyNode rootNode = new MyNode("root", null, string);
        rootNode = parseDextra(rootNode);
        return rootNode;
    }

    private static MyNode parseDextra(MyNode root) {
        if (root.getCurrentString().length() == 0) return root;
        root.setCurrentString(delSpace(root.getCurrentString()));
        int left = root.getCurrentString().indexOf("<");
        int right = root.getCurrentString().indexOf(">", left);
        if (left == 0 &&
                root.getCurrentString().substring(left, left + 2).equals("</")) {
            return root;
        }
        if (left == 0) {
            boolean isEnd = false;
            String endName = "";
            do {
                if (root.getCurrentString().length() == 0) return root;
                if (isEmptyNode(root.getCurrentString().substring(left, right + 1))) {
                    endName = root.getCurrentString().substring(left, right+1);
                } else {
                    if (isEndNode(root.getCurrentString().substring(left, right + 1)) && root.getName().equals(getClearName(root.getCurrentString().substring(left, right + 1)))){
                        break;
                    }
                    String nameChild = getClearName(root.getCurrentString().substring(left + 1, right));
                    MyNode child = new MyNode(nameChild, null, root.getCurrentString());
                    child.setCurrentString(root.getCurrentString().substring(right + 1));
                    child = parseDextra(child);
                    root.addChild(child);
                    root.setCurrentString(child.getCurrentString());

                    left = root.getCurrentString().indexOf("<");
                    right = root.getCurrentString().indexOf(">", left);
                    endName = root.getCurrentString().substring(left, right+1);
                }
                isEnd = root.getName().equals(getClearName(endName));

                root.setCurrentString(root.getCurrentString().substring(right + 1));
                left = root.getCurrentString().indexOf("<");
                right = root.getCurrentString().indexOf(">", left);
            } while (!(isEndNode(endName) && isEnd));
            return root;
        } else{
            root.setValue(root.getCurrentString().substring(0, left));
            root.setCurrentString(root.getCurrentString().substring(left));
        }
        left = root.getCurrentString().indexOf("<");
        right = root.getCurrentString().indexOf(">", left);

        if (!root.getName().equals(getClearName(root.getCurrentString().substring(left, right+1)))){
            root.setCurrentString(root.getCurrentString().substring(right + 1));
            root = parseDextra(root);
        }
        return root;
    }

    private static String fetchNormalize(String response) {
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");
        return response;
    }
    private static String getClearName(String str) {
        str = str.replace("<", "");
        str = str.replace(">", "");
        str = str.replace("/", "");
        str = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
        return str;
    }
    private static boolean isEndNode(String str){
        return (str.contains("</") && str.contains(">"));
    }
    private static boolean isEmptyNode(String str){
        return (str.contains("<") && str.contains("/>"));
    }
    private static String delSpace(String string){
        while (string.substring(0, 1).equals(" ") || string.substring(0, 1).equals("\n")) string = string.substring(1);
        return string;
    }
}
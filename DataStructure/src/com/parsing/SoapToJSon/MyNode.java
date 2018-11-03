package com.parsing.SoapToJSon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @version 2.0.0
 *
 * */
public class MyNode implements Iterable<MyNode> {
        private String name;
        private Object value; // помоему название ноды
        private String currentString;
        private List<MyNode> childs;
        private int N = 0;
        
        public MyNode(String name) {
            this(name, null);
        }
        public MyNode(String name, Object value) {
            this(name, value, null);
        }
        public MyNode(String name, Object value, String string) {
            this.name = name;
            this.value = value;
            this.currentString = string;
            childs = new ArrayList<MyNode>();
        }
        public MyNode(Element element){
            this.name = getValidName(element.getNodeName());
            this.value = element.getNodeValue();
            childs = new ArrayList<MyNode>();
            for (int i = 0; i < element.getChildNodes().getLength(); i++) {
                if (!element.getChildNodes().item(i).getNodeName().contains("#text"))
                addChild(new MyNode(element.getChildNodes().item(i)));
            }
        }
        public MyNode(Node node){
            this.name = getValidName(node.getNodeName());
            this.value = node.getNodeValue();
            childs = new ArrayList<MyNode>();
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeName().contains("#text") && node.getChildNodes().item(i).getNodeValue() != null)
                    this.value = node.getChildNodes().item(i).getNodeValue();
                if (!node.getChildNodes().item(i).getNodeName().contains("#text")) 
                    addChild(new MyNode(node.getChildNodes().item(i)));
            }
        }
        public boolean isEmpty(){ return N == 0; }
        public int size() { return N; }
        /*private void resize (int max){
            Node[] temp = (Node[]) new Object[max];
            for (int i = 0; i < N; i++)
                temp[i] = childs[i];
            childs = temp;
        }*/

        public void setValue(Object value) {
            this.value = value;
        }

        public Object getValue() {
            if (value != null) return this.value;
            return null;
        }

        public void addChild(MyNode child) {
            //if (N == childs.size()) resize(2 * childs.length);
            this.childs.add(child);
            N++;
        }
        public MyNode getChild(String name){
            for (MyNode child : childs){
                    if (child.getName().equals(name)) return child;
            }
            return null;
        }
        /*public Node delChild(){
            Node item = childs[--N];
            childs[N] = null;
            if (N > 0 && N == childs.length/4) resize(childs.length/2);
            return item;
        }*/
        public MyNode getLast(){
            return childs.get(N-1);
        }
        //public Node[] getChilds(){ return childs; }
        //public Node getChild(int i){ return childs[i]; }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String toString() {
            return this.name + ": " + this.value;
        }
        
        public String printNode(){
            String str = "<" + this.name + ">";
            
            if (isEmpty()) str += value;
            /*if (isEmpty()){
                if (value != null) str += value;
                else return "</" + this.name + ">";
            }*/
            else {
                str += "\n";
                for (MyNode child : this)
                    str += printNode(child, "");
            }
            str += "</" + this.name + ">";
            return str;
        }
        private String printNode(MyNode node, String space){
            space += "    ";
            String str = space + "<" + node.getName() + ">";
            if (node.isEmpty()){
                if (node.value == null) return space + "<" + node.name + "/>\n";
                else str += node.value;
                str += "</" + node.getName() + ">\n";
            }
            else { 
                str += "\n";
                for (MyNode child : node)
                    str += printNode(child, space);
                str += space + "</" + node.getName() + ">\n";
            }
            return str;
        }

        public String convertToJSon(){
            String str = "{\"" + this.name + "\"";
            if (isEmpty()) str += ":\"" + value + "\"";
            else {
                str += ":{\n";
                for (MyNode child : this)
                    str += convertToJSon(child, ""); // возможно тут удалять ласт точку
                if (str.substring(str.length()-2, str.length()-1).equals(",")) str = str.substring(0, str.length()-2) + "\n";
                str += "}";
            }
            str += "}";
            return str;
        }
        private String convertToJSon(MyNode node, String space){
            space += "    ";
            String str = "";// = space + "\"" + node.getName() + "\"";
            if (node.isEmpty()){
                str = space + "\"" + node.getName() + "\"";
                if (node.value == null) return space + ":\"\"\n";
                else str += ":\"" + node.value + "\"";
                str += ",\n";
            }
            else {
                str = space + "\"" + node.getName() + "\":{\n";
                for (MyNode child : node)
                    str += convertToJSon(child, space);
                if (str.substring(str.length()-2, str.length()-1) == ",") str = str.substring(0, str.length()-2) + "\n";
                str += space + "}\n";
            }
            return str;
        }
        
        private int count = 0;
        public Map<String, String> printMap(){
            String mapKey = this.name;
            String mapValue = null;
            Map<String, String> map = new HashMap<String, String>();
            map.put(count++ + "::0::" + mapKey, mapValue);
            
            if (isEmpty()) mapValue += this.value;
            else {
                for (MyNode child : this){
                    map.putAll(printMap(child, 0));
                }
            }
            return map;
        }
        private Map<String, String> printMap(MyNode node, int level){
            Map<String, String> map = new HashMap<String, String>();
            String mapKey = node.getName();
            String mapValue = null;
            if (node.getValue() != null) mapValue = node.getValue().toString();
            map.put(count++ + "::" + level + "::" + mapKey, mapValue);
            if (node.isEmpty()){
                return map;
            }
            else {
                for (MyNode child : node){
                    map.putAll(printMap(child, level + 1));
                }
            }
            return map;
        }

        public Iterator iterator() {
            return new NodeIterator();
        }

        public void setCurrentString(String currentString) {
            this.currentString = currentString;
        }

        public String getCurrentString() {
            return currentString;
        }

        private class NodeIterator implements Iterator<MyNode>{
            private int i = 0;

            public boolean hasNext() {
                return i < N;
            }

            public MyNode next() {
                return childs.get(i++);
            }

            public void remove() {
            }
        }
        
        private String getValidName(String name){
            name = !name.contains(":") ? name : name.split(":")[name.split(":").length-1];
            if (name.length() > 1) name = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
            else if (name.length() > 0) name = name.toUpperCase();
            return name;
        }
}


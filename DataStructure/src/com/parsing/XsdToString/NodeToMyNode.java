package com.parsing.XsdToString;

import java.io.ByteArrayInputStream;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.xml.sax.SAXException;

public class NodeToMyNode {

    private static String text = "";

    public NodeToMyNode() {
        super();
    }

    public static void main(String[] args) {

        start(text);
    }

    public static void start(String request) {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder documentBuilder;
            documentBuilder = dbf.newDocumentBuilder();
            Document doc;
            doc = documentBuilder.parse(new ByteArrayInputStream(request.getBytes("UTF-8")));

            Element rootEl = (Element)doc.getFirstChild();
            System.out.println(rootEl.getNodeName());
            MyNode myNode = new MyNode(rootEl);
            System.out.println(myNode.printNode());

        } catch (IOException e) {
        } catch (SAXException e) {
        } catch (ParserConfigurationException e) {
        }
    }
}

package com.parsing.SoapToJSon;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MainClass{
    public static String test_text =
                    "<sms_info>\n" +
                    "    <to>87771652384</to>\n" +
                    "    <text>testMess</text>\n" +
                    "</sms_info>";

    public static void main(String[] args) {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder documentBuilder;
            documentBuilder = dbf.newDocumentBuilder();
            Document doc;
            doc = documentBuilder.parse(new ByteArrayInputStream(test_text.getBytes("UTF-8")));

            Element rootEl = (Element)doc.getFirstChild();
            System.out.println(rootEl.getNodeName());
            MyNode myNode = new MyNode(rootEl);
            System.out.println(myNode.convertToJSon());

        } catch (IOException e) {
        } catch (SAXException e) {
        } catch (ParserConfigurationException e) {
        }

    }

}
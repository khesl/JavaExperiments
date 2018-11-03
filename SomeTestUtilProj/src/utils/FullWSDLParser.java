import com.predic8.schema.*;
import com.predic8.wsdl.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FullWSDLParser {

    public static String[] listOperations(String filename) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
        Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(filename));
        System.out.println(d.getDocumentElement());
        NodeList elements = d.getElementsByTagName("operation");
        ArrayList<String> operations = new ArrayList<String>();
        for (int i = 0; i < elements.getLength(); i++) {
            operations.add(elements.item(i).getAttributes().getNamedItem("name").getNodeValue());
        }
        return operations.toArray(new String[operations.size()]);
    }

    public static void main(String[] args) {
        try {
            System.out.println(listOperations("S:\\my folder\\doc\\OracleBusProjects\\Е-штрафы\\итоговые сервисы и типы\\Eshtraf_Search_test_v1_3.wsdl"));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

        /*public static void getDefs() {
            WSDLParser parser = new WSDLParser();

            Definitions defs = parser.parse("http://srv-d-osb.acb.kz:8011/EGOV_TEST/Services/Proxy_Services/Eshtraf_Search_v1.2?wsdl");

            System.out.println("-------------- WSDL Details --------------");
            System.out.println("TargetNamespace: \t" + defs.getTargetNamespace());
            //System.out.println("Style: \t\t\t" + defs.getStyle());
            if (defs.getDocumentation() != null) {
                System.out.println("Documentation: \t\t" + defs.getDocumentation());
            }
            System.out.println("\n");

            /* For detailed schema information see the FullSchemaParser.java sample.* /
            System.out.println("Schemas: ");
            for (Schema schema : defs.getSchemas()) {
                System.out.println("  TargetNamespace: \t" + schema.getTargetNamespace());
            }
            System.out.println("\n");

            System.out.println("Messages: ");
            for (Message msg : defs.getMessages()) {
                System.out.println("  Message Name: " + msg.getName());
                System.out.println("  Message Parts: ");
                for (Part part : msg.getParts()) {
                    System.out.println("    Part Name: " + part.getName());
                    System.out.println("    Part Element: " + ((part.getElement() != null) ? part.getElement() : "not available!"));
                    System.out.println("    Part Type: " + ((part.getType() != null) ? part.getType() : "not available!" ));
                    System.out.println("");
                }
            }
            System.out.println("");

            System.out.println("PortTypes: ");
            for (PortType pt : defs.getPortTypes()) {
                System.out.println("  PortType Name: " + pt.getName());
                System.out.println("  PortType Operations: ");
                for (Operation op : pt.getOperations()) {
                    System.out.println("    Operation Name: " + op.getName());
                    System.out.println("    Operation Input Name: "
                            + ((op.getInput().getName() != null) ? op.getInput().getName() : "not available!"));
                    System.out.println("    Operation Input Message: "
                            + op.getInput().getMessage().getQname());
                    System.out.println("    Operation Output Name: "
                            + ((op.getOutput().getName() != null) ? op.getOutput().getName() : "not available!"));
                    System.out.println("    Operation Output Message: "
                            + op.getOutput().getMessage().getQname());
                    System.out.println("    Operation Faults: ");
                    if (op.getFaults().size() > 0) {
                        for (Fault fault : op.getFaults()) {
                            System.out.println("      Fault Name: " + fault.getName());
                            System.out.println("      Fault Message: " + fault.getMessage().getQname());
                        }
                    } else System.out.println("      There are no faults available!");

                }
                System.out.println("");
            }
            System.out.println("");

            System.out.println("Bindings: ");
            for (Binding bnd : defs.getBindings()) {
                System.out.println("  Binding Name: " + bnd.getName());
                System.out.println("  Binding Type: " + bnd.getPortType().getName());
                System.out.println("  Binding Protocol: " + bnd.getBinding().getProtocol());
                if(bnd.getBinding() instanceof AbstractSOAPBinding) System.out.println("  Style: " + (((AbstractSOAPBinding)bnd.getBinding()).getStyle()));
                System.out.println("  Binding Operations: ");
                for (BindingOperation bop : bnd.getOperations()) {
                    System.out.println("    Operation Name: " + bop.getName());
                    if(bnd.getBinding() instanceof AbstractSOAPBinding) {
                        System.out.println("    Operation SoapAction: " + bop.getOperation().getSoapAction());
                        System.out.println("    SOAP Body Use: " + bop.getInput().getBindingElements().get(0).getUse());
                    }
                }
                System.out.println("");
            }
            System.out.println("");

            System.out.println("Services: ");
            for (Service service : defs.getServices()) {
                System.out.println("  Service Name: " + service.getName());
                System.out.println("  Service Potrs: ");
                for (Port port : service.getPorts()) {
                    System.out.println("    Port Name: " + port.getName());
                    System.out.println("    Port Binding: " + port.getBinding().getName());
                    System.out.println("    Port Address Location: " + port.getAddress().getLocation()
                            + "\n");
                }
            }
            System.out.println("");
        }*/

        private static void out(String str) {
            System.out.println(str);
        }
    }
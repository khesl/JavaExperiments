import kz.gov.pki.kalkan.asn1.pkcs.PKCSObjectIdentifiers;
import kz.gov.pki.kalkan.jce.provider.KalkanProvider;
import kz.gov.pki.kalkan.xmldsig.KncaXS;
import org.apache.xml.security.encryption.XMLCipherParameters;
import org.apache.xml.security.exceptions.AlgorithmAlreadyRegisteredException;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class KalkanSing {

    private static String singStringForCheck = "<edw:request xmlns:edw=\"http://xmlns.kztc-cits/sign\">\n" +
            "            <edw:messageId>f4562557-3d71-410f-9a04-bbade9d32be0</edw:messageId>\n" +
            "            <edw:chainId>c11961d7-fe6e-47fb-869f-f15194a65b22</edw:chainId>\n" +
            "            <edw:sendTime>2018-09-04T09:56:27</edw:sendTime>\n" +
            "            <edw:messageType>request</edw:messageType>\n" +
            "            <edw:iinBin>051140008189</edw:iinBin>\n" +
            "            <ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
            "              <ds:SignedInfo>\n" +
            "                <ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n" +
            "                <ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gost34310-gost34311\"/>\n" +
            "                <ds:Reference URI=\"\">\n" +
            "                  <ds:Transforms>\n" +
            "                    <ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>\n" +
            "                    <ds:Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments\"/>\n" +
            "                  </ds:Transforms>\n" +
            "                  <ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gost34311\"/>\n" +
            "                  <ds:DigestValue>1S0qMjZZruxsmNbzTN4z7Mf4Yrfc5ibvcNQdWqiR29A=</ds:DigestValue>\n" +
            "                </ds:Reference>\n" +
            "              </ds:SignedInfo>\n" +
            "              <ds:SignatureValue>Tl9Bo0IpnO/2K1CQV5HywsJ4h6Siznr24sq8EeU6AHz+IO7U7GSf6DXtIh9DHlqmh3CSQ3Logf+D\n" +
            "pptSJZJccw==</ds:SignatureValue>\n" +
            "              <ds:KeyInfo>\n" +
            "                <ds:X509Data>\n" +
            "                  <ds:X509Certificate>MIIFLzCCBNmgAwIBAgIUGmMmr/QW9jc54Rk367PC4KR2thEwDQYJKoMOAwoBAQECBQAwgc8xCzAJ\n" +
            "BgNVBAYTAktaMRUwEwYDVQQHDAzQkNCh0KLQkNCd0JAxFTATBgNVBAgMDNCQ0KHQotCQ0J3QkDFM\n" +
            "MEoGA1UECgxD0KDQnNCaIMKr0JzQldCc0JvQldCa0JXQotCi0IbQmiDQotCV0KXQndCY0JrQkNCb\n" +
            "0KvSmiDSmtCr0JfQnNCV0KLCuzFEMEIGA1UEAww70rDQm9Ci0KLQq9KaINCa0KPTmNCb0JDQndCU\n" +
            "0KvQoNCj0KjQqyDQntCg0KLQkNCb0KvSmiAoR09TVCkwHhcNMTgwNzAyMDUwMDExWhcNMTkwNzAy\n" +
            "MDUwMDExWjCCAWwxKjAoBgNVBAMMIdCc0JDQlNCY0JHQoNCQ0JjQnNCe0JIg0JzQkNCg0JDQojEf\n" +
            "MB0GA1UEBAwW0JzQkNCU0JjQkdCg0JDQmNCc0J7QkjEYMBYGA1UEBRMPSUlOODMxMjIxMzAxNDYx\n" +
            "MQswCQYDVQQGEwJLWjEVMBMGA1UEBwwM0JDQm9Cc0JDQotCrMRUwEwYDVQQIDAzQkNCb0JzQkNCi\n" +
            "0KsxYjBgBgNVBAoMWdCQ0JrQptCY0J7QndCV0KDQndCe0JUg0J7QkdCp0JXQodCi0JLQniAiQVNJ\n" +
            "QUNSRURJVCBCQU5LICjQkNCX0JjQr9Ca0KDQldCU0JjQoiDQkdCQ0J3QmikiMRgwFgYDVQQLDA9C\n" +
            "SU45MjAxNDAwMDA1MDgxHzAdBgNVBCoMFtCa0JDQm9CU0KvQkdCQ0JXQktCY0KcxKTAnBgkqhkiG\n" +
            "9w0BCQEWGlVaQUtPVi5WQEFTSUFDUkVESVRCQU5LLktaMGwwJQYJKoMOAwoBAQEBMBgGCiqDDgMK\n" +
            "AQEBAQEGCiqDDgMKAQMBAQADQwAEQDMuDnVeaHGGuhEI77mXtWNOZQd+aThOr8Usbh6aG+JWxXjN\n" +
            "x51yn3xFbXN9FiWZLv7MLF54zr30Jfv7HoM7ygejggHbMIIB1zAOBgNVHQ8BAf8EBAMCBsAwKAYD\n" +
            "VR0lBCEwHwYIKwYBBQUHAwQGCCqDDgMDBAECBgkqgw4DAwQBAgUwDwYDVR0jBAgwBoAEVbW0rjAd\n" +
            "BgNVHQ4EFgQU1wxKGBGdRedMUeNROGep5MsXubcwXgYDVR0gBFcwVTBTBgcqgw4DAwIBMEgwIQYI\n" +
            "KwYBBQUHAgEWFWh0dHA6Ly9wa2kuZ292Lmt6L2NwczAjBggrBgEFBQcCAjAXDBVodHRwOi8vcGtp\n" +
            "Lmdvdi5rei9jcHMwUAYDVR0fBEkwRzBFoEOgQYYeaHR0cDovL2NybC5wa2kuZ292Lmt6L2dvc3Qu\n" +
            "Y3Jshh9odHRwOi8vY3JsMS5wa2kuZ292Lmt6L2dvc3QuY3JsMFQGA1UdLgRNMEswSaBHoEWGIGh0\n" +
            "dHA6Ly9jcmwucGtpLmdvdi5rei9kX2dvc3QuY3JshiFodHRwOi8vY3JsMS5wa2kuZ292Lmt6L2Rf\n" +
            "Z29zdC5jcmwwYwYIKwYBBQUHAQEEVzBVMC8GCCsGAQUFBzAChiNodHRwOi8vcGtpLmdvdi5rei9j\n" +
            "ZXJ0L3BraV9nb3N0LmNlcjAiBggrBgEFBQcwAYYWaHR0cDovL29jc3AucGtpLmdvdi5rejANBgkq\n" +
            "gw4DCgEBAQIFAANBAOFrLZbdEtC2toguzfFJRHB8buHYCrdCtXMm3TGxXdgvV9GSU7tf+MyqGwxm\n" +
            "zPG1oGnUIwjQULgf3ChVQh3SmY4=</ds:X509Certificate>\n" +
            "                </ds:X509Data>\n" +
            "              </ds:KeyInfo>\n" +
            "            </ds:Signature>\n" +
            "          </edw:request>";

    static String xmlString = "<edw:request xmlns:edw=\"http://xmlns.kztc-cits/sign\">\n" +
            "\t<edw:messageId>54be264c-9352-4ba1-993c-4ac5c2795afd</edw:messageId>\n" +
            "\t<edw:chainId>eb609638-f296-444d-ada7-eaf1343d4348</edw:chainId>\n" +
            "\t<edw:sendTime>2018-09-03T10:33:46</edw:sendTime>\n" +
            "\t<edw:messageType>request</edw:messageType>\n" +
            "\t<edw:iinBin>051140008189</edw:iinBin>\n" +
            "</edw:request>";

    public static void main(String[] args) {
        KalkanSing object = new KalkanSing();
        //String signedXml = object.signXML("S:\\my folder\\programming\\IntelliJ_IDEAProjects\\JavaExperiments\\SomeTestUtilProj\\src\\utils\\keys\\AUTH_RSA256_bc714c614b969bfe58dc9dcd38dad6d226e58913.p12", "Mmk0818");
        String signedXml = null;
        try {
            signedXml = object.signXML2(xmlString, "S:\\my folder\\programming\\IntelliJ_IDEAProjects\\JavaExperiments\\SomeTestUtilProj\\src\\utils\\keys\\AUTH_RSA256_bc714c614b969bfe58dc9dcd38dad6d226e58913.p12", "Mmk0818");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (PrivilegedActionException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (XMLSecurityException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println(signedXml);
        object.verifyXml(signedXml);
        object.verifyXml(singStringForCheck);
    }

    public String signXML(final String container, String password) {

        String result = null;

        try {
            String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                    + "<root>"
                    + "<person id=\"someid\">"
                    + "<name>Стеве Жобс</name>"
                    + "<iin>123456789012</iin>"
                    + "</person>"
                    + "</root>";

            Provider provider = new KalkanProvider();
            Security.addProvider(provider);
//          загружаем конфигурацию либо магической функцией
            KncaXS.loadXMLSecurity();
//            либо многословно так
//            System.setProperty("org.apache.xml.security.resource.config", "/kz/gov/pki/kalkan/xmldsig/pkigovkz.xml");
//            org.apache.xml.security.Init.init();
//            org.apache.xml.security.algorithms.JCEMapper.setProviderId(KalkanProvider.PROVIDER_NAME);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            final Document doc = documentBuilder.parse(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
            final String signMethod;
            final String digestMethod;
            KeyStore store = KeyStore.getInstance("PKCS12", provider.getName());
            InputStream inputStream;
            inputStream = AccessController.doPrivileged(new PrivilegedExceptionAction<FileInputStream>() {
                @Override
                public FileInputStream run() throws Exception {
                    FileInputStream fis = new FileInputStream(container);
                    return fis;
                }
            });
            store.load(inputStream, password.toCharArray());
            Enumeration<String> als = store.aliases();
            String alias = null;
            while (als.hasMoreElements()) {
                alias = als.nextElement();
            }

            final PrivateKey privateKey = (PrivateKey) store.getKey(alias, password.toCharArray());
            final X509Certificate x509Certificate = (X509Certificate) store.getCertificate(alias);
            String sigAlgOid = x509Certificate.getSigAlgOID();
            if (sigAlgOid.equals(PKCSObjectIdentifiers.sha1WithRSAEncryption.getId())) {
                signMethod = Constants.MoreAlgorithmsSpecNS + "rsa-sha1";
                digestMethod = Constants.MoreAlgorithmsSpecNS + "sha1";
            } else if (sigAlgOid.equals(PKCSObjectIdentifiers.sha256WithRSAEncryption.getId())) {
                signMethod = Constants.MoreAlgorithmsSpecNS + "rsa-sha256";
                digestMethod = XMLCipherParameters.SHA256;
            } else {
                signMethod = Constants.MoreAlgorithmsSpecNS + "gost34310-gost34311";
                digestMethod = Constants.MoreAlgorithmsSpecNS + "gost34311";
            }

            XMLSignature sig = new XMLSignature(doc, "", signMethod);

            if (doc.getFirstChild() != null) {
                doc.getFirstChild().appendChild(sig.getElement());
                Transforms transforms = new Transforms(doc);
                transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
                transforms.addTransform(XMLCipherParameters.N14C_XML_CMMNTS);
                sig.addDocument("", transforms, digestMethod);
                sig.addKeyInfo(x509Certificate);
                sig.sign(privateKey);
                StringWriter os = new StringWriter();
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer trans = tf.newTransformer();
                trans.transform(new DOMSource(doc), new StreamResult(os));
                os.close();
                result = os.toString();
            }

            System.err.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String signXML2(String xmlString, final String container, String password)
            throws ParserConfigurationException, SAXException, IOException,
            KeyStoreException, NoSuchAlgorithmException, NoSuchProviderException, CertificateException,
            UnrecoverableKeyException, PrivilegedActionException, AlgorithmAlreadyRegisteredException,ClassNotFoundException, XMLSignatureException,
            XMLSecurityException, TransformerException {

        String result = null;
        //org.apache.xml.security.Init.init ();

        //String password="123456";
        //final String container="C:\\apps\\fmw\\user_projects\\domains\\base_domain\\config\\fmwconfig\\GOSTKNCA.p12";
        boolean exists = false;

        Provider provider = new KalkanProvider();
        Provider[] providers = Security.getProviders();
        for (Provider p : providers) {
            if (p.getName().equals(provider.getName())) {
                exists = true;
            }
        }

        if (!exists) {
            Security.addProvider(provider);
        } else {
            Security.removeProvider(provider.getName());
            Security.addProvider(provider);
        }
        //Load configuration or use magic function
        KncaXS.loadXMLSecurity();
//	            Or write full command
        //System.setProperty("org.apache.xml.security.resource.config", "/kz/gov/pki/kalkan/xmldsig/pkigovkz.xml");
        //org.apache.xml.security.Init.init();
        //org.apache.xml.security.algorithms.JCEMapper.setProviderId(KalkanProvider.PROVIDER_NAME);

        //SignatureAlgorithm.register("http://www.w3.org/2001/04/xmldsig-more#gost34310-gost34311", "kz.gov.pki.kalkan.xmldsig.SignatureGost34310$Gost34310Gost34311");


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document doc = documentBuilder.parse(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));


        String signMethod;
        String digestMethod;
        KeyStore store = KeyStore.getInstance("PKCS12", provider.getName());
        InputStream inputStream;
        inputStream = AccessController.doPrivileged(new PrivilegedExceptionAction<FileInputStream>() {
            @Override
            public FileInputStream run() throws Exception {
                FileInputStream fis = new FileInputStream(container);
                return fis;
            }
        });


        store.load(inputStream, password.toCharArray());
        System.out.println("TESTPREP Load Certificate");

        Enumeration<String> als = store.aliases();
        String alias = null;
        while (als.hasMoreElements()) {
            alias = als.nextElement();
            System.out.println("TESTPREP Alias="+alias);
        }

        final PrivateKey privateKey = (PrivateKey) store.getKey(alias, password.toCharArray());
        final X509Certificate x509Certificate = (X509Certificate) store.getCertificate(alias);
        // System.out.println("x509Certificate="+x509Certificate);

        String sigAlgOid = x509Certificate.getSigAlgOID();
        if (sigAlgOid.equals(PKCSObjectIdentifiers.sha1WithRSAEncryption.getId())) {
            signMethod = Constants.MoreAlgorithmsSpecNS + "rsa-sha1";
            digestMethod = Constants.MoreAlgorithmsSpecNS + "sha1";
        } else if (sigAlgOid.equals(PKCSObjectIdentifiers.sha256WithRSAEncryption.getId())) {
            signMethod = Constants.MoreAlgorithmsSpecNS + "rsa-sha256";
            digestMethod = XMLCipherParameters.SHA256;
        } else {
            signMethod = Constants.MoreAlgorithmsSpecNS + "gost34310-gost34311";
            digestMethod = Constants.MoreAlgorithmsSpecNS + "gost34311";
        }
        System.out.println("TESTPREP sigAlgOid ="+sigAlgOid);

        XMLSignature sig = new XMLSignature(doc, "", signMethod);
        System.out.println("TESTPREP doc ="+doc.toString());

        StringWriter os1 = new StringWriter();
        TransformerFactory tf1 = TransformerFactory.newInstance();
        Transformer trans1 = tf1.newTransformer();
        trans1.transform(new DOMSource(doc), new StreamResult(os1));
        os1.close();

        System.out.println("TESTPREP doc1 ="+os1.toString());


        if (doc.getFirstChild() != null) {
            doc.getFirstChild().appendChild(sig.getElement());
            System.out.println("TESTPREP before transform="+doc.toString());
            Transforms transforms = new Transforms(doc);

            System.out.println("TESTPREP transform created=");
            transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
            System.out.println("TESTPREP Add transform 1=");
            transforms.addTransform(XMLCipherParameters.N14C_XML_CMMNTS);
            System.out.println("TESTPREP Add transform 2=");


            sig.addDocument("", transforms, digestMethod);
            System.out.println("TESTPREP Add document=");

            sig.addKeyInfo(x509Certificate);
            System.out.println("TESTPREP before sign=");
            sig.sign(privateKey);
            System.out.println("TESTPREP after sign=");
            StringWriter os = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.transform(new DOMSource(doc), new StreamResult(os));
            os.close();
            result = os.toString();

        }







        return result;
    }


    public boolean verifyXml(String xmlString) {
        boolean result = false;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document doc = documentBuilder.parse(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));

            Element sigElement = null;
            Element rootEl = (Element) doc.getFirstChild();

            NodeList list = rootEl.getElementsByTagName("ds:Signature");
            int length = list.getLength();
            for (int i = 0; i < length; i++) {
                Node sigNode = list.item(length - 1);
                sigElement = (Element) sigNode;
                if (sigElement == null) {
                    System.err.println("Bad signature: Element 'ds:Reference' is not found in XML document");
                }
                XMLSignature signature = new XMLSignature(sigElement, "");
                KeyInfo ki = signature.getKeyInfo();
                X509Certificate cert = ki.getX509Certificate();
                if (cert != null) {
                    result = signature.checkSignatureValue(cert);
                    rootEl.removeChild(sigElement);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("VERIFICATION RESULT IS: " + result);
        return result;
    }
}

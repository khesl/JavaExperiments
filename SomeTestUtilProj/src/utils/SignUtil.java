import kz.gov.pki.kalkan.jce.provider.KalkanProvider;
import org.apache.xml.security.encryption.XMLCipherParameters;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.TransformationException;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author allzhass
 */
public class SignUtil {

    static String UTF_8_ENCODING = "UTF-8";
    static String ALGORITHM = "ECGOST34310";
    static String DIGEST_METHOD = "gost34311";
    static String SIGNATURE_METHOD = "gost34310-gost34311";
    static String BAD_XML_FORMAT = "XML is not signed. Bad format!";
    static String KEY_STORE_NULL = "Unable to read key storage or storage is empty!";

    static String xmlString = "<edw:request xmlns:edw=\"http://xmlns.kztc-cits/sign\">\n" +
            "  <edw:messageId>07e4b5dd-ca02-4aea-b93d-513c482922b2</edw:messageId>\n" +
            "  <edw:chainId>82770d7b-393a-48be-b840-d60370b60aab</edw:chainId>\n" +
            "  <edw:sendTime>2018-08-27T11:43:46</edw:sendTime>\n" +
            "  <edw:messageType>request</edw:messageType>\n" +
            "  <edw:iinBin>051140008189</edw:iinBin>\n" +
            "</edw:request>";

    public static void main(String[] args) {
        SignUtil object = new SignUtil();
        //String signedXml = object.getMySign(xmlString,"S:\\my folder\\programming\\IntelliJ_IDEAProjects\\JavaExperiments\\SomeTestUtilProj\\src\\utils\\keys\\AUTH_RSA256_bc714c614b969bfe58dc9dcd38dad6d226e58913.p12", "Mmk0818");
        String signedXml = object.getSignedDoc("S:\\my folder\\programming\\IntelliJ_IDEAProjects\\JavaExperiments\\SomeTestUtilProj\\src\\utils\\keys\\AUTH_RSA256_bc714c614b969bfe58dc9dcd38dad6d226e58913.p12", "PKCS12", "Mmk0818", xmlString, "UTF-8");
        object.verifyXml(signedXml);
    }

    public static String getSignedDoc (String storagePath, String keyStoreType, String password, String xmlToSign, String encoding){
        Provider provider = registerKalkanProvider();
        loadXMLSecurity();

        String signedXML = "";
        try {
            signedXML = signXml(storagePath, keyStoreType, password, provider, xmlToSign, encoding);
            System.out.println(signedXML);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (CertificateException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (KeyStoreException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SAXException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (TransformationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (XMLSecurityException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return signedXML;
    }

    public static String getMySign(String xml, String cerPath, String password){
        Provider provider = registerKalkanProvider();
        loadXMLSecurity();

        String keyStoreType = "PKCS12";
        String sign = "";
        try {
            KeyStore store = loadKeyStore(cerPath, keyStoreType, password, provider);
            sign = sign(cerPath, keyStoreType, password, provider, xml);
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        } catch (MarshalException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (XMLSignatureException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (XMLSecurityException e) {
            e.printStackTrace();
        }

        return sign;
    }

    private  static void loadXMLSecurity() {
        System.setProperty("org.apache.xml.security.ignoreLineBreaks", "true");
        System.setProperty("org.apache.xml.security.resource.config", "/kz/gov/pki/kalkan/xmldsig/pkigovkz.xml");
        org.apache.xml.security.Init.init();
        org.apache.xml.security.algorithms.JCEMapper.setProviderId(KalkanProvider.PROVIDER_NAME);
    }

    // Read file
    private static String readFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        }
    }

    // Register KALKAN provider
    private static Provider registerKalkanProvider() {
        Security.addProvider(new KalkanProvider());
        Provider kalkanProvider = Security.getProvider(KalkanProvider.PROVIDER_NAME);
        if (kalkanProvider == null) {
            kalkanProvider = new KalkanProvider();
            Security.addProvider(kalkanProvider);
        }

        if (!(kalkanProvider instanceof KalkanProvider)) {
            Security.removeProvider(KalkanProvider.PROVIDER_NAME);
            Security.addProvider(new KalkanProvider());
        }

        return kalkanProvider;
    }

    private static String sign(String cerPath, String keyStoreType, String password, Provider provider, String xml) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException, UnrecoverableEntryException, MarshalException, ParserConfigurationException, TransformerConfigurationException, SAXException, XMLSignatureException, NoSuchProviderException, XMLSecurityException {
        // Load the KeyStore
        KeyStore keyStore = loadKeyStore(cerPath, keyStoreType, password, provider);
        // Get key alias
        String keyAlias = getKeyAlias(keyStore);
        // Get the signing key and certificate
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(keyAlias, new KeyStore.PasswordProtection(password.toCharArray()));
        X509Certificate cert = (X509Certificate) keyEntry.getCertificate();
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias, password.toCharArray());
//        X500PrivateCredential x500PrivateCredential = new X500PrivateCredential(cert, privateKey);

        // Create a DOM XMLSignatureFactory that will be used to
        // generate the enveloped signature.
        XMLSignatureFactory xmlSignatureFactory = XMLSignatureFactory.getInstance("DOM");
        // Create a Reference to the enveloped document (in this case,
        // you are signing the whole document, so a URI of "" signifies
        // that, and also specify the SHA1 digest algorithm and
        // the ENVELOPED Transform.
        Reference ref = xmlSignatureFactory.newReference
                ("", xmlSignatureFactory.newDigestMethod(getDigestMethod(privateKey), null),
                        Collections.singletonList
                                (xmlSignatureFactory.newTransform
                                        (Transform.ENVELOPED, (TransformParameterSpec) null)),
                        null, null);

        // Create the SignedInfo.
        SignedInfo signatureInfo = xmlSignatureFactory.newSignedInfo
                (xmlSignatureFactory.newCanonicalizationMethod
                                (CanonicalizationMethod.INCLUSIVE,
                                        (C14NMethodParameterSpec) null),
                        xmlSignatureFactory.newSignatureMethod(SIGNATURE_METHOD, null),
                        Collections.singletonList(ref));

        // Create the KeyInfo containing the X509Data.
        KeyInfoFactory kif = xmlSignatureFactory.getKeyInfoFactory();
        List x509Content = new ArrayList<>();
        x509Content.add(cert.getSubjectX500Principal().getName());
        x509Content.add(cert);
        X509Data x509data = kif.newX509Data(x509Content);
        KeyInfo keyInfo = kif.newKeyInfo(Collections.singletonList(x509data));

        return signXML(xmlSignatureFactory, keyEntry, keyInfo, signatureInfo, xml);
    }

    // Load the KeyStore
    private static KeyStore loadKeyStore(String cerPath, String keyStoreType, String password, Provider provider) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException {
        InputStream is = null;
        try {
            is = new FileInputStream(new File(cerPath));
            KeyStore keyStore = KeyStore.getInstance(keyStoreType, provider.getName());

            keyStore.load(is, password.toCharArray());
            return keyStore;
        } finally {
            if (is!=null) {
                is.close();
            }
        }
    }

    // Get key alias
    private static String getKeyAlias(KeyStore keyStore) throws KeyStoreException {

        Enumeration<String> e = keyStore.aliases();
        String keyAlias = null;

        while (e.hasMoreElements()) {
            keyAlias = e.nextElement();
        }
        return keyAlias;
    }

    private static String signXML(XMLSignatureFactory xmlSignatureFactory, KeyStore.PrivateKeyEntry keyEntry, KeyInfo keyInfo, SignedInfo signatureInfo, String xml) throws ParserConfigurationException, IOException, SAXException, MarshalException, XMLSignatureException, TransformerConfigurationException, XMLSecurityException {
        // Instantiate the document to be signed.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(xml);

        // Create a DOMSignContext and specify the RSA PrivateKey and
        // location of the resulting XMLSignature's parent element.
        DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement());

        // Create the XMLSignature, but don't sign it yet.
//        XMLSignature signature = xmlSignatureFactory.newXMLSignature(signatureInfo, keyInfo);

        // Marshal, generate, and sign the enveloped signature.
//        signature.sign(dsc);
        return xmlDocumentToString(doc);
    }

    private static String xmlDocumentToString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, UTF_8_ENCODING);

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }

    private static String getDigestMethod(PrivateKey privateKey){
        if ((privateKey.getAlgorithm().toUpperCase().compareTo(ALGORITHM) == 0)) {
            return Constants.MoreAlgorithmsSpecNS + DIGEST_METHOD;
        } else {
//          digestMethod = MyConstants.MoreAlgorithmsSpecNS + "sha1";
            return  XMLCipherParameters.SHA256;
        }
    }

    private static String getSignatureMethod(PrivateKey privateKey){
        if ((privateKey.getAlgorithm().toUpperCase().compareTo(ALGORITHM) == 0)) {
            return Constants.MoreAlgorithmsSpecNS + SIGNATURE_METHOD;
        } else {
//          signMethod = MyConstants.MoreAlgorithmsSpecNS + "rsa-sha1";
            return Constants.MoreAlgorithmsSpecNS + "rsa-sha256";
        }
    }

    private static Document parseStringToXMLDocument(String xmlString, String encoding) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        return documentBuilder.parse(new ByteArrayInputStream(xmlString.getBytes(encoding)));
    }

    private static String signXml(String storagePath, String keyStoreType, String password, Provider provider, String xmlToSign, String encoding) throws IOException, SAXException, ParserConfigurationException, CertificateException, NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException, UnrecoverableEntryException, XMLSecurityException, TransformerException {

        KeyStore keyStore = null;
        try {
            final Document doc = parseStringToXMLDocument(xmlToSign, encoding);
            doc.normalizeDocument();

            keyStore = loadKeyStore(storagePath, keyStoreType, password, provider);
            if (keyStore == null) {
                throw new RuntimeException(KEY_STORE_NULL);
            }

            String keyAlias = getKeyAlias(keyStore);
            KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(keyAlias, new KeyStore.PasswordProtection(password.toCharArray()));
            X509Certificate cert = (X509Certificate) keyEntry.getCertificate();
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias, password.toCharArray());

            XMLSignature sig = new XMLSignature(doc, "", getSignatureMethod(privateKey));
            String result = null;
            if (doc.getFirstChild() != null) {
                doc.getFirstChild().appendChild(sig.getElement());
                Transforms transforms = new Transforms(doc);
                transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
                transforms.addTransform(Transforms.TRANSFORM_C14N_OMIT_COMMENTS);
//                transforms.addTransform(XMLCipherParameters.N14C_XML_CMMNTS);
                sig.addDocument("", transforms, getDigestMethod(privateKey));
                sig.addKeyInfo(cert);
                sig.sign(privateKey);
                StringWriter os = new StringWriter();
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer trans = tf.newTransformer();
                trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                trans.transform(new DOMSource(doc), new StreamResult(os));
                os.close();
                result = os.toString();
            }

            if (result == null) {
                throw new RuntimeException(BAD_XML_FORMAT);
            }
            return result;
        } finally {
            keyStore = null;
        }
    }

    public static boolean verifyXml(String xmlString) {
        boolean signatureValue = false;
        boolean verifySignedInfo = false;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlString));
            Document doc = documentBuilder.parse(is);

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
                doc.normalizeDocument();//TODO: added
                XMLSignature signature = new XMLSignature(sigElement, "");
                org.apache.xml.security.keys.KeyInfo ki = signature.getKeyInfo();
                X509Certificate cert = ki.getX509Certificate();
                if (cert != null) {
                    signatureValue = signature.checkSignatureValue(cert);
                    verifySignedInfo = signature.getSignedInfo().verify();
                    rootEl.removeChild(sigElement);
                    System.out.println("verifySignedInfo = " + verifySignedInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.err.println("VERIFICATION RESULT IS: " + signatureValue);
        return signatureValue;
    }

    //-----------------------------------------------------
}

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import kz.gov.pki.kalkan.asn1.pkcs.PKCSObjectIdentifiers;
import kz.gov.pki.kalkan.util.encoders.Base64;

import org.apache.xml.security.encryption.XMLCipherParameters;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

public class Signature_checker {

    public static void main(String[] args) {


    }

    /*public Result verifyXmlString(String xml, String encoding) throws EdsException {
        logger.info("verifyXmlString() - start: encoding = {}; xml = {}", encoding, xml);

        boolean errors = false;
        Algorithm algorithm = null;
        Document doc = loadXmlString(xml, encoding);
        logger.info("verifyXmlString: doc = {}", doc);
        if (doc.getFirstChild() == null)
            ExceptionUtil.throwEdsException(logger,
                    new EdsException("the input xml document did not has a child node", EdsExceptionCode.xml_child_empty),
                    "verifyXmlString");

        NodeList list = doc.getElementsByTagNameNS(javax.xml.crypto.dsig.XMLSignature.XMLNS, SIGNATURE_NODE_NAME);
        int length = list.getLength();
        List<Node> nodes = new ArrayList<>();
        if (length == 0) {
            logger.info("verifyXmlString() - getElementsByTagName return 0 items, try find over xml scan");
            getElementsByTagName(nodes, doc.getChildNodes(), SIGNATURE_NODE_NAME);
        } else {
            for (int i = 0; i < length; i++) {
                nodes.add(list.item(i));
            }
        }

        if (nodes.size() == 0) {
            logger.info("verifyXmlString() - nodes.size = 0");
            ExceptionUtil.throwEdsException(logger,
                    new EdsException("the input xml document did not has a digital signature",
                            EdsExceptionCode.xml_digisign_notfound),
                    "verifyXmlString");
        }
        try {
            for (Node node : nodes) {
                XMLSignature signature = new XMLSignature((Element)node, "");
                logger.info("verifyXmlString() - 'new XMLSignature' completed: {}", signature);
                KeyInfo ki = signature.getKeyInfo();
                logger.info("verifyXmlString() - 'signature.getKeyInfo()' completed : {}", ki);
                X509Certificate certKey = ki.getX509Certificate();
                logger.info("verifyXmlString() - 'ki.getX509Certificate()' completed : {}");

                //TODO: Do you want to do this?
                //                node.getParentNode().removeChild(node);
                //TODO: end

                if (certKey != null) {
                    errors |= !signature.checkSignatureValue(certKey);
                    if (errors) {
                        logger.info("verifyXmlString() - checkSignatureValue on X509Certificate return failed");
                        break;
                    }
                } else {
                    PublicKey pk = ki.getPublicKey();
                    if (pk != null) {
                        errors |= !signature.checkSignatureValue(pk);
                        if (errors) {
                            logger.info("verifyXmlString() - checkSignatureValue on PublicKey return failed");
                            break;
                        }
                    } else {
                        logger.info("verifyXmlString() - cann't obtain information about public key. Verification is impossible.");
                        errors |= true;
                        break;
                    }
                }

                // check that the certificate is valid at the moment
                certKey.checkValidity(new Date());
                algorithm = Algorithm.fromString(certKey.getPublicKey().getAlgorithm());
                if (algorithm == null)
                    ExceptionUtil.throwEdsException(logger,
                            new EdsException("the signature is badly formatted", EdsExceptionCode.unsupported_algorithm),
                            "verifyXmlString");
                Map<Algorithm, List<X509Certificate>> rootCertificates = keyStoreService.getRootCertificates();

                // verify the root trust of a ceritifcate
                verifyRootCertificate(certKey, rootCertificates.get(algorithm));

                // is certificate revoked?
                if (isCertificateRevokedByCrl(certKey)) {
                    errors |= true;
                    break;
                }
            }
        } catch (XMLSignatureException ex) {
            ExceptionUtil.throwEdsException(logger,
                    new EdsException("the signature is badly formatted", ex, EdsExceptionCode.xml_signature_error),
                    "verifyXmlString");
        } catch (XMLSecurityException ex) {
            ExceptionUtil.throwEdsException(logger,
                    new EdsException("error craeting XMLSignature", ex, EdsExceptionCode.xml_signature_error),
                    "verifyXmlString");

        } catch (CertificateNotYetValidException ex) {
            ExceptionUtil.throwEdsException(logger,
                    new EdsException("the certificate is not yet valid with respect to the date supplied",
                            ex, EdsExceptionCode.certificate_date_error),
                    "verifyXmlString");
        } catch (CertificateExpiredException ex) {
            ExceptionUtil.throwEdsException(logger,
                    new EdsException("the certificate has expired with respect to the date supplied",
                            ex, EdsExceptionCode.certificate_date_error),
                    "verifyXmlString");

        } catch (RuntimeException ex) {
            ExceptionUtil.processRuntimeException(ex, "verifyXmlString", logger);
        }

        Result result = new Result();
        result.status = !errors;
        result.algorithm = algorithm;
        logger.info("verifyXmlString() - end: result = {}", result);
        return result;
    }*/

}

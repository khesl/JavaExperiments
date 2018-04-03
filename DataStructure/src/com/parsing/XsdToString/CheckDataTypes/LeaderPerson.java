package com.parsing.XsdToString.CheckDataTypes;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LeaderPerson complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="LeaderPerson">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FIO" type="{http://srv-osb.acb.kz/Services/}FIO"/>
 *         &lt;element name="Document" type="{http://srv-osb.acb.kz/Services/}Document"/>
 *         &lt;element name="IIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LeaderPerson", propOrder = { "fio", "document" })
public class LeaderPerson {

    @XmlElement(name = "FIO", required = true)
    protected FIO fio;
    @XmlElement(name = "Document", required = true)
    protected Document document;
    @XmlElement(name = "IIN", required = true)
    protected String iin;

    /**
     * Gets the value of the fio property.
     *
     * @return
     *     possible object is
     *     {@link FIO }
     *
     */
    public FIO getFIO() {
        return fio;
    }

    /**
     * Sets the value of the fio property.
     *
     * @param value
     *     allowed object is
     *     {@link FIO }
     *
     */
    public void setFIO(FIO value) {
        this.fio = value;
    }

    /**
     * Gets the value of the document property.
     *
     * @return
     *     possible object is
     *     {@link Document }
     *
     */
    public Document getDocument() {
        return document;
    }

    /**
     * Sets the value of the document property.
     *
     * @param value
     *     allowed object is
     *     {@link Document }
     *
     */
    public void setDocument(Document value) {
        this.document = value;
    }
    
    /**
     * Gets the value of the iin property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIIN() {
        return iin;
    }

    /**
     * Sets the value of the iin property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIIN(String value) {
        this.iin = value;
    }

}

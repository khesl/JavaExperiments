package com.parsing.XsdToString.CheckDataTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Person complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Person">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FIO" type="{http://srv-osb.acb.kz/Services/}FIO"/>
 *         &lt;element name="Document" type="{http://srv-osb.acb.kz/Services/}Document"/>
 *         &lt;element name="IIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Gender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="National" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Citizenship" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BurnDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Address" type="{http://srv-osb.acb.kz/Services/}Address"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Person",
         propOrder = { "fio", "document", "iin", "gender", "national",
                       "citizenship", "burnDate", "address" })
public class Person {

    @XmlElement(name = "FIO", required = true)
    protected FIO fio;
    @XmlElement(name = "Document", required = true)
    protected Document document;
    @XmlElement(name = "IIN", required = true)
    protected String iin;
    @XmlElement(name = "Gender", required = true)
    protected String gender;
    @XmlElement(name = "National", required = true)
    protected String national;
    @XmlElement(name = "Citizenship", required = true)
    protected String citizenship;
    @XmlElement(name = "BurnDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar burnDate;
    @XmlElement(name = "Address", required = true)
    protected Address address;

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

    /**
     * Gets the value of the gender property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the national property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNational() {
        return national;
    }

    /**
     * Sets the value of the national property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNational(String value) {
        this.national = value;
    }

    /**
     * Gets the value of the citizenship property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCitizenship() {
        return citizenship;
    }

    /**
     * Sets the value of the citizenship property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCitizenship(String value) {
        this.citizenship = value;
    }

    /**
     * Gets the value of the burnDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getBurnDate() {
        return burnDate;
    }

    /**
     * Sets the value of the burnDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setBurnDate(XMLGregorianCalendar value) {
        this.burnDate = value;
    }

    /**
     * Gets the value of the address property.
     *
     * @return
     *     possible object is
     *     {@link Address }
     *
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     *
     * @param value
     *     allowed object is
     *     {@link Address }
     *
     */
    public void setAddress(Address value) {
        this.address = value;
    }

}

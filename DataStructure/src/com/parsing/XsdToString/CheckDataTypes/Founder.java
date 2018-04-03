package com.parsing.XsdToString.CheckDataTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Founder complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Founder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IINBIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FounderFIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FounderPercent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FounderBurnDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="FounderBurnPlace" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Address" type="{http://srv-osb.acb.kz/Services/}Address"/>
 *         &lt;element name="Legal" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Founder",
         propOrder = { "iinbin", "founderFIO", "founderPercent",
                       "founderBurnDate", "founderBurnPlace", "address",
                       "legal" })
public class Founder {

    @XmlElement(name = "IINBIN", required = true)
    protected String iinbin;
    @XmlElement(name = "FounderFIO", required = true)
    protected String founderFIO;
    @XmlElement(name = "FounderPercent", required = true)
    protected String founderPercent;
    @XmlElement(name = "FounderBurnDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar founderBurnDate;
    @XmlElement(name = "FounderBurnPlace", required = true)
    protected String founderBurnPlace;
    @XmlElement(name = "Address", required = true)
    protected Address address;
    @XmlElement(name = "Legal")
    protected boolean legal;

    /**
     * Gets the value of the iinbin property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIINBIN() {
        return iinbin;
    }

    /**
     * Sets the value of the iinbin property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIINBIN(String value) {
        this.iinbin = value;
    }

    /**
     * Gets the value of the founderFIO property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFounderFIO() {
        return founderFIO;
    }

    /**
     * Sets the value of the founderFIO property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFounderFIO(String value) {
        this.founderFIO = value;
    }

    /**
     * Gets the value of the founderPercent property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFounderPercent() {
        return founderPercent;
    }

    /**
     * Sets the value of the founderPercent property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFounderPercent(String value) {
        this.founderPercent = value;
    }

    /**
     * Gets the value of the founderBurnDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFounderBurnDate() {
        return founderBurnDate;
    }

    /**
     * Sets the value of the founderBurnDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setFounderBurnDate(XMLGregorianCalendar value) {
        this.founderBurnDate = value;
    }

    /**
     * Gets the value of the founderBurnPlace property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFounderBurnPlace() {
        return founderBurnPlace;
    }

    /**
     * Sets the value of the founderBurnPlace property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFounderBurnPlace(String value) {
        this.founderBurnPlace = value;
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

    /**
     * Gets the value of the legal property.
     *
     */
    public boolean isLegal() {
        return legal;
    }

    /**
     * Sets the value of the legal property.
     *
     */
    public void setLegal(boolean value) {
        this.legal = value;
    }

}

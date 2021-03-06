package com.parsing.XsdToString.CheckDataTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for DataResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StatusResponse" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Reason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OrderAmound" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumContract" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataResponse",
         propOrder = { "statusResponse", "reason", "orderAmound",
                       "numContract" })
public class DataResponse {

    @XmlElement(name = "StatusResponse")
    protected boolean statusResponse;
    @XmlElement(name = "Reason", required = true)
    protected String reason;
    @XmlElement(name = "OrderAmound", required = true)
    protected String orderAmound;
    @XmlElement(name = "NumContract", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar numContract;

    /**
     * Gets the value of the statusResponse property.
     *
     */
    public boolean isStatusResponse() {
        return statusResponse;
    }

    /**
     * Sets the value of the statusResponse property.
     *
     */
    public void setStatusResponse(boolean value) {
        this.statusResponse = value;
    }

    /**
     * Gets the value of the reason property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the orderAmound property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOrderAmound() {
        return orderAmound;
    }

    /**
     * Sets the value of the orderAmound property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOrderAmound(String value) {
        this.orderAmound = value;
    }

    /**
     * Gets the value of the numContract property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getNumContract() {
        return numContract;
    }

    /**
     * Sets the value of the numContract property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setNumContract(XMLGregorianCalendar value) {
        this.numContract = value;
    }

}

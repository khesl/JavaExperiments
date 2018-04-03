package com.parsing.XsdToString.CheckDataTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pepID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DataResponse" type="{http://srv-osb.acb.kz/Services/}DataResponse"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ErrorCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ErrorMsg" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "pepID", "dataResponse" })
@XmlRootElement(name = "CheckResponse")
public class CheckResponse {

    @XmlElement(required = true)
    protected String pepID;
    @XmlElement(name = "DataResponse", required = true)
    protected DataResponse dataResponse;
    @XmlAttribute(name = "ErrorCode", required = true)
    protected String errorCode;
    @XmlAttribute(name = "ErrorMsg")
    protected String errorMsg;

    /**
     * Gets the value of the pepID property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPepID() {
        return pepID;
    }

    /**
     * Sets the value of the pepID property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPepID(String value) {
        this.pepID = value;
    }

    /**
     * Gets the value of the dataResponse property.
     *
     * @return
     *     possible object is
     *     {@link DataResponse }
     *
     */
    public DataResponse getDataResponse() {
        return dataResponse;
    }

    /**
     * Sets the value of the dataResponse property.
     *
     * @param value
     *     allowed object is
     *     {@link DataResponse }
     *
     */
    public void setDataResponse(DataResponse value) {
        this.dataResponse = value;
    }

    /**
     * Gets the value of the errorCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the errorMsg property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Sets the value of the errorMsg property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setErrorMsg(String value) {
        this.errorMsg = value;
    }

}

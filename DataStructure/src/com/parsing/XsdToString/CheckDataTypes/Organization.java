package com.parsing.XsdToString.CheckDataTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Organization complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Organization">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrganizationNameRu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="registeringDepartment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OKED" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Organization",
         propOrder = { "organizationNameRu", "bin", "registeringDepartment",
                       "oked" })
public class Organization {

    @XmlElement(name = "OrganizationNameRu", required = true)
    protected String organizationNameRu;
    @XmlElement(name = "BIN", required = true)
    protected String bin;
    @XmlElement(name = "RegisteringDepartment", required = true)
    protected String registeringDepartment;
    @XmlElement(name = "OKED", required = true)
    protected String oked;

    /**
     * Gets the value of the organizationNameRu property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOrganizationNameRu() {
        return organizationNameRu;
    }

    /**
     * Sets the value of the organizationNameRu property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOrganizationNameRu(String value) {
        this.organizationNameRu = value;
    }

    /**
     * Gets the value of the bin property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBIN() {
        return bin;
    }

    /**
     * Sets the value of the bin property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBIN(String value) {
        this.bin = value;
    }

    /**
     * Gets the value of the registeringDepartment property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRegisteringDepartment() {
        return registeringDepartment;
    }

    /**
     * Sets the value of the registeringDepartment property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRegisteringDepartment(String value) {
        this.registeringDepartment = value;
    }

    /**
     * Gets the value of the oked property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOKED() {
        return oked;
    }

    /**
     * Sets the value of the oked property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOKED(String value) {
        this.oked = value;
    }

}

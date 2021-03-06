package com.parsing.XsdToString.CheckDataTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Address complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Address">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ZIPCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DistrictRu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RegionRu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CityRu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StreetRu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BuildingNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CorpusNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AppartmentNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address",
         propOrder = { "country", "zipCode", "districtRu", "regionRu",
                       "cityRu", "streetRu", "buildingNumber", "corpusNumber",
                       "appartmentNumber" })
public class Address {

    @XmlElement(name = "Country", required = true)
    protected String country;
    @XmlElement(name = "ZIPCode", required = true)
    protected String zipCode;
    @XmlElement(name = "DistrictRu", required = true)
    protected String districtRu;
    @XmlElement(name = "RegionRu", required = true)
    protected String regionRu;
    @XmlElement(name = "CityRu", required = true)
    protected String cityRu;
    @XmlElement(name = "StreetRu", required = true)
    protected String streetRu;
    @XmlElement(name = "BuildingNumber", required = true)
    protected String buildingNumber;
    @XmlElement(name = "CorpusNumber", required = true)
    protected String corpusNumber;
    @XmlElement(name = "AppartmentNumber", required = true)
    protected String appartmentNumber;

    /**
     * Gets the value of the country property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the zipCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getZIPCode() {
        return zipCode;
    }

    /**
     * Sets the value of the zipCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setZIPCode(String value) {
        this.zipCode = value;
    }

    /**
     * Gets the value of the districtRu property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDistrictRu() {
        return districtRu;
    }

    /**
     * Sets the value of the districtRu property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDistrictRu(String value) {
        this.districtRu = value;
    }

    /**
     * Gets the value of the regionRu property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRegionRu() {
        return regionRu;
    }

    /**
     * Sets the value of the regionRu property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRegionRu(String value) {
        this.regionRu = value;
    }

    /**
     * Gets the value of the cityRu property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCityRu() {
        return cityRu;
    }

    /**
     * Sets the value of the cityRu property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCityRu(String value) {
        this.cityRu = value;
    }

    /**
     * Gets the value of the streetRu property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStreetRu() {
        return streetRu;
    }

    /**
     * Sets the value of the streetRu property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStreetRu(String value) {
        this.streetRu = value;
    }

    /**
     * Gets the value of the buildingNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * Sets the value of the buildingNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBuildingNumber(String value) {
        this.buildingNumber = value;
    }

    /**
     * Gets the value of the corpusNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCorpusNumber() {
        return corpusNumber;
    }

    /**
     * Sets the value of the corpusNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCorpusNumber(String value) {
        this.corpusNumber = value;
    }

    /**
     * Gets the value of the appartmentNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAppartmentNumber() {
        return appartmentNumber;
    }

    /**
     * Sets the value of the appartmentNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAppartmentNumber(String value) {
        this.appartmentNumber = value;
    }

}

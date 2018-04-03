package com.parsing.XsdToString.CheckDataTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="Filial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Statuses" type="{http://srv-osb.acb.kz/Services/}Statuses"/>
 *         &lt;element name="NameRu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IINBIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OrgForm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FormOfLaw" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CommerceOrg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TypicalCharter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Ownership" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EnterpriseSubj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PrivateEnterpriseType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ForeignInvest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsRezident" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncCountry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RegDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HeadOrgBin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeSources" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Founders" type="{http://srv-osb.acb.kz/Services/}Founders"/>
 *         &lt;element name="TypeDoerAgency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DoerAgency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NameAgency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Beneficiary" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SignPerson" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Persons" type="{http://srv-osb.acb.kz/Services/}Persons"/>
 *         &lt;element name="Organizations" type="{http://srv-osb.acb.kz/Services/}Organizations"/>
 *         &lt;element name="LeaderPersons" type="{http://srv-osb.acb.kz/Services/}LeaderPersons"/>
 *         &lt;element name="Addresses" type="{http://srv-osb.acb.kz/Services/}Addresses"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
         propOrder = { "filial", "currency", "statuses", "nameRu", "iinbin",
                       "orgForm", "formOfLaw", "commerceOrg", "typicalCharter",
                       "ownership", "enterpriseSubj", "privateEnterpriseType",
                       "foreignInvest", "isRezident", "incCountry", "regDate",
                       "headOrgBin", "incomeSources", "founders",
                       "typeDoerAgency", "doerAgency", "nameAgency",
                       "beneficiary", "signPerson", "persons", "organizations",
                       "leaderPersons", "addresses" })
@XmlRootElement(name = "CheckRequest")
public class CheckRequest {

    @XmlElement(name = "Filial", required = true)
    protected String filial;
    @XmlElement(name = "Currency", required = true)
    protected String currency;
    @XmlElement(name = "Statuses", required = true)
    protected Statuses statuses;
    @XmlElement(name = "NameRu", required = true)
    protected String nameRu;
    @XmlElement(name = "IINBIN", required = true)
    protected String iinbin;
    @XmlElement(name = "OrgForm", required = true)
    protected String orgForm;
    @XmlElement(name = "FormOfLaw", required = true)
    protected String formOfLaw;
    @XmlElement(name = "CommerceOrg", required = true)
    protected String commerceOrg;
    @XmlElement(name = "TypicalCharter", required = true)
    protected String typicalCharter;
    @XmlElement(name = "Ownership", required = true)
    protected String ownership;
    @XmlElement(name = "EnterpriseSubj", required = true)
    protected String enterpriseSubj;
    @XmlElement(name = "PrivateEnterpriseType", required = true)
    protected String privateEnterpriseType;
    @XmlElement(name = "ForeignInvest", required = true)
    protected String foreignInvest;
    @XmlElement(name = "IsRezident", required = true)
    protected String isRezident;
    @XmlElement(name = "IncCountry", required = true)
    protected String incCountry;
    @XmlElement(name = "RegDate", required = true)
    protected String regDate;
    @XmlElement(name = "HeadOrgBin", required = true)
    protected String headOrgBin;
    @XmlElement(name = "IncomeSources", required = true)
    protected String incomeSources;
    @XmlElement(name = "Founders", required = true)
    protected Founders founders;
    @XmlElement(name = "TypeDoerAgency", required = true)
    protected String typeDoerAgency;
    @XmlElement(name = "DoerAgency", required = true)
    protected String doerAgency;
    @XmlElement(name = "NameAgency", required = true)
    protected String nameAgency;
    @XmlElement(name = "Beneficiary", required = true)
    protected String beneficiary;
    @XmlElement(name = "SignPerson", required = true)
    protected String signPerson;
    @XmlElement(name = "Persons", required = true)
    protected Persons persons;
    @XmlElement(name = "Organizations", required = true)
    protected Organizations organizations;
    @XmlElement(name = "LeaderPersons", required = true)
    protected LeaderPersons leaderPersons;
    @XmlElement(name = "Addresses", required = true)
    protected Addresses addresses;

    /**
     * Gets the value of the filial property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFilial() {
        return filial;
    }

    /**
     * Sets the value of the filial property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFilial(String value) {
        this.filial = value;
    }

    /**
     * Gets the value of the currency property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the statuses property.
     *
     * @return
     *     possible object is
     *     {@link Statuses }
     *
     */
    public Statuses getStatuses() {
        return statuses;
    }

    /**
     * Sets the value of the statuses property.
     *
     * @param value
     *     allowed object is
     *     {@link Statuses }
     *
     */
    public void setStatuses(Statuses value) {
        this.statuses = value;
    }

    /**
     * Gets the value of the nameRu property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNameRu() {
        return nameRu;
    }

    /**
     * Sets the value of the nameRu property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNameRu(String value) {
        this.nameRu = value;
    }

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
     * Gets the value of the orgForm property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOrgForm() {
        return orgForm;
    }

    /**
     * Sets the value of the orgForm property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOrgForm(String value) {
        this.orgForm = value;
    }

    /**
     * Gets the value of the formOfLaw property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFormOfLaw() {
        return formOfLaw;
    }

    /**
     * Sets the value of the formOfLaw property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFormOfLaw(String value) {
        this.formOfLaw = value;
    }

    /**
     * Gets the value of the commerceOrg property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCommerceOrg() {
        return commerceOrg;
    }

    /**
     * Sets the value of the commerceOrg property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCommerceOrg(String value) {
        this.commerceOrg = value;
    }

    /**
     * Gets the value of the typicalCharter property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTypicalCharter() {
        return typicalCharter;
    }

    /**
     * Sets the value of the typicalCharter property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTypicalCharter(String value) {
        this.typicalCharter = value;
    }

    /**
     * Gets the value of the ownership property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOwnership() {
        return ownership;
    }

    /**
     * Sets the value of the ownership property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOwnership(String value) {
        this.ownership = value;
    }

    /**
     * Gets the value of the enterpriseSubj property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEnterpriseSubj() {
        return enterpriseSubj;
    }

    /**
     * Sets the value of the enterpriseSubj property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEnterpriseSubj(String value) {
        this.enterpriseSubj = value;
    }

    /**
     * Gets the value of the privateEnterpriseType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPrivateEnterpriseType() {
        return privateEnterpriseType;
    }

    /**
     * Sets the value of the privateEnterpriseType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPrivateEnterpriseType(String value) {
        this.privateEnterpriseType = value;
    }

    /**
     * Gets the value of the foreignInvest property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getForeignInvest() {
        return foreignInvest;
    }

    /**
     * Sets the value of the foreignInvest property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setForeignInvest(String value) {
        this.foreignInvest = value;
    }

    /**
     * Gets the value of the isRezident property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIsRezident() {
        return isRezident;
    }

    /**
     * Sets the value of the isRezident property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIsRezident(String value) {
        this.isRezident = value;
    }

    /**
     * Gets the value of the incCountry property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIncCountry() {
        return incCountry;
    }

    /**
     * Sets the value of the incCountry property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIncCountry(String value) {
        this.incCountry = value;
    }

    /**
     * Gets the value of the regDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRegDate() {
        return regDate;
    }

    /**
     * Sets the value of the regDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRegDate(String value) {
        this.regDate = value;
    }

    /**
     * Gets the value of the headOrgBin property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getHeadOrgBin() {
        return headOrgBin;
    }

    /**
     * Sets the value of the headOrgBin property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setHeadOrgBin(String value) {
        this.headOrgBin = value;
    }

    /**
     * Gets the value of the incomeSources property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIncomeSources() {
        return incomeSources;
    }

    /**
     * Sets the value of the incomeSources property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIncomeSources(String value) {
        this.incomeSources = value;
    }

    /**
     * Gets the value of the founders property.
     *
     * @return
     *     possible object is
     *     {@link Founders }
     *
     */
    public Founders getFounders() {
        return founders;
    }

    /**
     * Sets the value of the founders property.
     *
     * @param value
     *     allowed object is
     *     {@link Founders }
     *
     */
    public void setFounders(Founders value) {
        this.founders = value;
    }

    /**
     * Gets the value of the typeDoerAgency property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTypeDoerAgency() {
        return typeDoerAgency;
    }

    /**
     * Sets the value of the typeDoerAgency property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTypeDoerAgency(String value) {
        this.typeDoerAgency = value;
    }

    /**
     * Gets the value of the doerAgency property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDoerAgency() {
        return doerAgency;
    }

    /**
     * Sets the value of the doerAgency property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDoerAgency(String value) {
        this.doerAgency = value;
    }

    /**
     * Gets the value of the nameAgency property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNameAgency() {
        return nameAgency;
    }

    /**
     * Sets the value of the nameAgency property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNameAgency(String value) {
        this.nameAgency = value;
    }

    /**
     * Gets the value of the beneficiary property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBeneficiary() {
        return beneficiary;
    }

    /**
     * Sets the value of the beneficiary property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBeneficiary(String value) {
        this.beneficiary = value;
    }

    /**
     * Gets the value of the signPerson property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSignPerson() {
        return signPerson;
    }

    /**
     * Sets the value of the signPerson property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSignPerson(String value) {
        this.signPerson = value;
    }

    /**
     * Gets the value of the persons property.
     *
     * @return
     *     possible object is
     *     {@link Persons }
     *
     */
    public Persons getPersons() {
        return persons;
    }

    /**
     * Sets the value of the persons property.
     *
     * @param value
     *     allowed object is
     *     {@link Persons }
     *
     */
    public void setPersons(Persons value) {
        this.persons = value;
    }

    /**
     * Gets the value of the organizations property.
     *
     * @return
     *     possible object is
     *     {@link Organizations }
     *
     */
    public Organizations getOrganizations() {
        return organizations;
    }

    /**
     * Sets the value of the organizations property.
     *
     * @param value
     *     allowed object is
     *     {@link Organizations }
     *
     */
    public void setOrganizations(Organizations value) {
        this.organizations = value;
    }

    /**
     * Gets the value of the leaderPersons property.
     *
     * @return
     *     possible object is
     *     {@link LeaderPersons }
     *
     */
    public LeaderPersons getLeaderPersons() {
        return leaderPersons;
    }

    /**
     * Sets the value of the leaderPersons property.
     *
     * @param value
     *     allowed object is
     *     {@link LeaderPersons }
     *
     */
    public void setLeaderPersons(LeaderPersons value) {
        this.leaderPersons = value;
    }

    /**
     * Gets the value of the addresses property.
     *
     * @return
     *     possible object is
     *     {@link Addresses }
     *
     */
    public Addresses getAddresses() {
        return addresses;
    }

    /**
     * Sets the value of the addresses property.
     *
     * @param value
     *     allowed object is
     *     {@link Addresses }
     *
     */
    public void setAddresses(Addresses value) {
        this.addresses = value;
    }

}

package com.parsing.XsdToString.CheckDataTypes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LeaderPersons complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="LeaderPersons">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element name="LeaderPerson" type="{http://srv-osb.acb.kz/Services/}LeaderPerson"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LeaderPersons", propOrder = { "leaderPerson" })
public class LeaderPersons {

    @XmlElement(name = "LeaderPerson", required = true)
    protected List<LeaderPerson> leaderPerson;

    /**
     * Gets the value of the leaderPerson property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the leaderPerson property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLeaderPerson().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LeaderPerson }
     *
     *
     */
    public List<LeaderPerson> getLeaderPerson() {
        if (leaderPerson == null) {
            leaderPerson = new ArrayList<LeaderPerson>();
        }
        return this.leaderPerson;
    }

}

package com.parsing.XsdToString.CheckDataTypes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Founders complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Founders">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element name="Founder" type="{http://srv-osb.acb.kz/Services/}Founder"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Founders", propOrder = { "founder" })
public class Founders {

    @XmlElement(name = "Founder", required = true)
    protected List<Founder> founder;

    /**
     * Gets the value of the founder property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the founder property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFounder().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Founder }
     *
     *
     */
    public List<Founder> getFounder() {
        if (founder == null) {
            founder = new ArrayList<Founder>();
        }
        return this.founder;
    }

}

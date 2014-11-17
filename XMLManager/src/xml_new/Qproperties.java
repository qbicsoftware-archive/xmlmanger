//
// This file was xml_new by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.14 at 04:51:46 PM CET 
//


package xml_new;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{}qfactors" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}qproperty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "qfactors",
    "qproperty"
})
@XmlRootElement(name = "qproperties")
public class Qproperties {

    protected List<Qfactors> qfactors;
    protected List<Qproperty> qproperty;

    /**
     * Gets the value of the qfactors property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qfactors property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQfactors().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Qfactors }
     * 
     * 
     */
    public List<Qfactors> getQfactors() {
        if (qfactors == null) {
            qfactors = new ArrayList<Qfactors>();
        }
        return this.qfactors;
    }

    /**
     * Gets the value of the qproperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qproperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQproperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Qproperty }
     * 
     * 
     */
    public List<Qproperty> getQproperty() {
        if (qproperty == null) {
            qproperty = new ArrayList<Qproperty>();
        }
        return this.qproperty;
    }

}

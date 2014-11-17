//
// This file was xml_new by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.14 at 04:51:46 PM CET 
//


package xml_new;

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
 *       &lt;choice>
 *         &lt;element ref="{}qcategorical"/>
 *         &lt;element ref="{}qcontinous"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "qcategorical",
    "qcontinous"
})
@XmlRootElement(name = "qfactors")
public class Qfactors {

    protected Qcategorical qcategorical;
    protected Qcontinous qcontinous;

    /**
     * Gets the value of the qcategorical property.
     * 
     * @return
     *     possible object is
     *     {@link Qcategorical }
     *     
     */
    public Qcategorical getQcategorical() {
        return qcategorical;
    }

    /**
     * Sets the value of the qcategorical property.
     * 
     * @param value
     *     allowed object is
     *     {@link Qcategorical }
     *     
     */
    public void setQcategorical(Qcategorical value) {
        this.qcategorical = value;
    }

    /**
     * Gets the value of the qcontinous property.
     * 
     * @return
     *     possible object is
     *     {@link Qcontinous }
     *     
     */
    public Qcontinous getQcontinous() {
        return qcontinous;
    }

    /**
     * Sets the value of the qcontinous property.
     * 
     * @param value
     *     allowed object is
     *     {@link Qcontinous }
     *     
     */
    public void setQcontinous(Qcontinous value) {
        this.qcontinous = value;
    }

}

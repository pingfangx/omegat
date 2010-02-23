//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.22 at 05:32:35 PM EET 
//


package gen.core.tbx;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{}termComp"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{}termNote"/>
 *           &lt;element ref="{}termNoteGrp"/>
 *         &lt;/choice>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{}admin"/>
 *           &lt;element ref="{}adminGrp"/>
 *           &lt;element ref="{}transacGrp"/>
 *           &lt;element ref="{}note"/>
 *           &lt;element ref="{}ref"/>
 *           &lt;element ref="{}xref"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "termComp",
    "termNoteOrTermNoteGrp",
    "adminOrAdminGrpOrTransacGrp"
})
@XmlRootElement(name = "termCompGrp")
public class TermCompGrp {

    @XmlElement(required = true)
    protected TermComp termComp;
    @XmlElements({
        @XmlElement(name = "termNoteGrp", type = TermNoteGrp.class),
        @XmlElement(name = "termNote", type = TermNote.class)
    })
    protected List<Object> termNoteOrTermNoteGrp;
    @XmlElements({
        @XmlElement(name = "admin", type = Admin.class),
        @XmlElement(name = "note", type = Note.class),
        @XmlElement(name = "transacGrp", type = TransacGrp.class),
        @XmlElement(name = "xref", type = Xref.class),
        @XmlElement(name = "ref", type = Ref.class),
        @XmlElement(name = "adminGrp", type = AdminGrp.class)
    })
    protected List<Object> adminOrAdminGrpOrTransacGrp;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the termComp property.
     * 
     * @return
     *     possible object is
     *     {@link TermComp }
     *     
     */
    public TermComp getTermComp() {
        return termComp;
    }

    /**
     * Sets the value of the termComp property.
     * 
     * @param value
     *     allowed object is
     *     {@link TermComp }
     *     
     */
    public void setTermComp(TermComp value) {
        this.termComp = value;
    }

    /**
     * Gets the value of the termNoteOrTermNoteGrp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the termNoteOrTermNoteGrp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTermNoteOrTermNoteGrp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TermNoteGrp }
     * {@link TermNote }
     * 
     * 
     */
    public List<Object> getTermNoteOrTermNoteGrp() {
        if (termNoteOrTermNoteGrp == null) {
            termNoteOrTermNoteGrp = new ArrayList<Object>();
        }
        return this.termNoteOrTermNoteGrp;
    }

    /**
     * Gets the value of the adminOrAdminGrpOrTransacGrp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adminOrAdminGrpOrTransacGrp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdminOrAdminGrpOrTransacGrp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Admin }
     * {@link Note }
     * {@link TransacGrp }
     * {@link Xref }
     * {@link Ref }
     * {@link AdminGrp }
     * 
     * 
     */
    public List<Object> getAdminOrAdminGrpOrTransacGrp() {
        if (adminOrAdminGrpOrTransacGrp == null) {
            adminOrAdminGrpOrTransacGrp = new ArrayList<Object>();
        }
        return this.adminOrAdminGrpOrTransacGrp;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}

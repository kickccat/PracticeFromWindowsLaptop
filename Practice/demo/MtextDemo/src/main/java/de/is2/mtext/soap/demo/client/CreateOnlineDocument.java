
package de.is2.mtext.soap.demo.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für createOnlineDocument complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="createOnlineDocument"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="aufrufArt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="function" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="dokArt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="vorlagenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="vorlagenPfad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="d3_KeyArt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="d3_Key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sachbearbeiter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="vorlagenFilter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="datenNachbeschaffungsKz" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="datenNachbeschaffungsSchluessel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="data_Format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createOnlineDocument", propOrder = {
    "aufrufArt",
    "function",
    "dokArt",
    "vorlagenName",
    "vorlagenPfad",
    "d3KeyArt",
    "d3Key",
    "sachbearbeiter",
    "oe",
    "vorlagenFilter",
    "datenNachbeschaffungsKz",
    "datenNachbeschaffungsSchluessel",
    "dataFormat",
    "data"
})
public class CreateOnlineDocument {

    protected String aufrufArt;
    protected Integer function;
    protected String dokArt;
    protected String vorlagenName;
    protected String vorlagenPfad;
    @XmlElement(name = "d3_KeyArt")
    protected String d3KeyArt;
    @XmlElement(name = "d3_Key")
    protected String d3Key;
    protected String sachbearbeiter;
    @XmlElement(name = "OE")
    protected String oe;
    protected String vorlagenFilter;
    protected String datenNachbeschaffungsKz;
    protected String datenNachbeschaffungsSchluessel;
    @XmlElement(name = "data_Format")
    protected String dataFormat;
    protected String data;

    /**
     * Ruft den Wert der aufrufArt-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAufrufArt() {
        return aufrufArt;
    }

    /**
     * Legt den Wert der aufrufArt-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAufrufArt(String value) {
        this.aufrufArt = value;
    }

    /**
     * Ruft den Wert der function-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFunction() {
        return function;
    }

    /**
     * Legt den Wert der function-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFunction(Integer value) {
        this.function = value;
    }

    /**
     * Ruft den Wert der dokArt-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDokArt() {
        return dokArt;
    }

    /**
     * Legt den Wert der dokArt-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDokArt(String value) {
        this.dokArt = value;
    }

    /**
     * Ruft den Wert der vorlagenName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVorlagenName() {
        return vorlagenName;
    }

    /**
     * Legt den Wert der vorlagenName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVorlagenName(String value) {
        this.vorlagenName = value;
    }

    /**
     * Ruft den Wert der vorlagenPfad-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVorlagenPfad() {
        return vorlagenPfad;
    }

    /**
     * Legt den Wert der vorlagenPfad-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVorlagenPfad(String value) {
        this.vorlagenPfad = value;
    }

    /**
     * Ruft den Wert der d3KeyArt-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getD3KeyArt() {
        return d3KeyArt;
    }

    /**
     * Legt den Wert der d3KeyArt-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setD3KeyArt(String value) {
        this.d3KeyArt = value;
    }

    /**
     * Ruft den Wert der d3Key-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getD3Key() {
        return d3Key;
    }

    /**
     * Legt den Wert der d3Key-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setD3Key(String value) {
        this.d3Key = value;
    }

    /**
     * Ruft den Wert der sachbearbeiter-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSachbearbeiter() {
        return sachbearbeiter;
    }

    /**
     * Legt den Wert der sachbearbeiter-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSachbearbeiter(String value) {
        this.sachbearbeiter = value;
    }

    /**
     * Ruft den Wert der oe-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOE() {
        return oe;
    }

    /**
     * Legt den Wert der oe-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOE(String value) {
        this.oe = value;
    }

    /**
     * Ruft den Wert der vorlagenFilter-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVorlagenFilter() {
        return vorlagenFilter;
    }

    /**
     * Legt den Wert der vorlagenFilter-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVorlagenFilter(String value) {
        this.vorlagenFilter = value;
    }

    /**
     * Ruft den Wert der datenNachbeschaffungsKz-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatenNachbeschaffungsKz() {
        return datenNachbeschaffungsKz;
    }

    /**
     * Legt den Wert der datenNachbeschaffungsKz-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatenNachbeschaffungsKz(String value) {
        this.datenNachbeschaffungsKz = value;
    }

    /**
     * Ruft den Wert der datenNachbeschaffungsSchluessel-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatenNachbeschaffungsSchluessel() {
        return datenNachbeschaffungsSchluessel;
    }

    /**
     * Legt den Wert der datenNachbeschaffungsSchluessel-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatenNachbeschaffungsSchluessel(String value) {
        this.datenNachbeschaffungsSchluessel = value;
    }

    /**
     * Ruft den Wert der dataFormat-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFormat() {
        return dataFormat;
    }

    /**
     * Legt den Wert der dataFormat-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFormat(String value) {
        this.dataFormat = value;
    }

    /**
     * Ruft den Wert der data-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getData() {
        return data;
    }

    /**
     * Legt den Wert der data-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setData(String value) {
        this.data = value;
    }

}

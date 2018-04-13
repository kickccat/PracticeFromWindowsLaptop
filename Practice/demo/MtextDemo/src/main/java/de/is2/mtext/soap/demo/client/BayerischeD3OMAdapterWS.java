
package de.is2.mtext.soap.demo.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0
 * Generated source version: 2.2
 * 
 */
@WebService(name = "BayerischeD3OMAdapterWS", targetNamespace = "http://omAdapter.bayerische.kwsoft.de/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BayerischeD3OMAdapterWS {


    /**
     * 
     * @param datenNachbeschaffungsKz
     * @param data
     * @param d3KeyArt
     * @param dataFormat
     * @param aufrufArt
     * @param d3Key
     * @param vorlagenFilter
     * @param datenNachbeschaffungsSchluessel
     * @param dokArt
     * @param sachbearbeiter
     * @param vorlagenName
     * @param oe
     * @param function
     * @param vorlagenPfad
     * @return
     *     returns de.is2.mtext.soap.demo.client.OnlineDocumentResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createOnlineDocument", targetNamespace = "http://omAdapter.bayerische.kwsoft.de/", className = "de.is2.mtext.soap.demo.client.CreateOnlineDocument")
    @ResponseWrapper(localName = "createOnlineDocumentResponse", targetNamespace = "http://omAdapter.bayerische.kwsoft.de/", className = "de.is2.mtext.soap.demo.client.CreateOnlineDocumentResponse")
    @Action(input = "http://omAdapter.bayerische.kwsoft.de/BayerischeD3OMAdapterWS/createOnlineDocumentRequest", output = "http://omAdapter.bayerische.kwsoft.de/BayerischeD3OMAdapterWS/createOnlineDocumentResponse")
    public OnlineDocumentResult createOnlineDocument(
        @WebParam(name = "aufrufArt", targetNamespace = "")
        String aufrufArt,
        @WebParam(name = "function", targetNamespace = "")
        Integer function,
        @WebParam(name = "dokArt", targetNamespace = "")
        String dokArt,
        @WebParam(name = "vorlagenName", targetNamespace = "")
        String vorlagenName,
        @WebParam(name = "vorlagenPfad", targetNamespace = "")
        String vorlagenPfad,
        @WebParam(name = "d3_KeyArt", targetNamespace = "")
        String d3KeyArt,
        @WebParam(name = "d3_Key", targetNamespace = "")
        String d3Key,
        @WebParam(name = "sachbearbeiter", targetNamespace = "")
        String sachbearbeiter,
        @WebParam(name = "OE", targetNamespace = "")
        String oe,
        @WebParam(name = "vorlagenFilter", targetNamespace = "")
        String vorlagenFilter,
        @WebParam(name = "datenNachbeschaffungsKz", targetNamespace = "")
        String datenNachbeschaffungsKz,
        @WebParam(name = "datenNachbeschaffungsSchluessel", targetNamespace = "")
        String datenNachbeschaffungsSchluessel,
        @WebParam(name = "data_Format", targetNamespace = "")
        String dataFormat,
        @WebParam(name = "data", targetNamespace = "")
        String data);

    /**
     * 
     * @param docId
     * @param userName
     * @param vorlage
     * @return
     *     returns java.lang.Integer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "finalizeDocument", targetNamespace = "http://omAdapter.bayerische.kwsoft.de/", className = "de.is2.mtext.soap.demo.client.FinalizeDocument")
    @ResponseWrapper(localName = "finalizeDocumentResponse", targetNamespace = "http://omAdapter.bayerische.kwsoft.de/", className = "de.is2.mtext.soap.demo.client.FinalizeDocumentResponse")
    @Action(input = "http://omAdapter.bayerische.kwsoft.de/BayerischeD3OMAdapterWS/finalizeDocumentRequest", output = "http://omAdapter.bayerische.kwsoft.de/BayerischeD3OMAdapterWS/finalizeDocumentResponse")
    public Integer finalizeDocument(
        @WebParam(name = "userName", targetNamespace = "")
        String userName,
        @WebParam(name = "docId", targetNamespace = "")
        String docId,
        @WebParam(name = "vorlage", targetNamespace = "")
        String vorlage);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getOtherAttributesForD3XML", targetNamespace = "http://omAdapter.bayerische.kwsoft.de/", className = "de.is2.mtext.soap.demo.client.GetOtherAttributesForD3XML")
    @ResponseWrapper(localName = "getOtherAttributesForD3XMLResponse", targetNamespace = "http://omAdapter.bayerische.kwsoft.de/", className = "de.is2.mtext.soap.demo.client.GetOtherAttributesForD3XMLResponse")
    @Action(input = "http://omAdapter.bayerische.kwsoft.de/BayerischeD3OMAdapterWS/getOtherAttributesForD3XMLRequest", output = "http://omAdapter.bayerische.kwsoft.de/BayerischeD3OMAdapterWS/getOtherAttributesForD3XMLResponse")
    public String getOtherAttributesForD3XML(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}

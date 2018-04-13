
package de.is2.mtext.soap.demo.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.is2.mtext.soap.demo.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateOnlineDocument_QNAME = new QName("http://omAdapter.bayerische.kwsoft.de/", "createOnlineDocument");
    private final static QName _CreateOnlineDocumentResponse_QNAME = new QName("http://omAdapter.bayerische.kwsoft.de/", "createOnlineDocumentResponse");
    private final static QName _FinalizeDocument_QNAME = new QName("http://omAdapter.bayerische.kwsoft.de/", "finalizeDocument");
    private final static QName _FinalizeDocumentResponse_QNAME = new QName("http://omAdapter.bayerische.kwsoft.de/", "finalizeDocumentResponse");
    private final static QName _GetOtherAttributesForD3XML_QNAME = new QName("http://omAdapter.bayerische.kwsoft.de/", "getOtherAttributesForD3XML");
    private final static QName _GetOtherAttributesForD3XMLResponse_QNAME = new QName("http://omAdapter.bayerische.kwsoft.de/", "getOtherAttributesForD3XMLResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.is2.mtext.soap.demo.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateOnlineDocument }
     * 
     */
    public CreateOnlineDocument createCreateOnlineDocument() {
        return new CreateOnlineDocument();
    }

    /**
     * Create an instance of {@link CreateOnlineDocumentResponse }
     * 
     */
    public CreateOnlineDocumentResponse createCreateOnlineDocumentResponse() {
        return new CreateOnlineDocumentResponse();
    }

    /**
     * Create an instance of {@link FinalizeDocument }
     * 
     */
    public FinalizeDocument createFinalizeDocument() {
        return new FinalizeDocument();
    }

    /**
     * Create an instance of {@link FinalizeDocumentResponse }
     * 
     */
    public FinalizeDocumentResponse createFinalizeDocumentResponse() {
        return new FinalizeDocumentResponse();
    }

    /**
     * Create an instance of {@link GetOtherAttributesForD3XML }
     * 
     */
    public GetOtherAttributesForD3XML createGetOtherAttributesForD3XML() {
        return new GetOtherAttributesForD3XML();
    }

    /**
     * Create an instance of {@link GetOtherAttributesForD3XMLResponse }
     * 
     */
    public GetOtherAttributesForD3XMLResponse createGetOtherAttributesForD3XMLResponse() {
        return new GetOtherAttributesForD3XMLResponse();
    }

    /**
     * Create an instance of {@link OnlineDocumentResult }
     * 
     */
    public OnlineDocumentResult createOnlineDocumentResult() {
        return new OnlineDocumentResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOnlineDocument }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateOnlineDocument }{@code >}
     */
    @XmlElementDecl(namespace = "http://omAdapter.bayerische.kwsoft.de/", name = "createOnlineDocument")
    public JAXBElement<CreateOnlineDocument> createCreateOnlineDocument(CreateOnlineDocument value) {
        return new JAXBElement<CreateOnlineDocument>(_CreateOnlineDocument_QNAME, CreateOnlineDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOnlineDocumentResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateOnlineDocumentResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://omAdapter.bayerische.kwsoft.de/", name = "createOnlineDocumentResponse")
    public JAXBElement<CreateOnlineDocumentResponse> createCreateOnlineDocumentResponse(CreateOnlineDocumentResponse value) {
        return new JAXBElement<CreateOnlineDocumentResponse>(_CreateOnlineDocumentResponse_QNAME, CreateOnlineDocumentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinalizeDocument }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FinalizeDocument }{@code >}
     */
    @XmlElementDecl(namespace = "http://omAdapter.bayerische.kwsoft.de/", name = "finalizeDocument")
    public JAXBElement<FinalizeDocument> createFinalizeDocument(FinalizeDocument value) {
        return new JAXBElement<FinalizeDocument>(_FinalizeDocument_QNAME, FinalizeDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinalizeDocumentResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FinalizeDocumentResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://omAdapter.bayerische.kwsoft.de/", name = "finalizeDocumentResponse")
    public JAXBElement<FinalizeDocumentResponse> createFinalizeDocumentResponse(FinalizeDocumentResponse value) {
        return new JAXBElement<FinalizeDocumentResponse>(_FinalizeDocumentResponse_QNAME, FinalizeDocumentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOtherAttributesForD3XML }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetOtherAttributesForD3XML }{@code >}
     */
    @XmlElementDecl(namespace = "http://omAdapter.bayerische.kwsoft.de/", name = "getOtherAttributesForD3XML")
    public JAXBElement<GetOtherAttributesForD3XML> createGetOtherAttributesForD3XML(GetOtherAttributesForD3XML value) {
        return new JAXBElement<GetOtherAttributesForD3XML>(_GetOtherAttributesForD3XML_QNAME, GetOtherAttributesForD3XML.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOtherAttributesForD3XMLResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetOtherAttributesForD3XMLResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://omAdapter.bayerische.kwsoft.de/", name = "getOtherAttributesForD3XMLResponse")
    public JAXBElement<GetOtherAttributesForD3XMLResponse> createGetOtherAttributesForD3XMLResponse(GetOtherAttributesForD3XMLResponse value) {
        return new JAXBElement<GetOtherAttributesForD3XMLResponse>(_GetOtherAttributesForD3XMLResponse_QNAME, GetOtherAttributesForD3XMLResponse.class, null, value);
    }

}

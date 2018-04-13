
package de.is2.soap.demo.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.is2.webservice.demo.client package. 
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

    private final static QName _TestMethod_QNAME = new QName("http://service.demo.soap.is2.de/",
            "testMethod");
    private final static QName _TestMethodResponse_QNAME = new QName("http://service.demo.soap" +
            ".is2.de/", "testMethodResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.is2.webservice.demo.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TestMethod }
     * 
     */
    public TestMethod createTestMethod() {
        return new TestMethod();
    }

    /**
     * Create an instance of {@link TestMethodResponse }
     * 
     */
    public TestMethodResponse createTestMethodResponse() {
        return new TestMethodResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestMethod }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TestMethod }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.demo.soap.is2.de/", name = "testMethod")
    public JAXBElement<TestMethod> createTestMethod(TestMethod value) {
        return new JAXBElement<TestMethod>(_TestMethod_QNAME, TestMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestMethodResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TestMethodResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.demo.webservice.is2.de/", name = "testMethodResponse")
    public JAXBElement<TestMethodResponse> createTestMethodResponse(TestMethodResponse value) {
        return new JAXBElement<TestMethodResponse>(_TestMethodResponse_QNAME, TestMethodResponse.class, null, value);
    }

}

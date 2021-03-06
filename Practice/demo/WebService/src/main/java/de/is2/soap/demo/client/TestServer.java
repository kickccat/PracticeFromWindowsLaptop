
package de.is2.soap.demo.client;

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
@WebService(name = "TestServer", targetNamespace = "http://service.demo.webservice.is2.de/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface TestServer {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "testMethod", targetNamespace = "http://service.demo.webservice.is2.de/", className = "de.is2.webservice.demo.client.TestMethod")
    @ResponseWrapper(localName = "testMethodResponse", targetNamespace = "http://service.demo.webservice.is2.de/", className = "de.is2.webservice.demo.client.TestMethodResponse")
    @Action(input = "http://service.demo.webservice.is2.de/TestServer/testMethodRequest", output = "http://service.demo.webservice.is2.de/TestServer/testMethodResponse")
    public String testMethod(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}

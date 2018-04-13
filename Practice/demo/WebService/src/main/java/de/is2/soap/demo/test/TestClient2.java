package de.is2.soap.demo.test;

import de.is2.soap.demo.service.TestServer;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class TestClient2 {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8081/service/TestServer?wsdl");
            QName qName = new QName("http://server.demo.webservice.test.is2.de/", "TestServerService");
            Service service = Service.create(url, qName);
            TestServer testServer = service.getPort(TestServer.class);
            String result = testServer.testMethod("Client 2 tests");
            System.out.println("Client 2 testing results: \n" + result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

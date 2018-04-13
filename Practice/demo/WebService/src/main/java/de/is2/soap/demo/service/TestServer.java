package de.is2.soap.demo.service;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.Date;

@WebService
public class TestServer {

    public String testMethod(String inParam) {
        System.out.println("Server recieve: " + inParam);
        return "Server recieves on " + new Date() + " from client: " + inParam;
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8081/service/TestServer", new TestServer());
        System.out.println("Server started.");
    }
}

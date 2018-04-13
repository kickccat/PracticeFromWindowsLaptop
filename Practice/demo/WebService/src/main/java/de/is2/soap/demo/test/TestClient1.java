package de.is2.soap.demo.test;

import de.is2.soap.demo.client.TestServer;
import de.is2.soap.demo.client.TestServerService;

public class TestClient1 {

    public static void main(String[] args) {
        TestServerService testServerService= new TestServerService();
        TestServer testServer = testServerService.getTestServerPort();

        String result = testServer.testMethod("Client 1 test");
        System.out.println("Client 1 test result: \n" + result);
    }
}

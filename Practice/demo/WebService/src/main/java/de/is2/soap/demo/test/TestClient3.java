package de.is2.soap.demo.test;

import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.net.URL;

public class TestClient3 {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8081/service/TestServer");
            String nameSpace = "http://server.demo.webservice.test.is2.de/";
            String method = "testMethod";

            Service service = new Service();
            Call call = (Call) service.createCall();

            call.setOperationName(new QName(nameSpace, method));
            call.setTargetEndpointAddress(url);
            call.setTimeout(3000);
            call.addParameter("arg0", Constants.XSD_STRING, ParameterMode.IN);
            call.setReturnType(Constants.XSD_STRING);

            String result = (String) call.invoke(new Object[]{"Client 3 tests"});
            System.out.println("Client 3 testing results: \n" + result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

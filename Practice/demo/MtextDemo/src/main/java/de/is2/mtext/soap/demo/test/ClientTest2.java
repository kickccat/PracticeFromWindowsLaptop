package de.is2.mtext.soap.demo.test;

import de.is2.mtext.soap.demo.client.BayerischeD3OMAdapterWS;
import de.is2.mtext.soap.demo.client.CreateOnlineDocument;
import de.is2.mtext.soap.demo.client.ObjectFactory;
import de.is2.mtext.soap.demo.client.OnlineDocumentResult;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class ClientTest2 {
    
    public static void main(String[] args) {
        String data = "<program>\n" +
                               "        <kundenkonto>\n" +
                               "            <FreischaltCode>Mtxz6?5</FreischaltCode>\n" +
                               "                <Textvariante>PANG</Textvariante>\n" +
                               "        </kundenkonto>\n" +
                               "</program>\n";
        
        ObjectFactory factory = new ObjectFactory();
        CreateOnlineDocument onlineDocument = factory.createCreateOnlineDocument();
        String serviceURL= "http://iswasdev2.bbv.de:9086/OMAdapter/BayerischeD3OMAdapterWSService/BayerischeD3OMAdapterWSService.wsdl";
        
        try {
            QName serviceName = new QName("http://omAdapter.bayerische.kwsoft.de/", "BayerischeD3OMAdapterWSService");
            URL wsdlURL = new URL(serviceURL);
            Service service = Service.create(wsdlURL, serviceName);
            BayerischeD3OMAdapterWS proxy = service.getPort(BayerischeD3OMAdapterWS.class);
    
            Iterator iterator = ((BindingProvider)proxy).getRequestContext().values().iterator();
            while (iterator.hasNext()) {
                System.out.println("Request context: " + iterator.next());
            }
            System.out.println("Other attribute: " + proxy.getOtherAttributesForD3XML(""));
    
            OnlineDocumentResult onlineDocumentResult = proxy.createOnlineDocument("MTCS",3,
                    "SAVED","Anmeldung",
                    "Verfahren_Allgemein/Kundenkonto","VSNR","B090390123456789","A321008","","","",
                    "","XML","");
            int status = onlineDocumentResult.getStatus();
    
            switch (status) {
                case 0:
                    display("OK");
                    break;
                case -1:
                    display("INVALID_PARAMETER");
                    break;
                case -2:
                    display("D3_FAILURE");
                    break;
                case -3:
                    display("FATAL_ERROR");
                    break;
                default:
                    display("UNKNOWN ERROR");
            }
    
            if (onlineDocumentResult.getStatus() != 0) {
                System.out.println("Error: " + onlineDocumentResult.getErrorMsg());
            }
            else {
                System.out.println("Document ID: " + onlineDocumentResult.getDocId());
            }
    
            String result = ((BayerischeD3OMAdapterWS) proxy).getOtherAttributesForD3XML("test");
            System.out.println("Test: " + result);
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    
    private static void display(String str) {
        System.out.println("Result: " + str);
    }
}

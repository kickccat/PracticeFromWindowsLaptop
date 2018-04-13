package de.is2.mtext.soap.demo.test;

import de.is2.mtext.soap.demo.client.BayerischeD3OMAdapterWS;
import de.is2.mtext.soap.demo.client.BayerischeD3OMAdapterWSService;
import de.is2.mtext.soap.demo.client.OnlineDocumentResult;

import javax.xml.ws.BindingProvider;
import java.util.HashMap;
import java.util.Map;

public class TestWsClient {
    
    public static void main(String[] args) throws Exception {
        
        String[] vsnrs = {"B090390047513", "B090390047513", "B090390052349", "S0030007122", "B090390049104", "B090390047513", "B090390052129", "B090390052349", "B040340038253", "S0030043730", "S0030043314", "S0030055760", "S0097000112", "S0030055757", "S0030055757", "S0097000112", "B090390050509"};
        
        BindingProvider bindingProvider = getBindingProvider("http://iswasdev2.bbv.de:9086/OMAdapter/BayerischeD3OMAdapterWSService");

        System.out.println("Request: " + bindingProvider.getRequestContext().values());
        System.out.println("Response: " + bindingProvider.getResponseContext().values());
        
        String[] messages = new String[vsnrs.length];
        String[] docIds = new String[vsnrs.length];
        
        Map<Integer, HashMap<Integer, String>> table = new HashMap<>(); // Status, message, docId
    
        // Test Daten
        String[] kundennr = {"K0126409982", "K0126410118"};
        String[] freischaltcodes = {"Test1", "Test2"};
        boolean isPangaea = false; // true with "PANG", false with "BAY"
        String[] testTextVariants = {"", "TEST"};
        String[] data = {"<program><kundenkonto><FreischaltCode>"+ freischaltcodes[0] +
                               "</FreischaltCode><Textvariante>"+ testTextVariants[0]
                               +"</Textvariante></kundenkonto></program>", "<program><kundenkonto><FreischaltCode>"+ freischaltcodes[1] +
                                                                                                           "</FreischaltCode><Textvariante>"+ testTextVariants[1]
                                                                                                           +"</Textvariante></kundenkonto></program>"};
    
        String data1 = "<program>\n" +
                               "        <kundenkonto>\n" +
                               "            <FreischaltCode>Mtxz6?5</FreischaltCode>\n" +
                               "\t\t <Textvariante>PANG</Textvariante>\n" +
                               "        </kundenkonto>\n" +
                               "</program>\n";
        
        for (int i = 0; i < kundennr.length; i++) {
//            OnlineDocumentResult documentResult = ((BayerischeD3OMAdapterWS) bindingProvider)
//                                                          .createOnlineDocument("MTCS", 3,
//                                                                  "SAVED", "Anmeldung",
//                                                                  "\\\\Verfahren_Allgemein\\Kundenkonto\\Vorlagen", "VSNR", vsnrs[i], "A321008", "", "", "",
//                                                                  "", "XML", data1);
    
            OnlineDocumentResult documentResult = ((BayerischeD3OMAdapterWS) bindingProvider)
                                                          .createOnlineDocument("MTCS", 3, "KUNDE",
                                                                  "Anmeldung",
                                                                  "\\\\Verfahren_Allgemein\\Kundenkonto\\Vorlagen", "KDNR", kundennr[i], "A321008", "", "", "", "",
                                                                  "XML", data[i]);
            
            int status = documentResult.getStatus();
            
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
            
            addValue(table, i, documentResult.getStatus(), documentResult.getErrorMsg(), documentResult.getDocId());
//            if (documentResult.getStatus() != 0) {
//                System.out.println("Error: " + documentResult.getErrorMsg());
//            } else {
//                System.out.println("Document ID: " + documentResult.getDocId());
//            }
        }
        System.out.println("Status ----- Message ------ docId");
        System.out.println("Size: " + table.size());
        for (Map.Entry<Integer, HashMap<Integer, String>> entry : table.entrySet()) {
            if (!entry.getValue().keySet().isEmpty()) {
                System.out.println("doc id: " + entry.getKey() + " --------------> " + entry.getValue());
            } else {
                System.out.println("msg: " + entry.getKey() + "---->" + entry.getValue());
            }
        }
    }
    
    private static void display(String str) {
        System.out.println("Result: " + str);
    }
    
    private static void addValue(Map<Integer, HashMap<Integer, String>> map, int index, Integer sts, String msg, String id) {
        HashMap<Integer, String> subMap = new HashMap<>();
        map.put(index, subMap);
        if (msg.isEmpty()) {
            subMap.put(sts, id);
        } else if (id.isEmpty()) {
            subMap.put(sts, msg);
        }
    }
    
    private static BindingProvider getBindingProvider(String url) {
        BayerischeD3OMAdapterWSService d3OMAdapterWSServiceservice = new BayerischeD3OMAdapterWSService();
        BayerischeD3OMAdapterWS d3OMAdapterWS = d3OMAdapterWSServiceservice.getBayerischeD3OMAdapterWSPort();
        
        BindingProvider bindingProvider = (BindingProvider) d3OMAdapterWS;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
        
        return bindingProvider;
    }
}

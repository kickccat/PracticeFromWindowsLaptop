package de.is2.mtext.soap.demo.repository;

import de.is20.bestandsinfo.service.api.PagingInfoParameter;
import de.is20.bestandsinfo.service.api.datatypes.kunde.Kunde;
import de.is20.bestandsinfo.service.api.datatypes.police.Police;
import de.is20.data.graphendb.client.EndkundenServiceAccess;
import de.is20.data.graphendb.client.KundenServiceAccessKSC;
import de.is20.data.graphendb.client.WebResourceProvider;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.function.Supplier;

public class BaseNeo4jAction {
    
    public final static String NEW_LINE_SEPERATOR = "\n";
    public static final String KUNDENNR_NODE_FILE = "C:\\Users\\yi.zhou\\Workspace\\temp\\KundenNr&NodeID.csv";
    
    WebResourceProvider webResourceProvider;
    EndkundenServiceAccess service = new EndkundenServiceAccess();
    KundenServiceAccessKSC kundenService = new KundenServiceAccessKSC();
    
    @Value(value = "http://test-bay1.is2.de:17474/bestandsinfo/")
    public static final String SERVERURL = "http://test-bay1.is2.de:17474/bestandsinfo/";
    
    public static <T> void addValue(StringBuilder sb, T value) {
        try {
            if (value.getClass().getSimpleName().equals("Double")) {
                value = getLocalFormat(value);
//				value = (T) value.toString().replace(".", ",");
            }
            sb.append(value);
        } catch (NullPointerException e) {
            sb.append("");
        }
        addDelimiter(sb);
    }
    
    public static void writeToFile(String outFile, StringBuilder stringBuilder) {
        try {
            Files.write(Paths.get(outFile), stringBuilder.toString().getBytes(Charset.forName("ISO-8859-1")),
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void lineBreak(StringBuilder stringBuilder) {
        stringBuilder.append(NEW_LINE_SEPERATOR);
    }
    
    private static <T> T getLocalFormat(T value) {
        String stringValue = value.toString();
        return (T) stringValue.replace(".", ",");
    }
    
    private static void addDelimiter(StringBuilder sb) {
        sb.append(";");
    }
    
    public String checkKundenNrWith10Length() {
        StringBuilder builder = new StringBuilder();
        int length = 0;
        // From KundenServiceAccessKSC get the all KundenListe with KundenNr.
        KundenServiceAccessKSC kundenServiceAccessKSC = connectDBWithKundeAccess();
        List<Kunde> kundeList = kundenServiceAccessKSC.getKundeListe(new PagingInfoParameter(0, 6666666));
        
        System.out.println("Kunde List: " + kundeList.size());
    
        Iterator<Kunde> kundeIterator = kundeList.iterator();
        while (kundeIterator.hasNext()) {
            String kundenNr = String.valueOf(kundeIterator.next().getKundennummer());
            if (kundenNr.startsWith("1") || kundenNr.length() == 9 || kundenNr.isEmpty() || kundenNr.equals("null")) {
                continue;
            }
            builder.append(kundenNr);
            length++;
        }
        System.out.println("Kunden Nummer länger als 9 existieren " + length);
        return builder.toString();
    }
    
    public StringBuilder readKundenNrAndNodeId() {
        
        // From KundenServiceAccessKSC get the all KundenListe with Node ID and KundenNr.
        KundenServiceAccessKSC kundenServiceAccessKSC = connectDBWithKundeAccess();
        List<Kunde> kundeList = kundenServiceAccessKSC.getKundeListe(new PagingInfoParameter(0,
                530000));
        System.out.println("Kunde List: " + kundeList.size());
        
        EndkundenServiceAccess endkundenServiceAccess = connectDBWithEndeKundeAccess();
        // Save KundenListe at the file "C:\\Users\\yi.zhou\\Workspace\\temp\\KundenNr&NodeID.csv"
        StringBuilder builder = new StringBuilder();
        String policeNodeId;
        int emptyValue = 0;
        // Titel
        builder.append("KundenNr;NodeId");
        builder.append("\n"); // Line breaks
        Iterator<Kunde> kundeIterator = kundeList.iterator();
        while (kundeIterator.hasNext()) {
            try {
//                String nodeId = String.valueOf(kundeIterator.next().getNodeId());
                String kundenNr = String.valueOf(kundeIterator.next().getKundennummer());
                List<Police> policeList = endkundenServiceAccess.getPoliceListe(kundenNr);
                for (Police p : policeList) {
                    if (p.getVsnr() == null || p.getVsnr().isEmpty() || p.getVsnr() == "null") {
                        continue;
                    }
                    policeNodeId = String.valueOf(p.getNodeId());
                    addValue(builder, kundenNr);
                    addValue(builder, policeNodeId);
                    lineBreak(builder);
                }
//                writeToFile(KUNDENNR_NODE_FILE, builder);
//                builder = new StringBuilder();
            } catch (NoSuchElementException e) {
                e.getMessage();
                emptyValue++;
            }
        }
        System.out.println("Empty nodes: " + emptyValue);
        return builder;
    }
    
    public void saveKundenNrAndNodeId(StringBuilder builder) {
        int length = 0;
        
        BufferedOutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(KUNDENNR_NODE_FILE));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(builder.toString().getBytes());
            
            while ((length = inputStream.read()) != -1) {
                outputStream.write(length);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
    
    public EndkundenServiceAccess connectDBWithEndeKundeAccess() {
        // Service connect to DB
        webResourceProvider = createWebResourceProvider(SERVERURL);
        service.setWebResourceProvider(webResourceProvider);
        
        return service;
    }
    
    public KundenServiceAccessKSC connectDBWithKundeAccess() {
        // Service connect to DB
        webResourceProvider = createWebResourceProvider(SERVERURL);
        kundenService.setWebResourceProvider(webResourceProvider);
        return kundenService;
    }
    
    private WebResourceProvider createWebResourceProvider(String url) {
        Supplier<String> sessionID = () -> UUID.randomUUID().toString();
        return new WebResourceProvider(
                //Consts.TEST_CLIENT_ID,
                "KundenkontoNeo4jAnbindung",
                //Consts.sessionID.get(),
                sessionID.get(),
                //Consts.THE_LOCAL_ROOT + Consts.PREFIX_4_USER,
                url,
                new WebResourceProvider.UsernameAndPassword("user", "user"));
    }
    
    public static void loadingProgress(double progressPercentage) {
        final int width = 50; // progress bar width in chars
        
        System.out.print("\r[");
        int i = 0;
        for (; i <= (int) (progressPercentage * width); i++) {
            System.out.print(".");
        }
        for (; i < width; i++) {
            System.out.print(" ");
        }
        System.out.print("]");
    }
}

package de.is2.mtext.soap.demo.test;

import de.is20.bestandsinfo.service.api.PagingInfoParameter;
import de.is20.bestandsinfo.service.api.datatypes.Kommunikation;
import de.is20.bestandsinfo.service.api.datatypes.kunde.Kunde;
import de.is20.bestandsinfo.service.datatypes.kunde.KundeDetailsImpl;
import de.is20.data.graphendb.befuellung.util.NeoTools;
import de.is20.data.graphendb.client.KundenServiceAccess;
import de.is20.data.graphendb.client.KundenServiceAccessKSC;
import de.is20.data.graphendb.client.WebResourceProvider;
import de.is20.data.graphendb.model.pm.PmEntityFamily;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.tooling.GlobalGraphOperations;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static de.is20.data.graphendb.befuellung.Consts.sessionID;
import static de.is20.data.graphendb.model.pm.generated.tables.GenPmEnumKommunikationsArt.GESCHAEFTLICH_1;
import static de.is20.data.graphendb.model.pm.generated.tables.GenPmEnumKommunikationsArt.PRIVAT_2;

public class FindCommunicationDataTest {
    public static final String THE_DATABASE = "http://test-bay1.is2.de:17474/browser/";
    WebResourceProvider webResourceProvider;
    GlobalGraphOperations dbOperations;
    KundenServiceAccess kundenServiceAccess = new KundenServiceAccessKSC();
    
    public static void main(String[] args) {
        System.out.println("Database: " + THE_DATABASE);
        new FindCommunicationDataTest().run();
    }
    
    private void run() {
        webResourceProvider = new WebResourceProvider(
                //Consts.TEST_CLIENT_ID,
                "Kundenkonto",
                //Consts.sessionID.get(),
                sessionID.get(),
                //Consts.THE_LOCAL_ROOT + Consts.PREFIX_4_USER,
                "http://test-bay1.is2.de:17474/bestandsinfo",
                new WebResourceProvider.UsernameAndPassword("user", "user"));
        
        kundenServiceAccess.setWebResourceProvider(webResourceProvider);
        List<Kunde> kundeList = kundenServiceAccess.getKundeListe(new PagingInfoParameter());
        kundeList.iterator().next();
        ResourceIterable<Node> kundenNodes = dbOperations.getAllNodesWithLabel(NeoTools.asLabel(PmEntityFamily.PARTNER));
        Iterator<Node> nodeIterator = kundenNodes.iterator();
        
        HashMap out = null;
        
        while (nodeIterator.hasNext()) {
            long nodeId = nodeIterator.next().getId();
            if (getKommunikation(nodeId).containsValue("") || getKommunikation(nodeId).values().isEmpty()) {
                continue;
            } else {
                out = getKommunikation(nodeId);
                break;
            }
        }
        System.out.println("Kunden details: " + out);
    }
    
    private HashMap getKommunikation(Long kundeNodeId) {
        KundeDetailsImpl kundenDetails = new KundeDetailsImpl(kundenServiceAccess.getKunde(kundeNodeId));
        HashMap commun = new HashMap(); // Store communication
        
        List<Kommunikation> list = kundenDetails.getKommunikationListe();
        for (Kommunikation k : list) {
            switch (k.getKommunikationTyp()) {
                case Kom_Telefon:
                    if (k.getKommunikationArt() == GESCHAEFTLICH_1) {
                        if (!commun.containsValue(k.getWert())) {
                            commun.put("TelefonBeruf", k.getWert());
                        }
                    } else if (k.getKommunikationArt() == PRIVAT_2) {
                        if (!commun.containsValue(k.getWert())) {
                            commun.put("TelefonPrivat", k.getWert());
                        }
                    } else {
                        if (!commun.containsValue(k.getWert())) {
                            commun.put("Telefon", k.getWert());
                        }
                    }
                    break;
                case Kom_Handy:
                    if (!commun.containsValue(k.getWert())) {
                        commun.put("Handy", k.getWert());
                    }
                    break;
                case Kom_Fax:
                    if (!commun.containsValue(k.getWert())) {
                        commun.put("Fax", k.getWert());
                    }
                    break;
                case Kom_eMail:
                    if (!commun.containsValue(k.getWert())) {
                        commun.put("Email", k.getWert());
                    }
                    break;
            }
        }
        return commun;
    }
}

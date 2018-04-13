package de.is2.mtext.soap.demo.graphendbtest;

import com.sun.jersey.api.client.WebResource;
import de.is20.bestandsinfo.service.api.AccessInfo;
import de.is20.bestandsinfo.service.api.KundenService;
import de.is20.bestandsinfo.service.api.PagingInfoParameter;
import de.is20.bestandsinfo.service.api.datatypes.antrag.Antrag;
import de.is20.bestandsinfo.service.api.datatypes.antrag.AntragFragment;
import de.is20.bestandsinfo.service.api.datatypes.kunde.KundeDetails;
import de.is20.bestandsinfo.service.api.datatypes.police.Police;
import de.is20.data.graphendb.client.*;
import de.is20.data.graphendb.hostmapping.utils.OutTime;
import de.is20.infrastructure.filestore.FileStoreAccess;
import de.is20.portal.model.AccessInfoImpl;
import de.is20.portal.model.api.IPortalModelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

@Component
@Order(2)
@PropertySource("classpath:application.properties")
public class TestGraphDatabase {

    @Value("${DATABASE}")
    private static String bay4allServerUrl;

    private static IPortalModelService portalService;
    private static WebResourceProvider webResourceProvider;
    private static WebResource webResource;
    private static PolicenServiceAccessKSC policenServiceAccessKSC;
    private static KundenServiceAccessKSC kundenServiceAccessKSC;
    private AntragsServiceAccess antragsService;
    private List<Antrag> antragListe;

    public static void main(String[] args) {
        run();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public static void setPortalService(IPortalModelService portalService) {
        TestGraphDatabase.portalService = portalService;
    }

    public String getBeratungsDokuLocation(String kundennr){
        List<Police> policeList = getPolices(kundennr);
        String dokuUrl = null;

        for ( Police p : policeList) {
            // Find the BeratungsDoku
            antragsService = new AntragsServiceAccess(new AccessInfoImpl(portalService),
                    new FileStoreAccess(webResource));
            antragListe = antragsService.getAntragListe(p.getNodeId());
            while (antragListe.listIterator().hasNext()) {
                Antrag antrag = antragListe.listIterator().next();
                if (antrag.getAntragsnummer().equals(p.getAntragsnummer()) && antrag.getFragmente()
                        .equals(AntragFragment.BERATUNGSDOKU_PDF)) {
                    dokuUrl= portalService.getPropertyService()
                            .getProperty("environment.setting.ausdruck.beratungsprotokoll.url");
                }
            }
        }
        return dokuUrl;
    }

    public static void run() {
        try (final OutTime outTime = new OutTime("EndkundenBeispiel", System.out)) {
            final Supplier<String> sessionID = () -> UUID.randomUUID().toString();
            webResourceProvider = new WebResourceProvider(
                    //Consts.TEST_CLIENT_ID,
                    "Kundenkonto",
                    //Consts.sessionID.get(),
                    sessionID.get(),
                    //Consts.THE_LOCAL_ROOT + Consts.PREFIX_4_USER,
                    "http://test-bay1.is2.de:17474/bestandsinfo",
                    new WebResourceProvider.UsernameAndPassword("user", "user"));

            final EndkundenServiceAccess service = new EndkundenServiceAccess();
            service.setWebResourceProvider(webResourceProvider);

            final KundeDetails details = service.getKundeByPartnerNr("100001030");

            List<Police> list = service.getPoliceListe("100001030");
            System.out.println(list.get(0).getVsnr());
            Long nodeId = list.get(0).getNodeId();
            byte[] info = service.getPoliceDetailsByNodeId("100001030", nodeId);
            Police my = list.get(0);
            details.getBankdatenListe();
            System.out.println(details.getName());
            System.out.println(details.getVorname());

            AccessInfo accessInfo= new AccessInfoImpl(portalService);
            KundenService kundenService = new KundenServiceAccess(accessInfo);
        }
    }

    public List<Police> getPolices(String kundennr) {
        webResource = webResourceProvider.createBasicWebResource(); // Get the WebResource
        kundenServiceAccessKSC.setWebResourceProvider(webResourceProvider);
        policenServiceAccessKSC.setWebResourceProvider(webResourceProvider);
        de.is20.bestandsinfo.service.api.datatypes.kunde.Kunde kunde = null;
        List<de.is20.bestandsinfo.service.api.datatypes.kunde.Kunde> kundeListe = kundenServiceAccessKSC.getKundeListeByPartnerNr(kundennr, new PagingInfoParameter(0, Integer.MAX_VALUE));

        if (kundeListe.size() == 1) {
            kunde = kundeListe.get(0);
        }

        if (kunde != null) {
            List<Police> polices = policenServiceAccessKSC.getPoliceListe(kunde.getNodeId());
            return polices;
        }

        return new ArrayList<>(0);
    }
}

package de.is2.mtext.soap.demo.repository;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import de.is2.kundenkonto.model.Berater;
import de.is2.kundenkonto.model.mapper.VermittlerBeraterMapper;
import de.is2.kundenkonto.services.GraphendbClientService;
import de.is2.kundenkonto.services.utils.VermittlerfotoService;
import de.is20.bestandsinfo.service.api.datatypes.vermittler.Vermittler;
import de.is20.data.graphendb.client.VermittlerServiceAccess;
import de.is20.data.graphendb.model.pm.CodedPmEnumVertriebsweg;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BeraterController{
    
    private static final Logger logger = LoggerFactory.getLogger(VermittlerfotoService.class);
    private static final String cmsURL = "https://www.diebayerische.de/media/images/vermittler_photos/";
    
    private VermittlerServiceAccess vermittlerServiceAccess;

    private VermittlerBeraterMapper vermittlerBeraterMapper;
    
    private GraphendbClientService graphendbClientService;

    private VermittlerfotoService vermittlerfotoService;
    
    public Berater getBerater(String kundennr) {

    	Vermittler vermittler = graphendbClientService.getVermittler(kundennr);
    	Berater berater = vermittlerBeraterMapper.toBerater(vermittler);
    	if ( berater != null ) {
            berater.setFoto(vermittlerfotoService.loadVermittlerfoto(vermittler.getVermittlernummer()));
        }
        return berater;
    }
    
    public String loadVermittlerfoto(String vermittlernummer) {
        try {
            byte[] byteArray;
            Client client = Client.create();
            final URI uri = UriBuilder.fromUri(cmsURL).build();
            client.setFollowRedirects(true);
            final WebResource service = client.resource(uri);
//            final ClientResponse response = service.path("248103").get(ClientResponse.class);
            final ClientResponse response = service.path(vermittlernummer).get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Request " + cmsURL + ": " + response.getStatus());
            }
            final InputStream entityInputStream = response.getEntityInputStream();
            byteArray = IOUtils.toByteArray(entityInputStream);
            if (byteArray.length > 100000) {
                String byteStr = new String(byteArray);
                System.out.println("Beraterfoto: " + byteStr);
                final String encodedBase64String = Base64.encodeBase64String(byteArray);
                return "data:image/jpeg;base64," + encodedBase64String;
            }
        } catch (final IOException e) {
            logger.error("Vermittler-Bild konnte nicht von CRM System geladen werden", e);
        }
        return null;
    }
    
    private List<Vermittler> getAllVermittler(String kundennummer) {
        return vermittlerServiceAccess.getVermittlerAnhandKundenNr(kundennummer);
    }
    
    public Vermittler getVermittler(String kundennummer) {
        List<Vermittler> vermittlers = getAllVermittler(kundennummer);
        if ( vermittlers != null && vermittlers.size() > 0 ){
            try {
                Vermittler vermittler = vermittlerServiceAccess.getOrigBestVermittlerByVeNr(vermittlers.get(0).getVermittlernummer());
                if ( CodedPmEnumVertriebsweg.MAKLER_MFA_2 == vermittler.getVertriebsweg() ){
                    // Makler
                    return null;
                }
                Date austritt = vermittler.getVertragEnde();
                if ( austritt != null) {
                    Calendar c = Calendar.getInstance();
                    c.setTime(austritt);
                    c.add(Calendar.MONTH, -3);
                    if ( c.getTime().before(new Date()) ) {
                        return null;
                    }
                }
                return vermittler;
            } catch (Exception ex){
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }
}

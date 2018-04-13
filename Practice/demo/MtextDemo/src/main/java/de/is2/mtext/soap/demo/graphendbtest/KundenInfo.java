package de.is2.mtext.soap.demo.graphendbtest;

import de.is20.bestandsinfo.service.api.datatypes.Kommunikation;
import de.is20.bestandsinfo.service.api.datatypes.kunde.KundeDetails;
import de.is20.bestandsinfo.service.api.datatypes.police.Police;
import de.is20.data.graphendb.client.EndkundenServiceAccess;
import de.is20.data.graphendb.client.WebResourceProvider;
import de.is20.data.graphendb.hostmapping.utils.OutTime;
import de.is20.data.graphendb.model.pm.generated.classes.GenPmClassKommunikationsTyp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import static de.is2.mtext.soap.demo.repository.BaseNeo4jAction.*;

public class KundenInfo {
    
    private static int notFound = 0;
    
    public static void main(String[] args) throws IOException {
        KundenInfo kundenInfo = new KundenInfo();
        kundenInfo.run();
    }
    
    public void run() throws IOException {
        try (final OutTime outTime = new OutTime("Endkunden Details", System.out)) {
            String kundenFile = "C:\\Users\\yi.zhou\\Workspace\\temp\\KundenDetails.csv";
            Supplier<String> sessionID = () -> UUID.randomUUID().toString();
            WebResourceProvider webResourceProvider = new WebResourceProvider(
                    //Consts.TEST_CLIENT_ID,
                    "Kundenkonto",
                    //Consts.sessionID.get(),
                    sessionID.get(),
                    //Consts.THE_LOCAL_ROOT + Consts.PREFIX_4_USER,
                    "http://test-bay1.is2.de:17474/bestandsinfo",
                    new WebResourceProvider.UsernameAndPassword("", ""));
            
            EndkundenServiceAccess service = new EndkundenServiceAccess();
            service.setWebResourceProvider(webResourceProvider);
            
            // Read KundenNr from csv file
            StringBuilder builder = new StringBuilder();
            // Titel
            builder.append("Policennummer;Vermittlernummer;Sparte;Vertragsart;Tarif;PolizierungsDatum;NameVN;VornameVN;Geburtsdatum;KundenNr;Telefon;Handy;Fax;Email;Stadt;Strasse;HausNr;HausNrZusatz;AdresseZusatz;Postfach;PLZ");
            builder.append(NEW_LINE_SEPERATOR); // Line breaks
    
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            
            FileReader fileReader = new FileReader("C:\\Users\\yi.zhou\\Workspace\\temp\\KundenNr.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input = bufferedReader.readLine();
            
            while ((input = bufferedReader.readLine()) != null) {
                try{
                    KundeDetails kundenDetails = service.getKundeByPartnerNr(input);
    
                    String policenNummer = "";
                    String vermittlerNummer = "";
                    String sparte = "";
                    String vertragsArt = "";
                    String tarif = "";
                    String polizierungsDatum = "";
                    String telefon = "";
                    String handy = "";
                    String fax = "";
                    String email = "";
                    for (Kommunikation kommunikation : kundenDetails.getKommunikationListe()) {
                        GenPmClassKommunikationsTyp typ = kommunikation.getKommunikationTyp();
                        switch (typ) {
                            case Kom_Telefon:
                                telefon = kommunikation.getWert();
                                break;
                            case Kom_Handy:
                                handy = "\t" + kommunikation.getWert();
                                break;
                            case Kom_Fax:
                                fax = kommunikation.getWert();
                                break;
                            case Kom_eMail:
                                email = kommunikation.getWert();
                                break;
                        }
                    }
    
                    if (telefon == null || handy == null || fax == null || email == null || telefon.isEmpty() || handy.isEmpty() || email.isEmpty() || fax.isEmpty()) {
                        continue;
                    }
    
                    List<Police> policeList = service.getPoliceListe(input);
                    if (policeList.size() > 0) {
                        for (Police police : policeList) {
                            if (police.getVsnr() == null || police.getVsnr().isEmpty()) {
                                continue;
                            } else {
                                policenNummer = "\t" + police.getVsnr();
                                vermittlerNummer = "\t" + police.getBetreuerNr();
                                sparte = police.getPolicenart().getBezeichnung();
                                vertragsArt = police.getPolicenart().name();
                                tarif = "\t" + police.getTkz();
                                polizierungsDatum = dateFormat.format(police.getBeginn());
                                break;
                            }
                        }
        
                    } else {
                        continue;
                    }
    
                    String nameVN = kundenDetails.getName();
                    String vornamVN = kundenDetails.getVorname();
                    String geburtsDatum = "";
                    if (kundenDetails.getGebDatum() != null) {
                        geburtsDatum = dateFormat.format(kundenDetails.getGebDatum());
                    }
    
                    String kundenNr = String.valueOf(kundenDetails.getKundennummer());
                    String stadt = kundenDetails.getAdresse().getOrt();
                    String strasse = kundenDetails.getAdresse().getStrasse();
                    String hausNr = kundenDetails.getAdresse().getHausnummer();
                    String hausnummerZusatz = kundenDetails.getAdresse().getHausnummerZusatz();
                    String adressZusatz = kundenDetails.getAdresse().getAdressZusatz();
                    String postfach = kundenDetails.getAdresse().getPostfach();
                    String plz = kundenDetails.getAdresse().getPlz();
    
                    // Add value to Stringbuffer
                    addValue(builder, policenNummer);
                    addValue(builder, vermittlerNummer);
                    addValue(builder, sparte);
                    addValue(builder, vertragsArt);
                    addValue(builder, tarif);
                    addValue(builder, polizierungsDatum);
                    addValue(builder, nameVN);
                    addValue(builder, vornamVN);
                    addValue(builder, geburtsDatum);
                    addValue(builder, kundenNr);
                    addValue(builder, telefon);
                    addValue(builder, handy);
                    addValue(builder, fax);
                    addValue(builder, email);
                    addValue(builder, stadt);
                    addValue(builder, strasse);
                    addValue(builder, hausNr);
                    addValue(builder, hausnummerZusatz);
                    addValue(builder, adressZusatz);
                    addValue(builder, postfach);
                    addValue(builder, plz);
                    lineBreak(builder);
                    writeToFile(kundenFile, builder);
                    builder = new StringBuilder();
                } catch (RuntimeException e) {
                    notFound++;
                }
            }
            System.out.println("Spend time: " + outTime.inc());
            System.out.println("Node not found are " + notFound);
        }
    }
}

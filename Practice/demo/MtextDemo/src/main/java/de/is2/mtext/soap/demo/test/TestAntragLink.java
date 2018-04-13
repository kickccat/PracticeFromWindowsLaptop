package de.is2.mtext.soap.demo.test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import de.is20.bestandsinfo.service.api.AntragsService;
import de.is20.bestandsinfo.service.api.datatypes.antrag.AntragFragment;
import de.is20.bestandsinfo.service.api.datatypes.antrag.AntragFragmentContainer;
import de.is20.data.graphendb.befuellung.Consts;
import de.is20.data.graphendb.client.AntragsServiceAccessKSC;
import de.is20.data.graphendb.client.WebResourceProvider;
import de.is20.data.graphendb.client.WebResourceProvider.UsernameAndPassword;
import de.is20.infrastructure.filestore.FileStoreAccess;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

public class TestAntragLink {

    public static void main(final String[] args) {

        final Supplier<String> sessionID = () -> UUID.randomUUID().toString();
        final WebResourceProvider webResourceProvider = new WebResourceProvider(
                //Consts.TEST_CLIENT_ID,
                Consts.TEST_CLIENT_ID,
                //Consts.sessionID.get(),
                sessionID.get(),
                //Consts.THE_LOCAL_ROOT + Consts.PREFIX_4_USER,
                "http://test-bay1.is2.de:17474/bestandsinfo",
                new UsernameAndPassword("user", "user")

        );
        final AntragsService aService = new AntragsServiceAccessKSC(createFilestoreClient()).setWebResourceProvider(webResourceProvider);
        final Set<AntragFragment> set = new HashSet<>();
//        set.add(AntragFragment.ANG_PDF);
        set.add(AntragFragment.ANG_PDF);
        //antragid: 24931022 kundeID: 24930239
        //ANT_PDF	e3b4ab26-8d14-4819-9ad1-94c28670341d
//		ANT_PDF:
//			e3b4ab26-8d14-4819-9ad1-94c28670341d
        //http://iswasdev2.bbv.de:9082/filestore/rest/files/store/6479f328-946b-44f2-b4d6-abe9f758431f
        //http://test-bay1.is2.de:17474/bestandsinfo/ksc/kunden/24930239/antrag/24931022?key=ANG_PDF
        //	iswasdev2.bbv.de:9082/filestore/rest/files/store/d9845ad6-1163-4e92-8b3d-104e6643f7f0 -> das hier liefert was

//        final AntragFragmentContainer data = aService.load(24930239l, 24931022l, set); // 0 Bytes
        /* PartnerNodeID: 4527 PartnerNr: 100057942  AntragNodeID: 23787247 */
        final AntragFragmentContainer data = aService.load(15600019L, 15600063L, set); //
        // 4988495 Bytes

        final byte[] blob = data.getFragment(AntragFragment.ANG_PDF).value;
        String fileName = data.getAntrag().getVertragsart();
        if(null == blob) {
            System.out.println("Der Blob ist Null");
        }
        else {
            System.out.println("Der Blob hat " + blob.length  + " Bytes.");
            System.out.println("Files in Blob are " + fileName);
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("C:\\Users\\yi.zhou\\Workspace\\temp\\test\\test.pdf");
            fos.write(blob);
            fos.close();
            System.out.println("Der Blob hat wurde geschrieben nach C:/Users/yi" +
                    ".zhou/Workspace/temp/test/test.pdf");
        } catch (final IOException e) {
            System.out.println("Fehler: " );
            e.printStackTrace(System.out);
            e.printStackTrace();
        }


    }
    /**
     * MATCH (n:Antrag{ANG_PDF:'d9845ad6-1163-4e92-8b3d-104e6643f7f0'}) RETURN n LIMIT 25
     * 15600023 id Antrag
     * 15600019 id Kunde
     * http://test-bay1.is2.de:17474/bestandsinfo/ksc/kunden/15600019/antrag/15600023?key=ANG_PDF -> das hier liefert was
     * @return
     */


    static FileStoreAccess createFilestoreClient() {
        final ClientConfig clientConfig = new DefaultClientConfig();
        final Client client = Client.create(clientConfig);
        final String serverUri = "http://iswasdev2.bbv.de:9082/filestore/rest/files";
        final WebResource webResourceFilestore = client.resource(serverUri);
        return new FileStoreAccess(webResourceFilestore);
    }

}


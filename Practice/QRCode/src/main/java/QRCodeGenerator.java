import com.beust.jcommander.internal.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;

public class QRCodeGenerator {
    
    public static final String QRCODEIMAGEPATH = "C:\\Users\\yi.zhou\\Workspace\\temp\\MyQRCode.jpg";
    
    public static void main(String[] args) {
        String contents = "董栩伶，我爱你，孩他爹";
        try {
            generateQRCodeImage(contents, 350, 350, QRCODEIMAGEPATH);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    
    private static void generateQRCodeImage(String text, int width, int height, String filePath) throws
                                                                                                 WriterException, IOException {
        Map<EncodeHintType, String> hintTypeStringMap = Maps.newHashMap();
        hintTypeStringMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hintTypeStringMap);
        
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "JPG", path);
    }
    
    private static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        
        ByteArrayOutputStream jpgOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "JPG", jpgOutputStream);
        byte[] jpgData = jpgOutputStream.toByteArray();
        return jpgData;
    }
}

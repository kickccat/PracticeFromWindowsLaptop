import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCodeReader {
    
    public static void main(String[] args) {
        try {
            File file = new File(QRCodeGenerator.QRCODEIMAGEPATH);
            String decodeText = decodeQRCode(file);
            if (decodeText == null) {
                System.out.println("No QR Code found in the image");
            } else {
                System.out.println("Decode text = " + decodeText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String decodeQRCode(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        
        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

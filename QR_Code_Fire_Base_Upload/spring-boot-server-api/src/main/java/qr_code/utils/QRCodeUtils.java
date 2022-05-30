package qr_code.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import qr_code.model.Product;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Hashtable;

public class QRCodeUtils {

    public static String encode(Product product) {
        String path = "D:\\qrcode\\" + "PD-" + product.getId() + ".png";

        Hashtable hashtable = new Hashtable();
        hashtable.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonProduct = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(product);

            BitMatrix bitMatrix = new MultiFormatWriter().encode(jsonProduct, BarcodeFormat.QR_CODE, 500, 500, hashtable);
            MatrixToImageWriter.writeToPath(bitMatrix, "png", Paths.get(path));
            return path;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Product decode(BufferedImage bufferedImage) {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
        try {
            Result rs = new MultiFormatReader().decode(binaryBitmap);
            ObjectMapper mapper = new ObjectMapper();
            Product product = mapper.readValue(rs.getText(), Product.class);

            return product;
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean check2QRCode(BufferedImage bf1, BufferedImage bf2) {
        BinaryBitmap binaryBitmap1 = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bf1)));
        BinaryBitmap binaryBitmap2 = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bf2)));

        try {
            Result rs1 = new MultiFormatReader().decode(binaryBitmap1);
            Result rs2 = new MultiFormatReader().decode(binaryBitmap2);
            Product product1 = new ObjectMapper().readValue(rs1.getText(), Product.class);
            Product product2 = new ObjectMapper().readValue(rs2.getText(), Product.class);
            return product1.equals(product2);
        } catch (NotFoundException e) {
            return false ;
        } catch (JsonMappingException e) {
            return false ;
        } catch (JsonProcessingException e) {
            return false ;
        }
    }
}

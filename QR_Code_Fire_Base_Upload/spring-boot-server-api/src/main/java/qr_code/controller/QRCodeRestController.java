package qr_code.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.sun.media.jfxmedia.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qr_code.model.Category;
import qr_code.model.Product;
import qr_code.service.IProductService;
import qr_code.service.impl.ProductServiceImpl;
import qr_code.utils.QRCodeUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/qr")
public class QRCodeRestController {

    @Autowired
    IProductService productService;

    @GetMapping(value = "/encode", produces = "image/png")
    public ResponseEntity<Resource> createQRCode(@RequestBody Product product) {
        String pathQRCode = QRCodeUtils.encode(product);
        try {
            ByteArrayResource byteArrayResource = new ByteArrayResource(Files.readAllBytes(Paths.get(pathQRCode)));
            return ResponseEntity.status(HttpStatus.OK).contentLength(byteArrayResource.contentLength()).body(byteArrayResource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/decode", consumes = {"multipart/form-data"})
    public ResponseEntity<Product> decodeQRCode(@RequestParam Optional<String> type,
                                                @RequestBody(required = false) MultipartFile file,
                                                @RequestParam(required = false, defaultValue = "") String link) {
        if (!type.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String typeOfCode = type.get();

        if (typeOfCode.equals("LINK")) {
            if (!link.equals("")) {
                try {
                    BufferedImage bufferedImage = ImageIO.read(new URL(link));
                    Product product = QRCodeUtils.decode(bufferedImage);
                    return new ResponseEntity<>(product, HttpStatus.OK);
                } catch (IOException e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }


        } else if (typeOfCode.equals("FILE")) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
                Product product = QRCodeUtils.decode(bufferedImage);
                return new ResponseEntity<>(product, HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/check", consumes = {"multipart/form-data"})
    public ResponseEntity<String> check2Codes(@RequestBody MultipartFile file1, @RequestBody MultipartFile file2) {
        try {
            BufferedImage bf1 = ImageIO.read(file1.getInputStream());
            BufferedImage bf2 = ImageIO.read(file2.getInputStream());
            if (QRCodeUtils.check2QRCode(bf1, bf2)) {
                return new ResponseEntity<>("VALID", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("INVALID", HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("INVALID", HttpStatus.BAD_REQUEST);
        }
    }

}

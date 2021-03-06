package qr_code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qr_code.model.Product;
import qr_code.service.IProductService;
import qr_code.utils.QRCodeUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    @PostMapping(value = "/decode", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> decodeQRCode(@RequestBody(required = false) MultipartFile file) {

        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            Product product = QRCodeUtils.decode(bufferedImage);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/check", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> check2Codes(@RequestBody MultipartFile file1, @RequestBody MultipartFile file2) {
        try {
            BufferedImage bf1 = ImageIO.read(file1.getInputStream());
            BufferedImage bf2 = ImageIO.read(file2.getInputStream());
            if (QRCodeUtils.check2QRCode(bf1, bf2)) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/link")
    public ResponseEntity<Product> decodeByLink(@RequestParam(required = false, defaultValue = "") String link) {
        if (link.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(new URL(link));
            Product product = QRCodeUtils.decode(bufferedImage);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

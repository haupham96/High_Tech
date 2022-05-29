package qr_code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qr_code.model.Category;
import qr_code.model.Product;
import qr_code.service.IProductService;
import qr_code.utils.QRCodeUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    IProductService iProductService;

    @GetMapping("")
    public ResponseEntity<Page<Product>> pageProduct(Pageable pageable) {
        Page<Product> products = this.iProductService.findAll(PageRequest.of(pageable.getPageNumber(), 100));
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Integer id) {
        Product product = this.iProductService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/latest")
    public ResponseEntity<Product> getLatestProduct() {
        Product product = this.iProductService.getLatestProduct();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "image/png")
    public ResponseEntity<Resource> createProduct(@RequestBody Product product) {
        this.iProductService.save(product);
        Product productQRCode = this.iProductService.getLatestProduct();
        String pathProductQRCode = QRCodeUtils.encode(productQRCode);
        try {
            ByteArrayResource byteArrayResource = new ByteArrayResource(Files.readAllBytes(Paths.get(pathProductQRCode)));
            return ResponseEntity.status(HttpStatus.OK).contentLength(byteArrayResource.contentLength()).body(byteArrayResource);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}",produces = "image/png")
    public ResponseEntity<Resource> editProduct(@RequestBody Product product, @PathVariable(required = false) Integer id) {
        if (this.iProductService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.iProductService.save(product);

        String pathQrCode = QRCodeUtils.encode(product);
        try {
            ByteArrayResource byteArrayResource = new ByteArrayResource(Files.readAllBytes(Paths.get(pathQrCode)));
            return ResponseEntity.status(HttpStatus.OK).contentLength(byteArrayResource.contentLength()).body(byteArrayResource);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(required = false) Integer id) {
        if (this.iProductService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.iProductService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> listCategory() {
        List<Category> categories = this.iProductService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}

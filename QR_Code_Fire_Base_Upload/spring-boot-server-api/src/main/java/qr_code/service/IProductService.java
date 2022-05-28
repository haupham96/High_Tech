package qr_code.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import qr_code.model.Category;
import qr_code.model.Product;

import java.util.List;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);

    Product findById(Integer id);

    void save(Product product);

    void deleteById(Integer id);

    Product getLatestProduct();

    List<Category> getAllCategories();
}

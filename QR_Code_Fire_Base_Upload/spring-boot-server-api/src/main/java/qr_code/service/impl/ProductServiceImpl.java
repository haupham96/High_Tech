package qr_code.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import qr_code.model.Category;
import qr_code.model.Product;
import qr_code.repository.ICategoryRepository;
import qr_code.repository.IColorRepository;
import qr_code.repository.IProductRepository;
import qr_code.service.IProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ICategoryRepository iCategoryRepository;

    @Autowired
    IColorRepository iColorRepository;

    @Autowired
    IProductRepository iProductRepository;


    @Override
    public Page<Product> findAll(Pageable pageable) {
        return this.iProductRepository.findAll(pageable);
    }

    @Override
    public Product findById(Integer id) {
        return this.iProductRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        this.iProductRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        this.iProductRepository.deleteById(id);
    }

    @Override
    public Product getLatestProduct() {
        return this.iProductRepository.getLatestProduct();
    }

    @Override
    public List<Category> getAllCategories() {
        return this.iCategoryRepository.findAll();
    }
}

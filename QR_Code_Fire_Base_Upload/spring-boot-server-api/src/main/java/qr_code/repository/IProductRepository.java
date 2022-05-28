package qr_code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qr_code.model.Product;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Query(
            value = " select * from product order by id desc limit 1 ",
            countQuery = "select count(*) from product order by id desc limit 1 ",
            nativeQuery = true
    )
    Product getLatestProduct();
}

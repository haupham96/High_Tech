package qr_code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qr_code.model.Category;

public interface ICategoryRepository extends JpaRepository<Category,Integer> {

}

package qr_code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qr_code.model.Color;

public interface IColorRepository extends JpaRepository<Color,Integer> {
}

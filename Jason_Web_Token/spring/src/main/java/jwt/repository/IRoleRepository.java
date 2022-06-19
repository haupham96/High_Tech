package jwt.repository;

import jwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
}

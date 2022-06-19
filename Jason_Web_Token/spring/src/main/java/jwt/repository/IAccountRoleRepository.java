package jwt.repository;

import jwt.model.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRoleRepository extends JpaRepository<AccountRole, Integer> {
}

package jwt.repository;

import jwt.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Integer> {

    Account findByUserName(String userName);
}

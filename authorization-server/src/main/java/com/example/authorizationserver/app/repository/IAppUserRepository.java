package com.example.authorizationserver.app.repository;

import com.example.authorizationserver.app.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findByEmail(String username);

    AppUser findByEmailAndIsEnable(String email, boolean isEnable);
}

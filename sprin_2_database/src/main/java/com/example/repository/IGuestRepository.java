package com.example.repository;

import com.example.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IGuestRepository extends JpaRepository<Guest, Long> {

    @Query(value = "SELECT * FROM guest where guest.id = :#{#id} ;",
            nativeQuery = true)
    Guest findGuestById(Long id);
}

package com.example.repository;

import com.example.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IFriendRepository extends JpaRepository<Friend, Long> {

    @Query(value = "SELECT * FROM `sprint-2-database`.friend WHERE id = :#{#id} ; ",
            nativeQuery = true)
    Friend findFriendById(Long id);
}

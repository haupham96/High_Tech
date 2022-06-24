package com.example.repository;

import com.example.model.GuestFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface IGuestFriendRepository extends JpaRepository<GuestFriend, Long> {

    @Modifying
    @Query(value = "INSERT INTO `sprint-2-database`.`guest_friend` (`friend_id`, `guest_id`) VALUES (:#{#guestFriend.friend.id}, :#{#guestFriend.guest.id}) ; ",
            nativeQuery = true)
    void insertGuestFriend(GuestFriend guestFriend);

}

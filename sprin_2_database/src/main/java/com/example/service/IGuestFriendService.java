package com.example.service;

import com.example.model.Friend;
import com.example.model.Guest;
import com.example.model.GuestFriend;

public interface IGuestFriendService {

    Friend findFriendById(Long id);

    Guest findGuestById(Long id);

    void save(GuestFriend guestFriend);

    GuestFriend findGuestFriendById(Long id);

}

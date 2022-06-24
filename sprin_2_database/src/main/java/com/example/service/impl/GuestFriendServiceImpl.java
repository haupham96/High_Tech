package com.example.service.impl;

import com.example.model.Friend;
import com.example.model.Guest;
import com.example.model.GuestFriend;
import com.example.repository.IFriendRepository;
import com.example.repository.IGuestFriendRepository;
import com.example.repository.IGuestRepository;
import com.example.service.IGuestFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestFriendServiceImpl implements IGuestFriendService {

    @Autowired
    IGuestFriendRepository iGuestFriendRepository;

    @Autowired
    IFriendRepository iFriendRepository;

    @Autowired
    IGuestRepository iGuestRepository;

    @Override
    public Friend findFriendById(Long id) {
        return this.iFriendRepository.findFriendById(id);
    }

    @Override
    public Guest findGuestById(Long id) {
        return this.iGuestRepository.findGuestById(id);
    }

    @Override
    public void save(GuestFriend guestFriend) {
        this.iGuestFriendRepository.insertGuestFriend(guestFriend);
    }

    @Override
    public GuestFriend findGuestFriendById(Long id) {
        return this.iGuestFriendRepository.findById(id).orElse(null);
    }

}

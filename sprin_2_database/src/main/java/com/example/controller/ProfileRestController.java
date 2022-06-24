package com.example.controller;

import com.example.dto.GuestFriendDto;
import com.example.model.Friend;
import com.example.model.Guest;
import com.example.model.GuestFriend;
import com.example.service.IGuestFriendService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/profile", method = RequestMethod.GET)
public class ProfileRestController {

    @Autowired
    IGuestFriendService iGuestFriendService;

    @GetMapping("/friend/{id}")
    public ResponseEntity<Friend> showFriendProfile(@PathVariable Long id) {
        Friend friend = this.iGuestFriendService.findFriendById(id);
        if (friend == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(friend, HttpStatus.OK);
    }

    @GetMapping("/guest/{id}")
    public ResponseEntity<Guest> showPersonalProfile(@PathVariable Long id) {
        Guest guest = this.iGuestFriendService.findGuestById(id);
        if (guest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(guest, HttpStatus.OK);
    }

    @PostMapping("/add-friend")
    public ResponseEntity<Map<String, String>> addFriend(@RequestBody GuestFriendDto guestFriendDto) {
        Map<String, String> errMap = new HashMap<>();

        if (guestFriendDto == null || guestFriendDto.getFriendDTO() == null || guestFriendDto.getGuestDTO() == null) {
            errMap.put("required", "friend and guest cannot be null");
            return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
        }

        Guest guest = new Guest();
        Friend friend = new Friend();
        GuestFriend guestFriend = new GuestFriend();

        BeanUtils.copyProperties(guestFriendDto,guestFriend);
        BeanUtils.copyProperties(guestFriendDto.getGuestDTO(),guest);
        BeanUtils.copyProperties(guestFriendDto.getFriendDTO(),friend);

        guestFriend.setFriend(friend);
        guestFriend.setGuest(guest);
        System.out.println(guestFriend);

        this.iGuestFriendService.save(guestFriend);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/guest-friend/{id}")
    public ResponseEntity<GuestFriend> getGetFriend(@PathVariable Long id) {
        GuestFriend guestFriend = this.iGuestFriendService.findGuestFriendById(id);

        if (guestFriend == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(guestFriend, HttpStatus.OK);
    }
}

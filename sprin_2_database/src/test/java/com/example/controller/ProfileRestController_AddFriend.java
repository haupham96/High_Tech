package com.example.controller;

import com.example.dto.FriendDTO;
import com.example.dto.GuestDTO;
import com.example.dto.GuestFriendDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileRestController_AddFriend {

    String guestDTOString = "{" +
            "  \"id\": 1,\n" +
            "        \"name\": \"Hau1\",\n" +
            "        \"dateOfBirth\": \"2000-01-01\",\n" +
            "        \"gender\": true,\n" +
            "        \"career\": \"Freelance\",\n" +
            "        \"address\": \"166 NX DN\",\n" +
            "        \"email\": \"hau1@gmail.com\",\n" +
            "        \"image\": \"img1\",\n" +
            "        \"maritalStatus\": false,\n" +
            "        \"deleteFlag\": false" +
            "}";
    String friendDTOString = "{\n" +
            "        \"id\": 4,\n" +
            "        \"name\": \"Hau2\",\n" +
            "        \"dateOfBirth\": \"2000-01-02\",\n" +
            "        \"gender\": true,\n" +
            "        \"career\": \"abc\",\n" +
            "        \"address\": \"29 NTT\",\n" +
            "        \"email\": \"hau2@gmail.com\",\n" +
            "        \"image\": \"img2\",\n" +
            "        \"maritalStatus\": false,\n" +
            "        \"deleteFlag\": false\n" +
            "    }";


    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createGuestFriend_13() throws Exception {


        GuestFriendDto guestFriendDto = new GuestFriendDto();
        FriendDTO friendDTO = new FriendDTO();
        GuestDTO guestDTO = new GuestDTO();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/profile/add-friend")
                        .content(objectMapper.writeValueAsString(guestFriendDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createGuestFriend_15() throws Exception {
        GuestFriendDto guestFriendDto = new GuestFriendDto();
        FriendDTO friendDTO = new FriendDTO();
        GuestDTO guestDTO = new GuestDTO();
        friendDTO = objectMapper.readValue(this.friendDTOString, FriendDTO.class);
        guestFriendDto.setFriendDTO(friendDTO);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/profile/add-friend")
                        .content(objectMapper.writeValueAsString(guestFriendDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createGuestFriend_18() throws Exception {
        GuestFriendDto guestFriendDto = new GuestFriendDto();
        FriendDTO friendDTO = objectMapper.readValue(friendDTOString,FriendDTO.class);
        GuestDTO guestDTO = objectMapper.readValue(guestDTOString,GuestDTO.class);

        guestFriendDto.setGuestDTO(guestDTO);
        guestFriendDto.setFriendDTO(friendDTO);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/profile/add-friend")
                        .content(objectMapper.writeValueAsString(guestFriendDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}

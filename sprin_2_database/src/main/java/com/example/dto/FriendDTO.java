package com.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FriendDTO {
    private Long id;
    private String name;
    private String dateOfBirth;
    private boolean gender;
    private String career;
    private String address;
    private String email;
    private String image;
    private Boolean maritalStatus;
    private boolean deleteFlag;
}

package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_of_birth", nullable = false, columnDefinition = "DATE")
    private String dateOfBirth;

    @Column(name = "gender", nullable = false)
    private boolean gender;

    @Column(name = "career", nullable = false, length = 50)
    private String career;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "favorite", length = 255)
    private String favorite;

    @Column(name = "image", columnDefinition = "LONGTEXT")
    private String image;

    @Column(name = "marital_status")
    private Boolean maritalStatus; // Nếu chọn kdl boolean thì input là giá trị null => tự động gán giá trị cho biến là false => sai logic
    // Nếu chọn kdl Boolean thì input là null => db vẫn nhận giá trị null

    @Column(name = "delete_flag")
    private boolean deleteFlag;

    @JsonBackReference
    @OneToMany(mappedBy = "friend")
    private Set<GuestFriend> guestFriends;
}

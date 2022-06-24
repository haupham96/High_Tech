package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "guest")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Guest {
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

    @Column(name = "image", columnDefinition = "LONGTEXT")
    private String image;

    @Column(name = "marital_status")
    private Boolean maritalStatus; // Nếu chọn kdl boolean thì input là giá trị null => tự động gán giá trị cho biến là false => sai logic
    // Nếu chọn kdl Boolean thì input là null => db vẫn nhận giá trị null

    @Column(name = "delete_flag")
    private boolean deleteFlag;
    @OneToOne(mappedBy = "guest")
    @JsonBackReference
    private Wallet wallet;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "guest")
    @JsonBackReference
    private Set<GuestTarget> guestTargetSet;
    @OneToMany(mappedBy = "guest")
    @JsonBackReference
    private Set<Gift> giftSet;

    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "guest_report",
    joinColumns = @JoinColumn(name = "guest_id"),
    inverseJoinColumns = @JoinColumn(name = "report_id")
    )
    private Set<Report> reportSet;

    @JsonBackReference
    @OneToMany(mappedBy = "guest")
    private Set<LikePost> likePostset;

    @JsonBackReference
    @OneToMany(mappedBy = "guest")
    private Set<LikeComment> likeCommentSet;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender=" + gender +
                ", career='" + career + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", maritalStatus=" + maritalStatus +
                ", deleteFlag=" + deleteFlag +
                ", personTargetSet=" + guestTargetSet +
                '}';
    }

}

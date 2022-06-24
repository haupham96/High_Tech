package com.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class GuestFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "BIT(1) default b'0'")
    private boolean isAccept;

    @Column(columnDefinition = "BIT(1) default b'1'")
    private boolean isSuggest;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "friend_id", referencedColumnName = "id")
    private Friend friend;

    @Override
    public String toString() {
        return "GuestFriend{" +
                "id=" + id +
                ", isAccept=" + isAccept +
                ", isSuggest=" + isSuggest +
                ", guest=" + guest +
                ", friend=" + friend +
                '}';
    }
}

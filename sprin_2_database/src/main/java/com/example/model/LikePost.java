package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "guest_id", referencedColumnName = "id", nullable = false)
    private Guest guest;

    @ManyToOne()
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

    @Column(name = "like_post_flag",nullable = false)
    private boolean likePostFlag;
}

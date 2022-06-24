package com.example.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "like_comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private Guest guest;
    @ManyToOne()
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Comment comment;

    @Column(name = "like_comment_flag",nullable = false)
    private boolean likeCommentFlag;
}

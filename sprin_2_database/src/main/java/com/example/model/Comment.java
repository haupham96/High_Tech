package com.example.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "longtext",nullable = false,name="ccontext")
    private String ccontent;
    @Column(name = "time", nullable = false, columnDefinition = "DATE")
    private String time;


    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;
    @OneToMany(mappedBy = "comment")
    @JsonBackReference
    private Set<LikeComment> likeCommentSet;
    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private Guest guest;
}

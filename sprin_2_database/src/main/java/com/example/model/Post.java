package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "longtext")
    private String image;

    @Column(columnDefinition = "date", nullable = false)
    private String postDate;

    @Column(nullable = false)
    private String privacy;

    private String feeling;

    @Column(columnDefinition = "longtext", nullable = false)
    private String content;

    @OneToMany(mappedBy = "post")
    @JsonBackReference
    private Set<LikePost> likes;

    @OneToMany(mappedBy = "post")
    @JsonBackReference
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id", nullable = false)
    private Guest guest;
}

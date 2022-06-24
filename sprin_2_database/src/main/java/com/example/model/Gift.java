package com.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName ="id" )
    private CategoryGift categoryGift;
    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private Guest guest;
}

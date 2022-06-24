package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "guest_favorite")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_id", referencedColumnName = "id", nullable = false)
    private Guest guest;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorite_id", referencedColumnName = "id", nullable = false)
    private Favorite favorite;
}

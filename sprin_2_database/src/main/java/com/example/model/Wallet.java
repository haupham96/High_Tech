package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "wallet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double value;
    private Double coin;
    private Double totalMoney;
    @OneToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private Guest guest;
}

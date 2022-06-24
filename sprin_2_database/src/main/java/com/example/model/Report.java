package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "date_report", nullable = false, columnDefinition = "DATE")
    private String dateReport;
    @ManyToMany(mappedBy = "reportSet")
    @JsonBackReference
    private Set<Guest> guestSet;
}

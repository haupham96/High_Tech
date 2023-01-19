package com.example.authorizationserver.app.entity;

import com.example.authorizationserver.common.TableName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.APP_USER)
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    @Column(name = "is_enable", columnDefinition = "boolean default false")
    private boolean isEnable;
    private String role;
}

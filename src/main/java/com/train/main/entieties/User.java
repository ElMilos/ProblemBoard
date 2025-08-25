package com.train.main.entieties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password_hash;

    @Column
    private String role;

    @Column(name = "created_at")
    private Instant createdAt;

    public User() {}

    public User(String username, String email, String password_hash, String role) {
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
        this.role = role;
        this.createdAt = Instant.now();
    }


}

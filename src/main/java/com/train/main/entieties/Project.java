package com.train.main.entieties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name="projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private boolean archived;

    @Column(name = "created_at")
    private Instant createdAt;

    public Project() {}

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        this.createdAt= Instant.now();
    }
}

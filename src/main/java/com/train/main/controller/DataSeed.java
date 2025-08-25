package com.train.main.controller;

import com.train.main.entieties.Issue;
import com.train.main.entieties.Project;
import com.train.main.entieties.User;
import com.train.main.repositories.IssueRepo;
import com.train.main.repositories.ProjectRepo;
import com.train.main.repositories.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

@Configuration
public class DataSeed {
        @Bean
        CommandLineRunner seed(UserRepo users, PasswordEncoder encoder, ProjectRepo projects, IssueRepo issues) {
            return args -> {
                if (users.count() > 0) return;

                var u1 = users.save(new User( "anna", "anna@example.com", encoder.encode("pwd"), "USER"));
                var u2 = users.save(new User( "bob", "bob@example.com", encoder.encode("pwd"), "USER"));

                var p1 = projects.save(new Project());

                issues.save(new Issue());

                issues.save(new Issue());
            };
        }
    }

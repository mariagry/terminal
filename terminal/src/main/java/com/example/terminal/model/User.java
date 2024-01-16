package com.example.terminal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "terminal_settings_id")
    private TerminalSettings terminalSettings;

}

package com.example.terminal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "terminal_settings")
@Entity
@Getter
@Setter
public class TerminalSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String windowColor;
    private String textColor;
    @JsonIgnore
    @OneToOne(mappedBy = "terminalSettings")
    private User user;
}

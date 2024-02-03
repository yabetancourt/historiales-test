package com.yabetancourt.historialestest;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;

    public Usuario() {
    }

    public Usuario(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }
}

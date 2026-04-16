package com.igreja.agenda.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table (name = "usuarios")
public class Usuario {
    // getters e setters
    // ID
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    // Nome
    private String nome;
    // Email
    private String email;
    // Senha
    private String senha;
    // Role
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}

package com.igreja.agenda.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "presencas", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "evento_id"})
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Presenca {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn (name = "evento_id")
    private Evento evento;

    @Enumerated (EnumType.STRING)
    private StatusPresenca status;
}

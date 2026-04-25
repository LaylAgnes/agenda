package com.igreja.agenda.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "eventos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private LocalDate data;

    private LocalTime hora;

    
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Presenca> presencas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "criado_por")
    private Usuario criadoPor;

   

    public void addPresenca(Presenca presenca) {
        presencas.add(presenca);
        presenca.setEvento(this);
    }

    public void removePresenca(Presenca presenca) {
        presencas.remove(presenca);
        presenca.setEvento(null);
    }

    public void limparPresencas() {
        for (Presenca p : presencas) {
            p.setEvento(null);
        }
        presencas.clear();
    }
}

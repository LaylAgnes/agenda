package com.igreja.agenda.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class EventoResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private LocalTime hora;
    private String criadoPor;
}
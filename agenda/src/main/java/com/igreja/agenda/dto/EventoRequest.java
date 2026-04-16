package com.igreja.agenda.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class EventoRequest {

    // SETTERS (OBRIGATÓRIO pro Spring funcionar)
    // GETTERS CORRETOS
    @NotBlank(message = "Título obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição obrigatória")
    private String descricao;

    @NotNull(message = "Data obrigatória")
    private LocalDate data;

    @NotNull(message = "Hora obrigatória")
    private LocalTime hora;

}
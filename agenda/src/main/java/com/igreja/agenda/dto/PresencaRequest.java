package com.igreja.agenda.dto;

import com.igreja.agenda.entity.StatusPresenca;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PresencaRequest {

    @NotNull(message = "Evento é obrigatório")
    private Long eventoId;

    @NotNull(message = "Status é obrigatório")
    private StatusPresenca status;

}
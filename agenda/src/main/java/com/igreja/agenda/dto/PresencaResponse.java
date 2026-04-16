package com.igreja.agenda.dto;

import com.igreja.agenda.entity.StatusPresenca;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PresencaResponse {
    private Long id;

    private String usuario;

    private Long eventoId;

    private StatusPresenca status;
}

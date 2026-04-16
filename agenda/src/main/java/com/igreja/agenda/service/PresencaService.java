package com.igreja.agenda.service;

import com.igreja.agenda.dto.PresencaRequest;
import com.igreja.agenda.dto.PresencaResponse;
import com.igreja.agenda.entity.*;
import com.igreja.agenda.exception.BusinessException;
import com.igreja.agenda.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PresencaService {

    private final PresencaRepository presencaRepository;
    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;

    public PresencaResponse responder(PresencaRequest presencaRequest, String emailUsuario) {

        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        Evento evento = eventoRepository.findById(presencaRequest.getEventoId())
                .orElseThrow(() -> new BusinessException("Evento não encontrado"));

        Presenca presenca = presencaRepository
                .findByUsuarioIdAndEventoId(usuario.getId(), evento.getId())
                .orElse(null);

        if (presenca == null) {
            presenca = Presenca.builder()
                    .usuario(usuario)
                    .evento(evento)
                    .status(presencaRequest.getStatus())
                    .build();
        } else {
            presenca.setStatus(presencaRequest.getStatus());
        }

        Presenca salva = presencaRepository.save(presenca);

        return toResponse(salva);
    }

    public List<PresencaResponse> listarPorEvento(Long eventoId) {

        // VALIDA SE EVENTO EXISTE
        eventoRepository.findById(eventoId)
                .orElseThrow(() -> new BusinessException("Evento não encontrado"));

        return presencaRepository.findByEventoId(eventoId)
                .stream()
                // ORDENA POR NOME (UX MELHOR)
                .sorted(Comparator.comparing(p -> p.getUsuario().getNome()))
                .map(this::toResponse)
                .toList();
    }

    private PresencaResponse toResponse(Presenca presenca) {
        return PresencaResponse.builder()
                .id(presenca.getId())
                .usuario(presenca.getUsuario().getNome())
                .eventoId(presenca.getEvento().getId())
                .status(presenca.getStatus())
                .build();
    }
}
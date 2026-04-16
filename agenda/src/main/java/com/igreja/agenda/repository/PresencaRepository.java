package com.igreja.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.igreja.agenda.entity.Presenca;

import java.util.List;
import java.util.Optional;

public interface PresencaRepository extends JpaRepository<Presenca, Long> {
    Optional<Presenca> findByUsuarioIdAndEventoId(Long usuarioId, Long eventoId);
    List<Presenca> findByEventoId(Long eventoId);
}

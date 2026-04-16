package com.igreja.agenda.controller;

import com.igreja.agenda.dto.UsuarioResponse;
import com.igreja.agenda.entity.Usuario;
import com.igreja.agenda.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // OPCIONAL (recomendado remover em produção)
    // manter apenas se quiser criar usuário manualmente (fora do auth)
    @PostMapping
    public UsuarioResponse criar(@RequestBody @Valid Usuario usuario){
        return service.salvar(usuario);
    }

    // apenas ADMIN pode listar usuários
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UsuarioResponse> listar(){
        return service.listar();
    }

    // apenas ADMIN pode buscar usuário
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UsuarioResponse buscar(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    // apenas ADMIN pode deletar
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
package com.igreja.agenda.service;

import com.igreja.agenda.entity.Usuario;
import com.igreja.agenda.entity.Role;
import com.igreja.agenda.exception.BusinessException;
import com.igreja.agenda.repository.UsuarioRepository;
import com.igreja.agenda.dto.UsuarioResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponse salvar(Usuario usuario) {

        // NORMALIZA EMAIL (evita duplicidade tipo Maiusculo/minusculo)
        String email = usuario.getEmail().toLowerCase();

        // VALIDA DUPLICIDADE
        if (repository.findByEmail(email).isPresent()) {
            throw new BusinessException("Email já cadastrado");
        }

        // REGRA: sempre nasce MEMBRO
        usuario.setRole(Role.MEMBRO);

        // CRIPTOGRAFIA DE SENHA
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        // garante email normalizado
        usuario.setEmail(email);

        Usuario salvo = repository.save(usuario);

        return new UsuarioResponse(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail()
        );
    }

    public List<UsuarioResponse> listar() {
        return repository.findAll()
                .stream()
                .map(u -> new UsuarioResponse(
                        u.getId(),
                        u.getNome(),
                        u.getEmail()
                ))
                .toList();
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }

    public void deletar(Long id) {

        // valida existência antes de deletar
        if (!repository.existsById(id)) {
            throw new BusinessException("Usuário não encontrado");
        }

        repository.deleteById(id);
    }
}
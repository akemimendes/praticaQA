package com.maktronik.praticaQA.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maktronik.praticaQA.exception.CampoObrigatorioException;
import com.maktronik.praticaQA.exception.EmailJaExisteException;
import com.maktronik.praticaQA.usuario.model.Usuario;
import com.maktronik.praticaQA.usuario.repository.IUsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public Usuario save(final Usuario usuario) {

        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new CampoObrigatorioException("Email é obrigatório");
        }

        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            throw new CampoObrigatorioException("Senha é obrigatória");
        }

        if (usuario.getSenha().length() < 8) {
            throw new CampoObrigatorioException("Senha deve ter no mínimo 8 caracteres");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new EmailJaExisteException("Email já cadastrado");
        }
        return usuarioRepository.save(usuario);
    }

}

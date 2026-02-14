package com.maktronik.praticaQA.usuario.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maktronik.praticaQA.usuario.model.Usuario;
import com.maktronik.praticaQA.usuario.service.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;

    

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        Usuario novoUsuario=usuarioService.save(usuario);
        return ResponseEntity.ok().body(novoUsuario);
    }

}

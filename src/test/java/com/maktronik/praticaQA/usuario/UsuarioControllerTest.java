package com.maktronik.praticaQA.usuario;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maktronik.praticaQA.exception.CampoObrigatorioException;
import com.maktronik.praticaQA.exception.EmailJaExisteException;
import com.maktronik.praticaQA.exception.GlobalExceptionHandler;
import com.maktronik.praticaQA.usuario.controller.UsuarioController;
import com.maktronik.praticaQA.usuario.model.Usuario;
import com.maktronik.praticaQA.usuario.service.UsuarioService;

@WebMvcTest(UsuarioController.class)
@Import(GlobalExceptionHandler.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService service;

    @Autowired
    private ObjectMapper objectMapper;

    // Teste 1 - Cadastro válido
    @Test
    void deveCadastrarUsuarioQuandoDadosValidos() throws Exception {
        Usuario usuario = new Usuario(); // construtor vazio
        usuario.setEmail("novo@email.com"); // set email
        usuario.setSenha("12345678"); // set senha

        doReturn(usuario).when(service).save(any(Usuario.class)); // mock retorna objeto

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk()); // 200 OK, pois seu controller retorna ResponseEntity.ok()
    }

    // Teste 2 - Email vazio
    @Test
    void deveRetornarErroQuandoEmailVazio() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("");
        usuario.setSenha("12345678");

        doThrow(new CampoObrigatorioException("Email é obrigatório"))
                .when(service).save(any(Usuario.class));

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isBadRequest());
    }

    // Teste 3 - Senha vazia
    @Test
    void deveRetornarErroQuandoSenhaVazia() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        usuario.setSenha("");

        doThrow(new CampoObrigatorioException("Senha é obrigatória"))
                .when(service).save(any(Usuario.class));

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isBadRequest());
    }

    // Teste 4 - Senha menor que 8 caracteres
    @Test
    void deveRetornarErroQuandoSenhaMenorQue8Caracteres() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        usuario.setSenha("123");

        doThrow(new CampoObrigatorioException("Senha deve ter no mínimo 8 caracteres"))
                .when(service).save(any(Usuario.class));

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isBadRequest());
    }

    // Teste 5 - Email já cadastrado
    @Test
    void deveRetornarErroQuandoEmailJaExiste() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        usuario.setSenha("12345678");

        doThrow(new EmailJaExisteException("Email já cadastrado"))
                .when(service).save(any(Usuario.class));

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isBadRequest());
    }

}

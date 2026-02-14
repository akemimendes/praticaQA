package com.maktronik.praticaQA.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.maktronik.praticaQA.exception.CampoObrigatorioException;
import com.maktronik.praticaQA.usuario.model.Usuario;
import com.maktronik.praticaQA.usuario.repository.IUsuarioRepository;
import com.maktronik.praticaQA.usuario.service.UsuarioService;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private IUsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    // CA01: Email obrigatório
    @Test
    void deveLancarErroQuandoEmailVazio() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            Usuario usuarioMock = new Usuario();
            usuarioMock.setEmail("");
            usuarioMock.setSenha("12345678");
            service.save(usuarioMock);
        });
        assertEquals("Email é obrigatório", exception.getMessage());
    }

    // CA02: Senha obrigatória
    @Test
    void deveLancarErroQuandoSenhaVazia() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            Usuario usuarioMock = new Usuario();
            usuarioMock.setEmail("teste@hotmail.com");
            usuarioMock.setSenha("");
            service.save(usuarioMock);
        });
        assertEquals("Senha é obrigatória", exception.getMessage());
    }

    // CA03: Senha mínimo 8 caracteres
    @Test
    void deveLancarErroQuandoSenhaMenorQue8Caracteres() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            Usuario usuarioMock = new Usuario();
            usuarioMock.setEmail("teste@email.com");
            usuarioMock.setSenha("12345");
            service.save(usuarioMock);

        });
        assertEquals("Senha deve ter no mínimo 8 caracteres", exception.getMessage());
    }

    // CA04: Email único
    @Test
    void deveLancarErroQuandoEmailJaExiste() {
        when(repository.existsByEmail("teste@email.com")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            Usuario usuarioMock = new Usuario();
            usuarioMock.setEmail("teste@email.com");
            usuarioMock.setSenha("12345678");
            service.save(usuarioMock);
        });

        assertEquals("Email já cadastrado", exception.getMessage());
    }

    // CA05: Cadastro válido retorna ID
    @Test
    void deveCadastrarUsuarioQuandoDadosValidos() {
        when(repository.existsByEmail("novo@email.com")).thenReturn(false);

        Usuario usuarioMock = new Usuario();
        usuarioMock.setEmail("novo@email.com");
        usuarioMock.setSenha("12345678");
        usuarioMock.setId(1L);
        service.save(usuarioMock);

        when(repository.save(any(Usuario.class))).thenReturn(usuarioMock);

       
        Usuario resultado = service.save(usuarioMock);

        assertNotNull(resultado);
        assertNotNull(resultado.getId());
        assertEquals("novo@email.com", resultado.getEmail());
    }

     @Test
    void deveLancarExcecaoQuandoEmailNull() {
        Usuario usuario = new Usuario();
        usuario.setEmail(null);
        usuario.setSenha("12345678");

        CampoObrigatorioException exception = assertThrows(
                CampoObrigatorioException.class,
                () -> service.save(usuario));

        assertEquals("Email é obrigatório", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoSenhaNull() {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        usuario.setSenha(null);

        CampoObrigatorioException exception = assertThrows(
                CampoObrigatorioException.class,
                () -> service.save(usuario));

        assertEquals("Senha é obrigatória", exception.getMessage());
    }

    

}

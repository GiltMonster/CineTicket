package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.entity.Acesso;
import br.senac.sp.projeto.cineticketoficial.exceptions.InvalidEmailException;
import br.senac.sp.projeto.cineticketoficial.exceptions.LoginInvalidException;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.AcessoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AcessoServiceTest {
    private final AcessoRepository repository = Mockito.mock(AcessoRepository.class);
    private final ValidadorEmail validadorEmail = Mockito.mock(ValidadorEmail.class);
    private final AcessoService service = new AcessoService(repository);


    @Test
    void testBuscarAcessoComEmail_Valido() {
        Acesso acesso = new Acesso();
        acesso.setEmail("yurimcf@gmail.com");
        acesso.setSenha("123456");
        String email = "yurimcf@gmail.com";

        when(validadorEmail.validate(email)).thenReturn(true);
        when(repository.findById(email)).thenReturn(Optional.of(acesso));

        Acesso resposta = service.buscarAcessoPorEmail(email);

        assertEquals(acesso, resposta);
        verify(repository, times(1)).findById(email);
    }

    @Test
    void testBuscarAcessoComEmail_Null() {
        Acesso acesso = new Acesso();
        acesso.setEmail("yurimcf@gmail.com");
        acesso.setSenha("123456");
        String email = null;

        when(validadorEmail.validate(email)).thenReturn(false);

        assertThrows(InvalidEmailException.class, (() -> service.buscarAcessoPorEmail(email)));
        verify(repository, times(0)).findById(email);
    }

    @Test
    void testBuscarAcessoComEmail_Invalido() {
        Acesso acesso = new Acesso();
        acesso.setEmail("yurimcf@gmail.com");
        acesso.setSenha("123456");
        String email = "yurimcfgmail.com";

        when(validadorEmail.validate(email)).thenReturn(false);

        assertThrows(InvalidEmailException.class, (() -> service.buscarAcessoPorEmail(email)));
        verify(repository, times(0)).findById(email);
    }

    @Test
    void testBuscarAcessoCom_AcessoNaoEncontrado() {
        Acesso acesso = new Acesso();
        acesso.setEmail("yurimcf@gmail.com");
        acesso.setSenha("123456");
        String email = "yuri@gmail.com";

        when(validadorEmail.validate(email)).thenReturn(false);
        when(repository.findById(email)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.buscarAcessoPorEmail(email), "Recurso não encontrado.");
        verify(repository, times(1)).findById(email);
    }

    @Test
    void testAtualizarSenha_Sucesso() {
        String email = "usuario@example.com";
        String antigaSenha = "1234567";
        String novaSenha = "novaSenha123";

        Acesso acesso = new Acesso();
        acesso.setEmail(email);
        acesso.setSenha(antigaSenha);

        Acesso acessoAtualizado = new Acesso();
        acessoAtualizado.setEmail(email);
        acessoAtualizado.setSenha(novaSenha);

        when(repository.findById(email)).thenReturn(Optional.of(acesso));
        when(repository.save(acessoAtualizado)).thenReturn(acessoAtualizado);

        Acesso resultado = service.atualizarSenha(acessoAtualizado);

        assertNotNull(resultado);
        assertEquals(novaSenha, resultado.getSenha());
        verify(repository, times(1)).findById(email);
        verify(repository, times(1)).save(acesso);
    }

    @Test
    void testAtualizarSenha_AcessoNaoEncontrado() {
        String email = "usuario@example.com";
        String novaSenha = "novaSenha123";

        Acesso acesso = new Acesso();
        acesso.setEmail(email);
        acesso.setSenha(novaSenha);

        when(repository.findById(email)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.atualizarSenha(acesso), "Recurso não encontrado.");
        verify(repository, times(1)).findById(email);
        verify(repository, never()).save(acesso);
    }

    @Test
    void testValidarLogin_CredenciaisValidas() {
        Acesso acesso = new Acesso();
        acesso.setEmail("usuario@example.com");
        acesso.setSenha("senha123");

        Acesso retorno = new Acesso();
        retorno.setEmail("usuario@example.com");
        retorno.setSenha("senha123");

        when(repository.findById("usuario@example.com")).thenReturn(Optional.of(retorno));

        Acesso resultado = service.validarLogin(acesso);

        assertSame(resultado, retorno);
        verify(repository, times(1)).findById("usuario@example.com");
    }

    @Test
    void testValidarLogin_SenhaInvalida() {
        Acesso acesso = new Acesso();
        acesso.setEmail("usuario@example.com");
        acesso.setSenha("senhaIncorreta");

        Acesso retorno = new Acesso();
        retorno.setEmail("usuario@example.com");
        retorno.setSenha("senha123");

        // Configurar o comportamento mock para o método buscarAcessoPorEmail
        when(repository.findById("usuario@example.com")).thenReturn(Optional.of(retorno));

        // Chamar o método validarLogin deve lançar uma exceção LoginInvalidException
        assertThrows(LoginInvalidException.class, () -> service.validarLogin(acesso));
    }

    @Test
    void testValidarLogin_EmailInvalido() {
        // Criar um objeto de acesso com um email inválido
        Acesso acesso = new Acesso();
        acesso.setEmail("usuario@example.com");
        acesso.setSenha("senhaIncorreta");

        when(repository.findById("usuario@example.com")).thenThrow(InvalidEmailException.class);

        // Chamar o método validarLogin deve lançar uma exceção InvalidEmailException
        assertThrows(InvalidEmailException.class, () -> service.validarLogin(acesso));
    }

    @Test()
    public void testValidarLogin_EmailNaoEncontrado() {
        Acesso acesso = new Acesso();
        acesso.setEmail("email@naoexistente.com");
        acesso.setSenha("senhaIncorreta");

        when(repository.findById("email@naoexistente.com")).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class,()->service.validarLogin(acesso));
    }

}
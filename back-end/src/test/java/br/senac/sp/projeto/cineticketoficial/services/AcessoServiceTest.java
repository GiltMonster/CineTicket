package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.entity.Acesso;
import br.senac.sp.projeto.cineticketoficial.exceptions.InvalidEmailException;
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
}
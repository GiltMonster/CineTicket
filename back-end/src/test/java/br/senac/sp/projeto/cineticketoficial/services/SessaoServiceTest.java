package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.DTO.SessaoDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Filme;
import br.senac.sp.projeto.cineticketoficial.entity.Sala;
import br.senac.sp.projeto.cineticketoficial.entity.Sessao;
import br.senac.sp.projeto.cineticketoficial.exceptions.IllegalArgumentException;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.SessaoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SessaoServiceTest {
    private final SessaoRepository repository = Mockito.mock(SessaoRepository.class);
    private final FilmeService filmeService = Mockito.mock(FilmeService.class);
    private final SalaService salaService = Mockito.mock(SalaService.class);
    private final SessaoService service = new SessaoService(repository, filmeService, salaService);

    @Test
    void testInserirSessao_Sucesso() {
        SessaoDTO sessaoDTO = new SessaoDTO();
        sessaoDTO.setDataSessao(LocalDate.of(2023, 05, 17));
        sessaoDTO.setIdSala("a1");
        sessaoDTO.setIdFilme(3);

        Sala salaTest = new Sala();
        salaTest.setIdSala("a1");

        Filme filmeTest = new Filme();
        filmeTest.setIdFilme(3);

        when(salaService.buscarSalaPorId(any(String.class))).thenReturn(salaTest);
        when(filmeService.buscarFilmePorId(any(Integer.class))).thenReturn(filmeTest);

        Sessao sessao = new Sessao();
        sessao.setIdSessao(1);
        sessao.setDataSessao(sessaoDTO.getDataSessao());
        sessao.setSala(salaService.buscarSalaPorId(sessaoDTO.getIdSala()));
        sessao.setFilme(filmeService.buscarFilmePorId(sessaoDTO.getIdFilme()));

        when(service.criarSessaoEFilme(sessaoDTO)).thenReturn(sessao);
        when(repository.save(any(Sessao.class))).thenReturn(sessao);

        Sessao resultado = service.criarSessaoEFilme(sessaoDTO);

        assertEquals(sessao, resultado);
    }

    @Test
    void testBuscarTodasSessoes_Sucesso() {
        Sessao sessao1 = new Sessao();
        sessao1.setIdSessao(1);
        Sessao sessao2 = new Sessao();
        sessao1.setIdSessao(2);
        Sessao sessao3 = new Sessao();
        sessao1.setIdSessao(3);
        Sessao sessao4 = new Sessao();
        sessao1.setIdSessao(4);
        Sessao sessao5 = new Sessao();
        sessao1.setIdSessao(5);

        List<Sessao> sessaos = new ArrayList<>();
        sessaos.add(sessao1);
        sessaos.add(sessao2);
        sessaos.add(sessao3);
        sessaos.add(sessao4);
        sessaos.add(sessao5);

        when(repository.findAll()).thenReturn(sessaos);

        List<Sessao> resultado = service.buscarTodasSessoes();

        assertNotNull(resultado);
        assertEquals(sessaos,resultado);
        assertEquals(5,sessaos.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testBuscarTodasSessoes_ListaVaria() {
        List<Sessao> sessaos = new ArrayList<>();

        when(repository.findAll()).thenReturn(sessaos);

        assertThrows(ResourceNotFoundException.class,() -> service.buscarTodasSessoes());
        verify(repository, times(1)).findAll();
    }



    @Test
    void testBuscarSessaoPorId_Sucesso() {
        Integer idBuscado = 1;
        Sessao sessao = new Sessao();
        sessao.setIdSessao(1);
        sessao.setDataSessao(LocalDate.of(2023, 12, 21));
        sessao.setSala(new Sala());
        sessao.setFilme(new Filme());


        when(repository.findById(idBuscado)).thenReturn(Optional.of(sessao));

        Sessao resultado = service.buscarSessaoPorId(idBuscado);

        assertEquals(sessao, resultado);
        assertEquals(sessao.getIdSessao(), resultado.getIdSessao());
        assertEquals(sessao.getDataSessao(), resultado.getDataSessao());
        assertEquals(sessao.getSala(), resultado.getSala());
        assertEquals(sessao.getFilme(), resultado.getFilme());
        assertEquals(sessao.getIngressoList(), resultado.getIngressoList());
        verify(repository, times(1)).findById(idBuscado);
    }

    @Test
    void testBuscarSessaoPorId_NaoEncontrado() {
        Integer idbuscado = 2;
        Filme filme = new Filme();
        filme.setIdFilme(1);
        filme.setTituloFilme("titulo 1");

        when(repository.findById(idbuscado)).thenReturn(Optional.empty());


        assertThrows(ResourceNotFoundException.class, () -> service.buscarSessaoPorId(idbuscado));
        verify(repository, times(1)).findById(idbuscado);
    }

    @Test
    void testBuscarSessaoPorId_IdNull() {
        assertThrows(IllegalArgumentException.class, () -> service.buscarSessaoPorId(null),
                "Campo 'id' não pode ser nulo e aceita valores numéricos somente");
        verify(repository, times(0)).findById(null);
    }

    @Test
    void testDelete_Sucesso() {
        Integer id = 1;
        Sessao sessao = new Sessao();
        sessao.setIdSessao(id);

        when(repository.findById(id)).thenReturn(Optional.of(sessao));

        Sessao resultado = service.deleteSessao(id);

        verify(repository, times(1)).deleteById(id);
        assertEquals(sessao, resultado);
    }

    @Test
    void testDelete_IdNaoEncontrado() {
        Integer id = 1;
        Sessao sessao = new Sessao();
        sessao.setIdSessao(id);

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.deleteSessao(id));

        verify(repository, times(0)).deleteById(id);
    }

    @Test
    void testDelete_IdNull() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteSessao(null),
                "Campo 'id' não pode ser nulo e aceita valores numéricos somente");
    }
}
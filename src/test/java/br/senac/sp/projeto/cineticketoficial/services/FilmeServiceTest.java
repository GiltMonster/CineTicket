package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.entity.Filme;
import br.senac.sp.projeto.cineticketoficial.exceptions.IllegalArgumentException;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.FilmeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmeServiceTest {
    private final FilmeRepository repository = Mockito.mock(FilmeRepository.class);
    private final FilmeService service = new FilmeService(repository);

    @Test
    void testInserirFilme_IdNull() {
        Filme filmeIdNull = new Filme();
        filmeIdNull.setTituloFilme("filmeIdNull");

        when(repository.save(filmeIdNull)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,
                () -> service.inserirFilme(filmeIdNull),
                "Campo 'id' não pode ser nulo e aceita valores numéricos somente");
    }

    @Test
    void testInserirFilme_NomeNull() {
        Filme filmeNomeNull = new Filme();
        filmeNomeNull.setIdFilme(1);

        assertThrows(IllegalArgumentException.class,
                () -> service.inserirFilme(filmeNomeNull),
                "Campo 'nome' não pode ser nulo");
    }

    @Test
    void testInserirFilme_Sucesso() {
        Filme filmeSucesso = new Filme();
        filmeSucesso.setIdFilme(1);
        filmeSucesso.setTituloFilme("teste1");

        when(repository.save(filmeSucesso)).thenReturn(filmeSucesso);

        Filme resultado = service.inserirFilme(filmeSucesso);

        assertEquals(filmeSucesso, resultado);

        verify(repository, times(1)).save(filmeSucesso);
    }


    @Test
    void testBuscarTodosFilmes_Sucesso() {
        Filme filme1 = new Filme();
        filme1.setIdFilme(1);
        filme1.setTituloFilme("titulo 1");
        Filme filme2 = new Filme();
        filme1.setIdFilme(2);
        filme1.setTituloFilme("titulo 2");
        Filme filme3 = new Filme();
        filme1.setIdFilme(3);
        filme1.setTituloFilme("titulo 3");

        List<Filme> filmes = new ArrayList<>();
        filmes.add(filme1);
        filmes.add(filme2);
        filmes.add(filme3);

        when(repository.findAll()).thenReturn(filmes);

        List<Filme> resultado = service.buscarTodosFilmes();
        assertNotNull(resultado);
        assertEquals(3, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testBuscarTodosFilmes_ListaVazia() {
        List<Filme> filmes = new ArrayList<>();

        when(repository.findAll()).thenReturn(filmes);

        assertThrows(ResourceNotFoundException.class,
                service::buscarTodosFilmes, "Recurso não encontrado.");
        verify(repository, times(1)).findAll();
    }

    @Test
    void testBuscarFilmePorId_Sucesso() {
        Integer idbuscado = 1;
        Filme filme = new Filme();
        filme.setIdFilme(1);
        filme.setTituloFilme("titulo 1");

        when(repository.findById(idbuscado)).thenReturn(Optional.of(filme));

        Filme resultado = service.buscarFilmePorId(idbuscado);

        assertEquals(filme, resultado);
        assertEquals(filme.getIdFilme(), resultado.getIdFilme());
        verify(repository, times(1)).findById(idbuscado);
    }

    @Test
    void testBuscarFilmePorId_NaoEncontrado() {
        Integer idbuscado = 2;
        Filme filme = new Filme();
        filme.setIdFilme(1);
        filme.setTituloFilme("titulo 1");

        when(repository.findById(idbuscado)).thenReturn(Optional.empty());


        assertThrows(ResourceNotFoundException.class, () -> service.buscarFilmePorId(idbuscado));
        verify(repository, times(1)).findById(idbuscado);
    }

    @Test
    void testBuscarFilmePorId_IdNull() {
        Filme filme = new Filme();
        filme.setIdFilme(1);
        filme.setTituloFilme("titulo 1");

        assertThrows(IllegalArgumentException.class, () -> service.buscarFilmePorId(null),
                "Campo 'id' não pode ser nulo e aceita valores numéricos somente");
        verify(repository, times(0)).findById(1);
    }


    @Test
    void testDelete_Sucesso() {
        Integer id = 1;
        Filme filme = new Filme();
        filme.setIdFilme(id);

        when(repository.findById(id)).thenReturn(Optional.of(filme));

        Filme resultado = service.delete(id);

        verify(repository, times(1)).deleteById(id);
        assertEquals(filme, resultado);
    }

    @Test
    void testDelete_IdNaoEncontrado() {
        Integer id = 1;
        Filme filme = new Filme();
        filme.setIdFilme(id);

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.delete(id));

        verify(repository, times(0)).deleteById(id);
    }

    @Test
    void testDelete_IdNull() {
        assertThrows(IllegalArgumentException.class, () -> service.delete(null),
                "Campo 'id' não pode ser nulo e aceita valores numéricos somente");
    }
}
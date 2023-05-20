package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.entity.Sala;
import br.senac.sp.projeto.cineticketoficial.exceptions.IllegalArgumentException;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.SalaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SalaServiceTest {
    private final SalaRepository repository = Mockito.mock(SalaRepository.class);
    private final SalaService service = new SalaService(repository);

    @Test
    void testInserirSala_IdNull() {
        Sala salaIdNull = new Sala();
        salaIdNull.setLegendado(true);

        when(repository.save(salaIdNull)).thenThrow(RuntimeException.class);

        assertThrows(IllegalArgumentException.class,
                () -> service.inserirSala(salaIdNull),
                "Campo 'id' não pode ser nulo");
    }

    @Test
    void testInserirSala_Sucesso() {
        Sala sala = new Sala();
        sala.setIdSala("a1");
        sala.setLegendado(true);

        when(repository.save(sala)).thenReturn(sala);

        Sala resultado = service.inserirSala(sala);

        assertEquals(sala, resultado);
        assertEquals(sala.getIdSala(), resultado.getIdSala());
        assertEquals(sala.isLegendado(), resultado.isLegendado());
        verify(repository, times(1)).save(sala);

    }


    @Test
    void testBuscarTodasSalas_Sucesso() {
        Sala sala1 = new Sala();
        sala1.setIdSala("a1");
        sala1.setLegendado(true);
        Sala sala2 = new Sala();
        sala1.setIdSala("a2");
        sala1.setLegendado(true);
        Sala sala3 = new Sala();
        sala1.setIdSala("b1");
        sala1.setLegendado(false);
        Sala sala4 = new Sala();
        sala1.setIdSala("b2");
        sala1.setLegendado(false);
        Sala sala5 = new Sala();
        sala1.setIdSala("c1");
        sala1.setLegendado(false);

        List<Sala> salas = new ArrayList<>();
        salas.add(sala1);
        salas.add(sala2);
        salas.add(sala3);
        salas.add(sala4);
        salas.add(sala5);

        when(repository.findAll()).thenReturn(salas);

        List<Sala> resultado = service.buscarTodasSalas();

        assertNotNull(resultado);
        assertEquals(5, resultado.size());
        assertEquals(salas.get(2), resultado.get(2));
        verify(repository, times(1)).findAll();
    }

    @Test
    void testBuscarTodasSalas_ListaVazia() {
        List<Sala> salas = new ArrayList<>();

        when(repository.findAll()).thenReturn(salas);

        assertThrows(ResourceNotFoundException.class, service::buscarTodasSalas, "Recurso não encontrado.");
        verify(repository, times(1)).findAll();

    }


    @Test
    void testBuscarSalaPorId_Sucesso() {
        String idbuscado = "a1";
        Sala sala = new Sala();
        sala.setIdSala("a1");
        sala.setLegendado(true);

        when(repository.findById(idbuscado)).thenReturn(Optional.of(sala));

        Sala resultado = service.buscarSalaPorId(idbuscado);

        assertEquals(sala, resultado);
        assertEquals(sala.getIdSala(), resultado.getIdSala());
        verify(repository, times(1)).findById(idbuscado);
    }

    @Test
    void testBuscarSalaPorId_NaoEncontrado() {
        String idbuscado = "a2";
        Sala sala = new Sala();
        sala.setIdSala("a1");
        sala.setLegendado(true);

        when(repository.findById(idbuscado)).thenReturn(Optional.empty());


        assertThrows(ResourceNotFoundException.class, () -> service.buscarSalaPorId(idbuscado));
        verify(repository, times(1)).findById(idbuscado);
    }

    @Test
    void testBuscarFilmePorId_IdNull() {
        Sala sala = new Sala();
        sala.setIdSala("a1");
        sala.setLegendado(true);

        assertThrows(IllegalArgumentException.class, () -> service.buscarSalaPorId(null),
                "Campo 'id' não pode ser nulo e aceita valores numéricos somente");
    }
}
package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.entity.Cadeira;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.CadeiraRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadeiraServiceTest {
    private final CadeiraRepository repository = Mockito.mock(CadeiraRepository.class);
    private final CadeiraService service = new CadeiraService(repository);

    @Test
    void buscarTodasCadeiras_Sucesso() {
        List<Cadeira> cadeiras = new ArrayList<>();
        cadeiras.add(new Cadeira(1));
        cadeiras.add(new Cadeira(2));
        cadeiras.add(new Cadeira(3));
        cadeiras.add(new Cadeira(4));
        cadeiras.add(new Cadeira(5));

        when(repository.findAll()).thenReturn(cadeiras);

        List<Cadeira> resultado = service.buscarTodasCadeiras();
        assertNotNull(resultado);
        assertEquals(5, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testBuscarTodasCadeiras_ListaVazia() {
        List<Cadeira> cadeiras = new ArrayList<>();

        when(repository.findAll()).thenReturn(cadeiras);

        assertThrows(ResourceNotFoundException.class,
                service::buscarTodasCadeiras, "Recurso não encontrado.");
        verify(repository, times(1)).findAll();
    }
}
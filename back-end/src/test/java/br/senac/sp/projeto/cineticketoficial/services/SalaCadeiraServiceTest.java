package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.DTO.SalaCadeiraDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Cadeira;
import br.senac.sp.projeto.cineticketoficial.entity.Sala;
import br.senac.sp.projeto.cineticketoficial.entity.SalaCadeira;
import br.senac.sp.projeto.cineticketoficial.entity.SalaCadeiraPK;
import br.senac.sp.projeto.cineticketoficial.exceptions.NullAttributesException;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.SalaCadeiraRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SalaCadeiraServiceTest {
    SalaCadeiraRepository repository = Mockito.mock(SalaCadeiraRepository.class);
    CadeiraService cadeiraService = Mockito.mock(CadeiraService.class);
    SalaService salaService = Mockito.mock(SalaService.class);
    SalaCadeiraService service = new SalaCadeiraService(repository, cadeiraService, salaService);

    @Test
    void TestInserirEAtualizarSalaCadeira_Sucesso() {
        SalaCadeiraDTO dto = new SalaCadeiraDTO();
        dto.setIdCadeira(1);
        dto.setIdSala("a1");
        dto.setOcupado(true);

        SalaCadeira salaCadeira = criarAPartirDTO(dto);

        when(repository.save(any(SalaCadeira.class))).thenReturn(salaCadeira);

        SalaCadeira resultado = service.inserirEAtualizarSalaCadeira(dto);
        assertEquals(salaCadeira, resultado);
    }

    @Test
    void TestInserirEAtualizarSalaCadeira_AtributosNulosNaSalaCadeiraDTO() {
        SalaCadeiraDTO dto = new SalaCadeiraDTO();
        dto.setIdSala("a1");
        dto.setOcupado(true);

        SalaCadeira salaCadeira = criarAPartirDTO(dto);

        when(repository.save(any(SalaCadeira.class))).thenReturn(salaCadeira);

        assertThrows(NullAttributesException.class, () -> service.inserirEAtualizarSalaCadeira(dto),
                "Todos os valores devem possuir valores");
    }

    @Test
    void testListarTodasSalaCadeiras_Sucesso() {
        SalaCadeira sc1 = new SalaCadeira();
        SalaCadeira sc2 = new SalaCadeira();
        SalaCadeira sc3 = new SalaCadeira();
        SalaCadeira sc4 = new SalaCadeira();
        SalaCadeira sc5 = new SalaCadeira();
        List<SalaCadeira> salaCadeiraList = new ArrayList<>();
        salaCadeiraList.add(sc1);
        salaCadeiraList.add(sc2);
        salaCadeiraList.add(sc3);
        salaCadeiraList.add(sc4);
        salaCadeiraList.add(sc5);

        when(repository.findAll()).thenReturn(salaCadeiraList);

        List<SalaCadeira> resultado = service.listarTodasSalaCadeiras();
        assertNotNull(resultado);
        assertEquals(5, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testListarTodasSalaCadeiras_ListaVazia() {
        List<SalaCadeira> salas = new ArrayList<>();

        when(repository.findAll()).thenReturn(salas);

        assertThrows(ResourceNotFoundException.class,
                service::listarTodasSalaCadeiras, "Recurso não encontrado.");
        verify(repository, times(1)).findAll();
    }

    @Test
    void testAtualizarStatusCadeiras_Sucesso() {
        SalaCadeiraDTO dto1 = new SalaCadeiraDTO();
        dto1.setIdCadeira(1);
        dto1.setIdSala("b1");
        dto1.setOcupado(true);
        SalaCadeiraDTO dto2 = new SalaCadeiraDTO();
        dto2.setIdCadeira(3);
        dto2.setIdSala("c1");
        dto2.setOcupado(true);
        SalaCadeiraDTO dto3 = new SalaCadeiraDTO();
        dto3.setIdCadeira(2);
        dto3.setIdSala("a1");
        dto3.setOcupado(true);

        List<SalaCadeiraDTO> cadeirasOcupadas = new ArrayList<>();
        cadeirasOcupadas.add(dto1);
        cadeirasOcupadas.add(dto2);
        cadeirasOcupadas.add(dto3);

        List<SalaCadeira> cadeirasEmUso = new ArrayList<>();
        for (SalaCadeiraDTO dto : cadeirasOcupadas) {
            SalaCadeira sc = criarAPartirDTO(dto);
            sc.setOcupado(true);
            cadeirasEmUso.add(sc);
            when(repository.save(any(SalaCadeira.class))).thenReturn(sc);
        }

        service.atualizarStatusCadeiras(cadeirasOcupadas);
        verify(repository, times(cadeirasOcupadas.size())).save(any(SalaCadeira.class));
        verify(repository, times(1)).saveAll(anyCollection());
    }

    @Test
    void testCriarEResetSalaCadeira_Sucesso() {
        List<Cadeira> cadeiras = new ArrayList<>();
        cadeiras.add(new Cadeira(1));
        cadeiras.add(new Cadeira(2));
        cadeiras.add(new Cadeira(3));

        Sala sala1 = new Sala();
        sala1.setIdSala("sala1");
        sala1.setLegendado(true);
        Sala sala2 = new Sala();
        sala2.setIdSala("sala2");
        sala2.setLegendado(false);
        List<Sala> salas = new ArrayList<>();
        salas.add(sala1);
        salas.add(sala2);

        List<SalaCadeiraDTO> dtos = new ArrayList<>();
        for (Sala sala : salas) {
            for (Cadeira cadeira : cadeiras) {
                SalaCadeiraDTO dto = new SalaCadeiraDTO();
                dto.setIdSala(sala.getIdSala());
                dto.setIdCadeira(cadeira.getIdCadeira());
                dto.setOcupado(false);
                dtos.add(dto);
            }
        }

        when(cadeiraService.buscarTodasCadeiras()).thenReturn(cadeiras);
        when(salaService.buscarTodasSalas()).thenReturn(salas);

        service.criarEResetSalaCadeiras();

        verify(cadeiraService).buscarTodasCadeiras();
        verify(salaService).buscarTodasSalas();
    }


    private SalaCadeira criarAPartirDTO(SalaCadeiraDTO dto) {
        SalaCadeiraPK pk = new SalaCadeiraPK();
        pk.setIdCadeira(dto.getIdCadeira());
        pk.setIdSala(dto.getIdSala());
        Sala sala = new Sala();
        sala.setIdSala(dto.getIdSala());
        Cadeira cadeira = new Cadeira();
        cadeira.setIdCadeira(dto.getIdCadeira());

        SalaCadeira salaCadeira = new SalaCadeira();
        salaCadeira.setSalacadeiraPK(pk);
        salaCadeira.setOcupado(dto.isOcupado());
        salaCadeira.setSala(sala);
        salaCadeira.setCadeira(cadeira);
        return salaCadeira;
    }
}
package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.DTO.IngressoDTO;
import br.senac.sp.projeto.cineticketoficial.entity.*;
import br.senac.sp.projeto.cineticketoficial.exceptions.IllegalArgumentException;
import br.senac.sp.projeto.cineticketoficial.exceptions.NullAttributesException;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.IngressoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IngressoServiceTest {
    private final IngressoRepository repository = Mockito.mock(IngressoRepository.class);
    private final ClienteService clienteService = Mockito.mock(ClienteService.class);
    private final SessaoService sessaoService = Mockito.mock(SessaoService.class);
    private final SalaCadeiraService salaCadeiraService = Mockito.mock(SalaCadeiraService.class);
    private final IngressoService service = new IngressoService(repository,clienteService,sessaoService,salaCadeiraService);

    @Test
    void testInserirIngresso_Sucesso() {
        IngressoDTO dto = new IngressoDTO();
        dto.setQuantidade(2);
        List<Cadeira> cadeiraList = new ArrayList<>();
        cadeiraList.add(new Cadeira(3));
        cadeiraList.add(new Cadeira(5));
        dto.setCadeiras(cadeiraList);
        dto.setValorUnitario(new BigDecimal(13));
        dto.setEmailCliente("exemplo@gmail.com");
        dto.setIdSessao(1);

        Ingresso ingresso = new Ingresso();
        ingresso.setIdIngresso(1);
        ingresso.setQuantidade(dto.getQuantidade());
        ingresso.setValorUnitario(dto.getValorUnitario());
        ingresso.setValorTotal(dto.getValorUnitario().multiply(new BigDecimal(dto.getQuantidade())));
        ingresso.setDataCompra(LocalDate.now());

        Cliente cliente = new Cliente();
        cliente.setEmail("exemplo@gmail.com");
        when(clienteService.buscarClientePorEmail(dto.getEmailCliente())).thenReturn(cliente);
        ingresso.setCliente(clienteService.buscarClientePorEmail(dto.getEmailCliente()));

        Sessao sessao = new Sessao();
        sessao.setIdSessao(1);
        Sala sala = new Sala();
        sala.setIdSala("a1");
        sessao.setSala(sala);
        when(sessaoService.buscarSessaoPorId(dto.getIdSessao())).thenReturn(sessao);
        ingresso.setSessao(sessao);

        when(service.inserirIngresso(dto)).thenReturn(ingresso);
        when(repository.save(any(Ingresso.class))).thenReturn(ingresso);

        Ingresso resultado = service.inserirIngresso(dto);

        assertEquals(ingresso,resultado);
        verify(repository,times(1)).save(any(Ingresso.class));
    }

    @Test
    void testInserirIngresso_AtributoDTONull() {
        IngressoDTO dto = new IngressoDTO();
        List<Cadeira> cadeiraList = new ArrayList<>();
        cadeiraList.add(new Cadeira(3));
        cadeiraList.add(new Cadeira(5));
        dto.setCadeiras(cadeiraList);
        dto.setValorUnitario(new BigDecimal(13));
        dto.setEmailCliente("exemplo@gmail.com");
        dto.setIdSessao(1);

        Ingresso ingresso = new Ingresso();
        ingresso.setIdIngresso(1);
        ingresso.setQuantidade(dto.getQuantidade());
        ingresso.setValorUnitario(dto.getValorUnitario());
        ingresso.setDataCompra(LocalDate.now());

        Cliente cliente = new Cliente();
        cliente.setEmail("exemplo@gmail.com");
        when(clienteService.buscarClientePorEmail(dto.getEmailCliente())).thenReturn(cliente);
        ingresso.setCliente(clienteService.buscarClientePorEmail(dto.getEmailCliente()));

        Sessao sessao = new Sessao();
        sessao.setIdSessao(1);
        Sala sala = new Sala();
        sala.setIdSala("a1");
        sessao.setSala(sala);
        when(sessaoService.buscarSessaoPorId(dto.getIdSessao())).thenReturn(sessao);
        ingresso.setSessao(sessao);

        assertThrows(NullAttributesException.class, () -> service.inserirIngresso(dto));
        verify(repository,times(0)).save(any(Ingresso.class));
    }


    @Test
    void testBuscarTodosIngressos_Sucesso() {
        List<Ingresso> ingressos = new ArrayList<>();
        ingressos.add(new Ingresso());
        ingressos.add(new Ingresso());
        ingressos.add(new Ingresso());
        ingressos.add(new Ingresso());
        ingressos.add(new Ingresso());
        when(repository.findAll()).thenReturn(ingressos);

        List<Ingresso> resultado = service.buscarTodosIngressos();

        assertNotNull(resultado);
        assertEquals(ingressos, resultado);
        assertEquals(ingressos.size(), resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testBuscarTodosIngressos_ListaVazia() {
        List<Ingresso> ingressos = new ArrayList<>();

        when(repository.findAll()).thenReturn(ingressos);

        assertThrows(ResourceNotFoundException.class,
                service::buscarTodosIngressos, "Recurso não encontrado.");
        verify(repository, times(1)).findAll();
    }

    @Test
    void testBuscarIngressoPorId_Sucesso() {
        Integer idBuscado = 2;
        Ingresso ingresso = new Ingresso();
        ingresso.setIdIngresso(2);
        ingresso.setQuantidade(1);
        ingresso.setValorUnitario(new BigDecimal(12));
        ingresso.setSessao(new Sessao());
        ingresso.setDataCompra(LocalDate.of(2023,05,20));
        ingresso.setValorTotal(new BigDecimal(12));
        ingresso.setCliente(new Cliente());

        when(repository.findById(idBuscado)).thenReturn(Optional.of(ingresso));

        Ingresso resultado = service.buscarIngressoPorId(idBuscado);

        assertEquals(ingresso, resultado);
        assertEquals(idBuscado,resultado.getIdIngresso());
        verify(repository, times(1)).findById(idBuscado);
    }

    @Test
    void testBuscarIngressoPorId_NaoEncontrado() {
        Integer idbuscado = 2;
        Ingresso ingresso = new Ingresso();
        ingresso.setIdIngresso(2);
        ingresso.setQuantidade(1);
        ingresso.setValorUnitario(new BigDecimal(12));
        ingresso.setSessao(new Sessao());
        ingresso.setDataCompra(LocalDate.of(2023,05,20));
        ingresso.setValorTotal(new BigDecimal(12));
        ingresso.setCliente(new Cliente());

        when(repository.findById(idbuscado)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarIngressoPorId(idbuscado));
        verify(repository, times(1)).findById(idbuscado);
    }

    @Test
    void testBuscarIngressoPorId_IdNull() {
        assertThrows(IllegalArgumentException.class, () -> service.buscarIngressoPorId(null),
                "Campo 'id' não pode ser nulo e aceita valores numéricos somente");
        verify(repository, times(0)).findById(null);
    }

    @Test
    void testExcluir_Sucesso() {
        Integer id = 1;
        Ingresso ingresso = new Ingresso();
        ingresso.setIdIngresso(id);

        when(repository.findById(id)).thenReturn(Optional.of(ingresso));

        Ingresso resultado = service.excluirIngresso(id);

        verify(repository, times(1)).deleteById(id);
        assertEquals(id, resultado.getIdIngresso());
    }

    @Test
    void testDeleteId_NaoEncontrado() {
        Integer id = 1;
        Ingresso ingresso = new Ingresso();
        ingresso.setIdIngresso(id);

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.excluirIngresso(id));

        verify(repository, times(0)).deleteById(id);
    }

    @Test
    void testExcluir_IdNull() {
        assertThrows(IllegalArgumentException.class, () -> service.excluirIngresso(null),
                "Campo 'id' não pode ser nulo e aceita valores numéricos somente");
    }
}
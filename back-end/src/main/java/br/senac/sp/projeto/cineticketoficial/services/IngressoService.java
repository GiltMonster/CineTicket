package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.DTO.IngressoDTO;
import br.senac.sp.projeto.cineticketoficial.DTO.SalaCadeiraDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Cadeira;
import br.senac.sp.projeto.cineticketoficial.entity.Ingresso;
import br.senac.sp.projeto.cineticketoficial.exceptions.IllegalArgumentException;
import br.senac.sp.projeto.cineticketoficial.exceptions.NullAttributesException;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.IngressoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngressoService {
    private final IngressoRepository repository;
    private final ClienteService clienteService;
    private final SessaoService sessaoService;
    private final SalaCadeiraService salaCadeiraService;

    public Ingresso inserirIngresso(IngressoDTO ingressoDTO) {
        if (ingressoDTO.possuiAtributosNulos()) {
            throw new NullAttributesException();
        }
        Ingresso ingresso = criarIngressoAPartirDTO(ingressoDTO);
        atualizarStatusCadeirasComIngressoDTO(ingressoDTO, ingresso);
        return this.repository.save(ingresso);
    }

    private Ingresso criarIngressoAPartirDTO(IngressoDTO dto) {
        Ingresso ingresso = new Ingresso();
        ingresso.setDataCompra(LocalDate.now());
        ingresso.setQuantidade(dto.getQuantidade());
        ingresso.setValorUnitario(dto.getValorUnitario());
        ingresso.setValorTotal(
                valorTotol(dto.getQuantidade(), dto.getValorUnitario()));
        ingresso.setCliente(
                clienteService.buscarClientePorEmail(dto.getEmailCliente()));
        ingresso.setSessao(
                sessaoService.buscarSessaoPorId(dto.getIdSessao()));
        return ingresso;
    }

    private void atualizarStatusCadeirasComIngressoDTO(IngressoDTO dto, Ingresso ingresso) {
        List<Cadeira> cadeiras = dto.getCadeiras();
        List<SalaCadeiraDTO> salaCadeiraDTOS = new ArrayList<>();
        for (Cadeira cadeira : cadeiras) {
            SalaCadeiraDTO salaCadeiraDTO = new SalaCadeiraDTO();
            salaCadeiraDTO.setIdCadeira(cadeira.getIdCadeira());
            salaCadeiraDTO.setIdSala(ingresso.getSessao().getSala().getIdSala());
            salaCadeiraDTOS.add(salaCadeiraDTO);
        }
        this.salaCadeiraService.atualizarStatusCadeiras(salaCadeiraDTOS);
    }


    private BigDecimal valorTotol(Integer quantidade, BigDecimal valorUnitario) {
        return new BigDecimal(quantidade).multiply(valorUnitario);
    }

    public List<Ingresso> buscarTodosIngressos() {
        List<Ingresso> ingressos = this.repository.findAll();
        if (ingressos.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return ingressos;
    }

    public Ingresso buscarIngressoPorId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Campo 'id' não pode ser nulo e aceita valores numéricos somente");
        }
        return this.repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Ingresso excluirIngresso(Integer id) {
        Ingresso deleted = buscarIngressoPorId(id);
        repository.deleteById(id);
        return deleted;
    }
}

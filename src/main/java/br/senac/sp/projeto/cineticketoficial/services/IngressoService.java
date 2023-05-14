package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.DTO.IngressoDTO;
import br.senac.sp.projeto.cineticketoficial.DTO.SalaCadeiraDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Cadeira;
import br.senac.sp.projeto.cineticketoficial.entity.Ingresso;
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
        Ingresso ingresso = new Ingresso();
        ingresso.setDataCompra(LocalDate.now());
        ingresso.setQuantidade(ingressoDTO.getQuantidade());
        ingresso.setValorUnitario(ingressoDTO.getValorUnitario());
        ingresso.setValorTotal(
                valorTotol(ingressoDTO.getQuantidade(), ingressoDTO.getValorUnitario()));
        ingresso.setCliente(
                clienteService.buscarClientePorEmail(ingressoDTO.getEmailCliente()));
        ingresso.setSessao(
                sessaoService.buscarSessaoPorId(ingressoDTO.getIdSessao()));

        //refatorar isso
        List<Cadeira> cadeiras = ingressoDTO.getCadeiras();
        List<SalaCadeiraDTO> salaCadeiraDTOS = new ArrayList();
        for (Cadeira cadeira : cadeiras){
            SalaCadeiraDTO salaCadeiraDTO = new SalaCadeiraDTO();
            salaCadeiraDTO.setIdCadeira(cadeira.getIdCadeira());
            salaCadeiraDTO.setIdSala(ingresso.getSessao().getSala().getIdSala());
            salaCadeiraDTOS.add(salaCadeiraDTO);
        }
        this.salaCadeiraService.atualizarStatusCadeiras(salaCadeiraDTOS);

        return this.repository.save(ingresso);
    }


    private BigDecimal valorTotol(Integer quantidade, BigDecimal valorUnitario) {
        return new BigDecimal(quantidade).multiply(valorUnitario);
    }

    public List<Ingresso> buscarTodosIngressos() {
        return this.repository.findAll();
    }

    public Ingresso buscarIngressoPorId(Integer id) {
        return this.repository.findById(id).orElseThrow();
    }

    public Ingresso excluirIngresso(Integer id) {
        Ingresso deleted = buscarIngressoPorId(id);
        repository.deleteById(id);
        return deleted;
    }
}

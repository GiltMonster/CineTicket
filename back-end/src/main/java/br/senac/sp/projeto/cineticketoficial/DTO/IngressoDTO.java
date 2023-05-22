package br.senac.sp.projeto.cineticketoficial.DTO;

import br.senac.sp.projeto.cineticketoficial.entity.Cadeira;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class IngressoDTO {
    private Integer quantidade;
    private List<Cadeira> cadeiras;
    private BigDecimal valorUnitario;
    private String emailCliente;
    private Integer idSessao;

    public boolean possuiAtributosNulos() {
        return quantidade == null ||
                cadeiras.isEmpty() ||
                valorUnitario == null ||
                emailCliente == null ||
                idSessao == null;
    }
}

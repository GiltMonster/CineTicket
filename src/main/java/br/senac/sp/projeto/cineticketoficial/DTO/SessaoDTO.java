package br.senac.sp.projeto.cineticketoficial.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class SessaoDTO {
    private LocalDate dataSessao;
    private Integer idFilme;
    private String nomeFilme;
    private String idSala;

    public boolean possuiAtributosNulos() {
        return dataSessao == null ||
                idFilme == null ||
                idSala == null ||
                idFilme == null ;
    }
}

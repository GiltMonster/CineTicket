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
    private String idSala;
}

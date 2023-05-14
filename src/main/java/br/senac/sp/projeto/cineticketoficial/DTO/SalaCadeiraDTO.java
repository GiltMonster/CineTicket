package br.senac.sp.projeto.cineticketoficial.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SalaCadeiraDTO {
    private String idSala;
    private Integer idCadeira;
    private boolean ocupado;


}

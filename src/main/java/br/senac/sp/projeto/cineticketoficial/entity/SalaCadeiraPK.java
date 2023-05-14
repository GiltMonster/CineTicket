package br.senac.sp.projeto.cineticketoficial.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@RequiredArgsConstructor
@Embeddable
public class SalaCadeiraPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_sala")
    private String idSala;

    @Column(name = "id_cadeira")
    private Integer idCadeira;

}

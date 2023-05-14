package br.senac.sp.projeto.cineticketoficial.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "salacadeira")
@NamedQueries({
    @NamedQuery(name = "Salacadeira.findAll", query = "SELECT s FROM SalaCadeira s"),
    @NamedQuery(name = "Salacadeira.findByIdSala", query = "SELECT s FROM SalaCadeira s WHERE s.salacadeiraPK.idSala = :idSala"),
    @NamedQuery(name = "Salacadeira.findByIdCadeira", query = "SELECT s FROM SalaCadeira s WHERE s.salacadeiraPK.idCadeira = :idCadeira"),
    @NamedQuery(name = "Salacadeira.findByOcupado", query = "SELECT s FROM SalaCadeira s WHERE s.ocupado = :ocupado")})
public class SalaCadeira implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @EmbeddedId
    private SalaCadeiraPK salacadeiraPK;

    @Basic(optional = false)
    @Column(name = "ocupado")
    private boolean ocupado;

    @JoinColumn(name = "id_cadeira", referencedColumnName = "id_cadeira", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cadeira cadeira;

    @JoinColumn(name = "id_sala", referencedColumnName = "id_sala", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sala sala;


}

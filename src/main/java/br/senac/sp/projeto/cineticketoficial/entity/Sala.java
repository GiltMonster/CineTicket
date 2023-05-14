package br.senac.sp.projeto.cineticketoficial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "sala")
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s"),
    @NamedQuery(name = "Sala.findByIdSala", query = "SELECT s FROM Sala s WHERE s.idSala = :idSala"),
    @NamedQuery(name = "Sala.findByLegendado", query = "SELECT s FROM Sala s WHERE s.legendado = :legendado")})
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sala")
    private String idSala;

    @Column(name = "legendado")
    private boolean legendado;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sala", fetch = FetchType.EAGER)
    private List<Sessao> sessaoList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sala", fetch = FetchType.EAGER)
    private List<SalaCadeira> salaCadeiraList;

}

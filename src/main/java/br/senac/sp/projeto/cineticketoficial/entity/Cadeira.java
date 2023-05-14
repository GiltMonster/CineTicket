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
@Table(name = "cadeira")
@NamedQueries({
        @NamedQuery(name = "Cadeira.findAll", query = "SELECT c FROM Cadeira c"),
        @NamedQuery(name = "Cadeira.findByIdCadeira", query = "SELECT c FROM Cadeira c WHERE c.idCadeira = :idCadeira")})
public class Cadeira implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cadeira")
    private Integer idCadeira;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cadeira", fetch = FetchType.EAGER)
    private List<SalaCadeira> salaCadeiraList;
}

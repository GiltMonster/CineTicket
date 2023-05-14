
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
@Table(name = "filme")
@NamedQueries({
    @NamedQuery(name = "Filme.findAll", query = "SELECT f FROM Filme f"),
    @NamedQuery(name = "Filme.findByIdFilme", query = "SELECT f FROM Filme f WHERE f.idFilme = :idFilme"),
    @NamedQuery(name = "Filme.findByTituloFilme", query = "SELECT f FROM Filme f WHERE f.tituloFilme = :tituloFilme")})
public class Filme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_filme")
    private Integer idFilme;

    @Basic(optional = false)
    @Column(name = "titulo_filme")
    private String tituloFilme;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filme", fetch = FetchType.EAGER)
    private List<Sessao> sessaoList;
}

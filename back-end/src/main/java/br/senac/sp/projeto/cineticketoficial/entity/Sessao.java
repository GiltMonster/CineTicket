package br.senac.sp.projeto.cineticketoficial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "sessao")
@NamedQueries({
        @NamedQuery(name = "Sessao.findAll", query = "SELECT s FROM Sessao s"),
        @NamedQuery(name = "Sessao.findByIdSessao", query = "SELECT s FROM Sessao s WHERE s.idSessao = :idSessao"),
        @NamedQuery(name = "Sessao.findByDataSessao", query = "SELECT s FROM Sessao s WHERE s.dataSessao = :dataSessao")})
public class Sessao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sessao")
    private Integer idSessao;

    @Basic(optional = false)
    @Column(name = "data_sessao")
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate dataSessao;

    @JoinColumn(name = "id_filme", referencedColumnName = "id_filme")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Filme filme;

    @JoinColumn(name = "id_sala", referencedColumnName = "id_sala")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sala sala;

    //é necessario?
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessao", fetch = FetchType.EAGER)
    private List<Ingresso> ingressoList;
}

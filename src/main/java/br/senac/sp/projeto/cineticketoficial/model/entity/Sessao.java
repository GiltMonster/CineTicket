package br.senac.sp.projeto.cineticketoficial.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sessao")
@NamedQueries({
        @NamedQuery(name = "Sessao.findAll", query = "SELECT s FROM Sessao s"),
        @NamedQuery(name = "Sessao.findByIdSessao", query = "SELECT s FROM Sessao s WHERE s.idSessao = :idSessao"),
        @NamedQuery(name = "Sessao.findByDataHoraSessao", query = "SELECT s FROM Sessao s WHERE s.dataHoraSessao = :dataHoraSessao")})
public class Sessao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sessao")
    private Integer idSessao;
    @Basic(optional = false)
    @Column(name = "data_hora_sessao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraSessao;
    @JoinColumn(name = "id_filme", referencedColumnName = "id_filme")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Filme idFilme;
    @JoinColumn(name = "num_sala", referencedColumnName = "id_sala")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sala numSala;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSessao", fetch = FetchType.EAGER)
    private List<Ingresso> ingressoList;

    public Sessao() {
    }

    public Sessao(Integer idSessao) {
        this.idSessao = idSessao;
    }

    public Sessao(Integer idSessao, Date dataHoraSessao) {
        this.idSessao = idSessao;
        this.dataHoraSessao = dataHoraSessao;
    }

    public Integer getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Integer idSessao) {
        this.idSessao = idSessao;
    }

    public Date getDataHoraSessao() {
        return dataHoraSessao;
    }

    public void setDataHoraSessao(Date dataHoraSessao) {
        this.dataHoraSessao = dataHoraSessao;
    }

    public Filme getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Filme idFilme) {
        this.idFilme = idFilme;
    }

    public Sala getNumSala() {
        return numSala;
    }

    public void setNumSala(Sala numSala) {
        this.numSala = numSala;
    }

    public List<Ingresso> getIngressoList() {
        return ingressoList;
    }

    public void setIngressoList(List<Ingresso> ingressoList) {
        this.ingressoList = ingressoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSessao != null ? idSessao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sessao)) {
            return false;
        }
        Sessao other = (Sessao) object;
        if ((this.idSessao == null && other.idSessao != null) || (this.idSessao != null && !this.idSessao.equals(other.idSessao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.senac.sp.projeto.cineticketoficial.model.entity.Sessao[ idSessao=" + idSessao + " ]";
    }

}

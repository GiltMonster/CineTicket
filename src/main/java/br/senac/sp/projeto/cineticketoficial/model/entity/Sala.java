package br.senac.sp.projeto.cineticketoficial.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;


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
    @Basic(optional = false)
    @Column(name = "legendado")
    private boolean legendado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numSala", fetch = FetchType.EAGER)
    private List<Sessao> sessaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSala", fetch = FetchType.EAGER)
    private List<Cadeira> cadeiraList;

    public Sala() {
    }

    public Sala(String idSala) {
        this.idSala = idSala;
    }

    public Sala(String idSala, boolean legendado) {
        this.idSala = idSala;
        this.legendado = legendado;
    }

    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public boolean getLegendado() {
        return legendado;
    }

    public void setLegendado(boolean legendado) {
        this.legendado = legendado;
    }

    public List<Sessao> getSessaoList() {
        return sessaoList;
    }

    public void setSessaoList(List<Sessao> sessaoList) {
        this.sessaoList = sessaoList;
    }

    public List<Cadeira> getCadeiraList() {
        return cadeiraList;
    }

    public void setCadeiraList(List<Cadeira> cadeiraList) {
        this.cadeiraList = cadeiraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSala != null ? idSala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.idSala == null && other.idSala != null) || (this.idSala != null && !this.idSala.equals(other.idSala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.senac.sp.projeto.cineticketoficial.model.entity.Sala[ idSala=" + idSala + " ]";
    }

}

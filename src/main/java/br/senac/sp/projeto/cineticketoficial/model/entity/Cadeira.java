package br.senac.sp.projeto.cineticketoficial.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "cadeira")
@NamedQueries({
    @NamedQuery(name = "Cadeira.findAll", query = "SELECT c FROM Cadeira c"),
    @NamedQuery(name = "Cadeira.findByIdCadeira", query = "SELECT c FROM Cadeira c WHERE c.idCadeira = :idCadeira"),
    @NamedQuery(name = "Cadeira.findByNumCadeira", query = "SELECT c FROM Cadeira c WHERE c.numCadeira = :numCadeira"),
    @NamedQuery(name = "Cadeira.findByOcupada", query = "SELECT c FROM Cadeira c WHERE c.ocupada = :ocupada")})
public class Cadeira implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cadeira")
    private Integer idCadeira;
    @Basic(optional = false)
    @Column(name = "num_cadeira")
    private int numCadeira;
    @Basic(optional = false)
    @Column(name = "ocupada")
    private boolean ocupada;
    @JoinColumn(name = "id_sala", referencedColumnName = "id_sala")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sala idSala;

    public Cadeira() {
    }

    public Cadeira(Integer idCadeira) {
        this.idCadeira = idCadeira;
    }

    public Cadeira(Integer idCadeira, int numCadeira, boolean ocupada) {
        this.idCadeira = idCadeira;
        this.numCadeira = numCadeira;
        this.ocupada = ocupada;
    }

    public Integer getIdCadeira() {
        return idCadeira;
    }

    public void setIdCadeira(Integer idCadeira) {
        this.idCadeira = idCadeira;
    }

    public int getNumCadeira() {
        return numCadeira;
    }

    public void setNumCadeira(int numCadeira) {
        this.numCadeira = numCadeira;
    }

    public boolean getOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCadeira != null ? idCadeira.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cadeira)) {
            return false;
        }
        Cadeira other = (Cadeira) object;
        if ((this.idCadeira == null && other.idCadeira != null) || (this.idCadeira != null && !this.idCadeira.equals(other.idCadeira))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.senac.sp.projeto.cineticketoficial.model.entity.Cadeira[ idCadeira=" + idCadeira + " ]";
    }
    
}

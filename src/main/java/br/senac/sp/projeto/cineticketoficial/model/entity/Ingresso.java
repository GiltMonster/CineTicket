package br.senac.sp.projeto.cineticketoficial.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "ingresso")
@NamedQueries({
    @NamedQuery(name = "Ingresso.findAll", query = "SELECT i FROM Ingresso i"),
    @NamedQuery(name = "Ingresso.findByIdIngresso", query = "SELECT i FROM Ingresso i WHERE i.idIngresso = :idIngresso"),
    @NamedQuery(name = "Ingresso.findByQuantidadeIngresso", query = "SELECT i FROM Ingresso i WHERE i.quantidadeIngresso = :quantidadeIngresso"),
    @NamedQuery(name = "Ingresso.findByValorTotalIngresso", query = "SELECT i FROM Ingresso i WHERE i.valorTotalIngresso = :valorTotalIngresso"),
    @NamedQuery(name = "Ingresso.findByDataCompraIngresso", query = "SELECT i FROM Ingresso i WHERE i.dataCompraIngresso = :dataCompraIngresso")})
public class Ingresso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ingresso")
    private Integer idIngresso;
    @Basic(optional = false)
    @Column(name = "quantidade_ingresso")
    private int quantidadeIngresso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor_total_ingresso")
    private BigDecimal valorTotalIngresso;
    @Basic(optional = false)
    @Column(name = "data_compra_ingresso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCompraIngresso;
    @JoinColumn(name = "email_cliente", referencedColumnName = "email")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente emailCliente;
    @JoinColumn(name = "id_sessao", referencedColumnName = "id_sessao")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sessao idSessao;

    public Ingresso() {
    }

    public Ingresso(Integer idIngresso) {
        this.idIngresso = idIngresso;
    }

    public Ingresso(Integer idIngresso, int quantidadeIngresso, BigDecimal valorTotalIngresso, Date dataCompraIngresso) {
        this.idIngresso = idIngresso;
        this.quantidadeIngresso = quantidadeIngresso;
        this.valorTotalIngresso = valorTotalIngresso;
        this.dataCompraIngresso = dataCompraIngresso;
    }

    public Integer getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(Integer idIngresso) {
        this.idIngresso = idIngresso;
    }

    public int getQuantidadeIngresso() {
        return quantidadeIngresso;
    }

    public void setQuantidadeIngresso(int quantidadeIngresso) {
        this.quantidadeIngresso = quantidadeIngresso;
    }

    public BigDecimal getValorTotalIngresso() {
        return valorTotalIngresso;
    }

    public void setValorTotalIngresso(BigDecimal valorTotalIngresso) {
        this.valorTotalIngresso = valorTotalIngresso;
    }

    public Date getDataCompraIngresso() {
        return dataCompraIngresso;
    }

    public void setDataCompraIngresso(Date dataCompraIngresso) {
        this.dataCompraIngresso = dataCompraIngresso;
    }

    public Cliente getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(Cliente emailCliente) {
        this.emailCliente = emailCliente;
    }

    public Sessao getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Sessao idSessao) {
        this.idSessao = idSessao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIngresso != null ? idIngresso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingresso)) {
            return false;
        }
        Ingresso other = (Ingresso) object;
        if ((this.idIngresso == null && other.idIngresso != null) || (this.idIngresso != null && !this.idIngresso.equals(other.idIngresso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.senac.sp.projeto.cineticketoficial.model.entity.Ingresso[ idIngresso=" + idIngresso + " ]";
    }
    
}

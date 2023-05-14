package br.senac.sp.projeto.cineticketoficial.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "ingresso")
@NamedQueries({
        @NamedQuery(name = "Ingresso.findAll", query = "SELECT i FROM Ingresso i"),
        @NamedQuery(name = "Ingresso.findByIdIngresso", query = "SELECT i FROM Ingresso i WHERE i.idIngresso = :idIngresso"),
        @NamedQuery(name = "Ingresso.findByQuantidade", query = "SELECT i FROM Ingresso i WHERE i.quantidade = :quantidade"),
        @NamedQuery(name = "Ingresso.findByValorUnitario", query = "SELECT i FROM Ingresso i WHERE i.valorUnitario = :valorUnitario"),
        @NamedQuery(name = "Ingresso.findByValorTotal", query = "SELECT i FROM Ingresso i WHERE i.valorTotal = :valorTotal"),
        @NamedQuery(name = "Ingresso.findByDataCompra", query = "SELECT i FROM Ingresso i WHERE i.dataCompra = :dataCompra")})
public class Ingresso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingresso")
    private Integer idIngresso;

    @Basic(optional = false)
    @Column(name = "quantidade")
    private int quantidade;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @Basic(optional = false)
    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Basic(optional = false)
    @Column(name = "data_compra")
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate dataCompra;

    @JoinColumn(name = "email_cliente", referencedColumnName = "email")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente cliente;

    @JoinColumn(name = "id_sessao", referencedColumnName = "id_sessao")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sessao sessao;
}

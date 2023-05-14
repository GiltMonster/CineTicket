
package br.senac.sp.projeto.cineticketoficial.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "cliente")
@NamedQueries({
        @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
        @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email"),
        @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome"),
        @NamedQuery(name = "Cliente.findBySobrenome", query = "SELECT c FROM Cliente c WHERE c.sobrenome = :sobrenome"),
        @NamedQuery(name = "Cliente.findByDataNascimento", query = "SELECT c FROM Cliente c WHERE c.dataNascimento = :dataNascimento"),
        @NamedQuery(name = "Cliente.findByTelefone", query = "SELECT c FROM Cliente c WHERE c.telefone = :telefone"),
        @NamedQuery(name = "Cliente.findByEndereco", query = "SELECT c FROM Cliente c WHERE c.endereco = :endereco")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Basic(optional = false)
    @Column(name = "telefone")
    private String telefone;

    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.EAGER)
    private Acesso acesso;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emailCliente", fetch = FetchType.EAGER)
    private List<Ingresso> ingressoList;

}

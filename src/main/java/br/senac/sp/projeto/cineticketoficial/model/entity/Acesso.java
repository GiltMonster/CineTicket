package br.senac.sp.projeto.cineticketoficial.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Entity
@Table(name = "acesso")
@NamedQueries({
        @NamedQuery(name = "Acesso.findByEmailAndSenha", query = "SELECT a FROM Acesso a WHERE a.emailCliente = :email AND a.senha = :senha"),
        })
@SessionScope
public class Acesso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "email_cliente")
    private String emailCliente;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @JoinColumn(name = "email_cliente", referencedColumnName = "email", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JsonIgnore
    private Cliente cliente;

    public Acesso() {
    }

    public Acesso(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public Acesso(String emailCliente, String senha) {
        this.emailCliente = emailCliente;
        this.senha = senha;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailCliente != null ? emailCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acesso)) {
            return false;
        }
        Acesso other = (Acesso) object;
        if ((this.emailCliente == null && other.emailCliente != null) || (this.emailCliente != null && !this.emailCliente.equals(other.emailCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.senac.sp.projeto.cineticketoficial.model.entity.Acesso[ emailCliente=" + emailCliente + " ]";
    }

}



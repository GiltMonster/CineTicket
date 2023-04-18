package br.senac.sp.projeto.cineticketoficial.model.repository;

import br.senac.sp.projeto.cineticketoficial.model.entity.Acesso;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso,String> {
    Acesso findByEmailAndSenha(String email, String senha);
}

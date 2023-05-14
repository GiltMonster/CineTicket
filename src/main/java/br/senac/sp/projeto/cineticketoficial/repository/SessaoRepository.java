package br.senac.sp.projeto.cineticketoficial.repository;

import br.senac.sp.projeto.cineticketoficial.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao,Integer> {
}

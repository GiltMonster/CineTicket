package br.senac.sp.projeto.cineticketoficial.repository;

import br.senac.sp.projeto.cineticketoficial.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer> {
}

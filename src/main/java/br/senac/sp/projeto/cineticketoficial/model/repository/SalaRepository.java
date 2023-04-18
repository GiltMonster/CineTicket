package br.senac.sp.projeto.cineticketoficial.model.repository;

import br.senac.sp.projeto.cineticketoficial.model.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala,String> {
}

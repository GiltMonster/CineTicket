package br.senac.sp.projeto.cineticketoficial.repository;

import br.senac.sp.projeto.cineticketoficial.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala,String> {
}

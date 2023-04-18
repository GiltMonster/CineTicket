package br.senac.sp.projeto.cineticketoficial.model.repository;

import br.senac.sp.projeto.cineticketoficial.model.entity.Cadeira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadeiraRepository extends JpaRepository<Cadeira,Integer> {
}

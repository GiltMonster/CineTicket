package br.senac.sp.projeto.cineticketoficial.repository;

import br.senac.sp.projeto.cineticketoficial.entity.SalaCadeira;
import br.senac.sp.projeto.cineticketoficial.entity.SalaCadeiraPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaCadeiraRepository extends JpaRepository<SalaCadeira, SalaCadeiraPK> {
}

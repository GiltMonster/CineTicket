package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.entity.Sala;
import br.senac.sp.projeto.cineticketoficial.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SalaService {
    private final SalaRepository repository;

    public Sala inserirSala(Sala sala) {
        return this.repository.save(sala);
    }

    public List<Sala> buscarTodasSalas() {
        return this.repository.findAll();
    }

    public Sala buscarSalaPorId(String idSala) {
        return this.repository.findById(idSala).orElseThrow();
    }

}

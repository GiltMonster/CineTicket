package br.senac.sp.projeto.cineticketoficial.controller;

import br.senac.sp.projeto.cineticketoficial.model.entity.Sala;
import br.senac.sp.projeto.cineticketoficial.model.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalaService {
    private SalaRepository repository;

    @Autowired
    public SalaService(SalaRepository repository) {
        this.repository = repository;
    }

    public Sala add(Sala sala) {
        return this.repository.save(sala);
    }

    public Iterable<Sala> list() {
        return this.repository.findAll();
    }

    public Optional<Sala> getAcesso(String sala) {
        return this.repository.findById(sala);
    }

    public Sala delete(String sala) {
        Sala deleted = getAcesso(sala).get();
        repository.deleteById(sala);
        return deleted;
    }
}

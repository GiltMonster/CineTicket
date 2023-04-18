package br.senac.sp.projeto.cineticketoficial.controller;

import br.senac.sp.projeto.cineticketoficial.model.entity.Cadeira;
import br.senac.sp.projeto.cineticketoficial.model.repository.CadeiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadeiraService {
    private CadeiraRepository repository;

    @Autowired
    public CadeiraService(CadeiraRepository repository) {
        this.repository = repository;
    }

    public Cadeira add(Cadeira cadeira) {
        return this.repository.save(cadeira);
    }

    public Iterable<Cadeira> list() {
        return this.repository.findAll();
    }

    public Optional<Cadeira> getAcesso(Integer cadeira) {
        return this.repository.findById(cadeira);
    }

    public Cadeira delete(Integer cadeira) {
        Cadeira deleted = getAcesso(cadeira).get();
        repository.deleteById(cadeira);
        return deleted;
    }
}

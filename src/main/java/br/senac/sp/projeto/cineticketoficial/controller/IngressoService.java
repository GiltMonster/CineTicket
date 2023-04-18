package br.senac.sp.projeto.cineticketoficial.controller;

import br.senac.sp.projeto.cineticketoficial.model.entity.Ingresso;
import br.senac.sp.projeto.cineticketoficial.model.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class IngressoService {
    private IngressoRepository repository;

    @Autowired
    public IngressoService(IngressoRepository repository) {
        this.repository = repository;
    }

    public Ingresso add (Ingresso ingresso) {
        return this.repository.save(ingresso);
    }

    public Iterable<Ingresso> list () {
        return this.repository.findAll();
    }

    public Optional<Ingresso> getAcesso (Integer id) {
        return this.repository.findById(id);
    }

    public Ingresso delete (Integer id) {
        Ingresso deleted = getAcesso(id).get();
        repository.deleteById(id);
        return deleted;
    }
}

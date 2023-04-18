package br.senac.sp.projeto.cineticketoficial.controller;

import br.senac.sp.projeto.cineticketoficial.model.entity.Sessao;
import br.senac.sp.projeto.cineticketoficial.model.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessaoService {
    private SessaoRepository repository;

    @Autowired
    public SessaoService(SessaoRepository repository) {
        this.repository = repository;
    }

    public Sessao add(Sessao sessao) {
        return this.repository.save(sessao);
    }

    public Iterable<Sessao> list() {
        return this.repository.findAll();
    }

    public Optional<Sessao> getAcesso(Integer id) {
        return this.repository.findById(id);
    }

    public Sessao delete(Integer id) {
        Sessao deleted = getAcesso(id).get();
        repository.deleteById(id);
        return deleted;
    }
}
package br.senac.sp.projeto.cineticketoficial.controller;

import br.senac.sp.projeto.cineticketoficial.model.entity.Filme;
import br.senac.sp.projeto.cineticketoficial.model.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilmeService {
    private FilmeRepository repository;

    @Autowired
    public FilmeService(FilmeRepository repository) {
        this.repository = repository;
    }

    public Filme add(Filme filme) {
        return this.repository.save(filme);
    }

    public Iterable<Filme> list() {
        return this.repository.findAll();
    }

    public Optional<Filme> getAcesso(Integer id) {
        return this.repository.findById(id);
    }

    public Filme delete(Integer id) {
        Filme deleted = getAcesso(id).get();
        repository.deleteById(id);
        return deleted;
    }
}

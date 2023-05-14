package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.entity.Filme;
import br.senac.sp.projeto.cineticketoficial.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmeService {
    private final FilmeRepository repository;


    public Filme inserirFilme(Filme filme) {
        return this.repository.save(filme);
    }

    public List<Filme> buscarTodosFilmes() {
        return this.repository.findAll();
    }

    public Filme buscarFilmePorId(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    public Filme delete(Integer id) {
        Filme deleted = buscarFilmePorId(id);
        repository.deleteById(id);
        return deleted;
    }
}

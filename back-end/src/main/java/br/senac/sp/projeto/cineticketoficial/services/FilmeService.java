package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.entity.Filme;
import br.senac.sp.projeto.cineticketoficial.exceptions.IllegalArgumentException;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmeService {
    private final FilmeRepository repository;

    public Filme inserirFilme(Filme filme) {
        if (filme.getIdFilme() == null) {
            throw new IllegalArgumentException("Campo 'id' não pode ser nulo e aceita valores numéricos somente");
        }
        if (filme.getTituloFilme() == null) {
            throw new IllegalArgumentException("Campo 'nome' não pode ser nulo");
        }
        return this.repository.save(filme);
    }

    public List<Filme> buscarTodosFilmes() {
        List<Filme> filmes = this.repository.findAll();
        if (filmes.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return filmes;
    }

    public Filme buscarFilmePorId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Campo 'id' não pode ser nulo e aceita valores numéricos somente");
        }
        return this.repository.findById(id).orElseThrow(ResourceNotFoundException::new);

    }

    public Filme delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Campo 'id' não pode ser nulo e aceita valores numéricos somente");
        }
        Filme deleted = buscarFilmePorId(id);
        repository.deleteById(id);
        return deleted;
    }
}

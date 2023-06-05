package br.senac.sp.projeto.cineticketoficial.restAPIs;

import br.senac.sp.projeto.cineticketoficial.entity.Filme;
import br.senac.sp.projeto.cineticketoficial.services.FilmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/filmes")
public class FilmeRestController {
    private final FilmeService service;

    @PostMapping
    public Filme inserirFilme(@RequestBody Filme filme) {
        return this.service.inserirFilme(filme);
    }

    @GetMapping
    public List<Filme> buscarTodosFilmes() {
        return this.service.buscarTodosFilmes();
    }

    @DeleteMapping("/{idFilme}")
    public Filme excluirFilme(@PathVariable("idFilme") Integer idFilme) {
        return this.service.buscarFilmePorId(idFilme);
    }

    @GetMapping("/{idFilme}")
    public Filme buscarFilmePorId(@PathVariable("idFilme") Integer idFilme) {
        return this.service.buscarFilmePorId(idFilme);
    }
}

package br.senac.sp.projeto.cineticketoficial.view.rest;

import br.senac.sp.projeto.cineticketoficial.controller.FilmeService;
import br.senac.sp.projeto.cineticketoficial.model.entity.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filmes")
public class FilmeRestController {
    private FilmeService service;

    @Autowired
    public FilmeRestController(FilmeService service) {
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Filme addCli(@RequestBody Filme filme) {
        return this.service.add(filme);
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<Filme> list() {
        return this.service.list();
    }
}

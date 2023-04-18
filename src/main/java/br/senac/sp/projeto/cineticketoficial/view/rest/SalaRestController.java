package br.senac.sp.projeto.cineticketoficial.view.rest;

import br.senac.sp.projeto.cineticketoficial.controller.SalaService;
import br.senac.sp.projeto.cineticketoficial.model.entity.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salas")
public class SalaRestController {
    private SalaService service;

    @Autowired
    public SalaRestController(SalaService service) {
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Sala addCli(@RequestBody Sala sala) {
        return this.service.add(sala);
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<Sala> list() {
        return this.service.list();
    }
}

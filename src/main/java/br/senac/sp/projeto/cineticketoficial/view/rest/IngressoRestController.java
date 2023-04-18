package br.senac.sp.projeto.cineticketoficial.view.rest;

import br.senac.sp.projeto.cineticketoficial.controller.IngressoService;
import br.senac.sp.projeto.cineticketoficial.model.entity.Ingresso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingressos")
public class IngressoRestController {
    private IngressoService service;

    @Autowired
    public IngressoRestController(IngressoService service) {
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Ingresso addCli(@RequestBody Ingresso ingresso) {
        return this.service.add(ingresso);
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<Ingresso> list() {
        return this.service.list();
    }
}

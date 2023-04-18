package br.senac.sp.projeto.cineticketoficial.view.rest;


import br.senac.sp.projeto.cineticketoficial.controller.SessaoService;
import br.senac.sp.projeto.cineticketoficial.model.entity.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoRestController {
    private SessaoService service;

    @Autowired
    public SessaoRestController(SessaoService service) {
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Sessao addCli(@RequestBody Sessao sessao) {
        return this.service.add(sessao);
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<Sessao> list() {
        return this.service.list();
    }
}

package br.senac.sp.projeto.cineticketoficial.view.rest;


import br.senac.sp.projeto.cineticketoficial.controller.AcessoService;
import br.senac.sp.projeto.cineticketoficial.model.entity.Acesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/acessos")
public class AcessoRestController {
    private AcessoService service;

    @Autowired
    public AcessoRestController(AcessoService service) {
        this.service = service;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<Acesso> list () {
        return null;
    }
}

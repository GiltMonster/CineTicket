package br.senac.sp.projeto.cineticketoficial.view.rest;

import br.senac.sp.projeto.cineticketoficial.controller.AcessoService;
import br.senac.sp.projeto.cineticketoficial.controller.ClienteService;
import br.senac.sp.projeto.cineticketoficial.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {
    private ClienteService service;
    private AcessoService acessoService;

    @Autowired
    public ClienteRestController(ClienteService service, AcessoService acessoService) {
        this.service = service;
        this.acessoService = acessoService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Cliente addCli(@RequestBody Cliente cliente) {
        return this.service.add(cliente);
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<Cliente> list() {
        return this.service.list();
    }

    @GetMapping(value = "/{email}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Cliente delete(@PathVariable("email") String email) {
        return this.service.delete(email);
    }

    @PutMapping(value = "/{email}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Optional<Cliente> findByEmail(@PathVariable("email") String email) {
        return this.service.getCliente(email);
    }
}

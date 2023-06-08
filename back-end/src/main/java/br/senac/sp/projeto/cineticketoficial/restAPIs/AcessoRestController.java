package br.senac.sp.projeto.cineticketoficial.restAPIs;

import br.senac.sp.projeto.cineticketoficial.entity.Acesso;
import br.senac.sp.projeto.cineticketoficial.services.AcessoService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/acessos")
public class AcessoRestController {
    private final AcessoService service;

    @PostMapping
    public Acesso login(@RequestBody Acesso acesso) {
        Acesso login = service.validarLogin(acesso);
        return  login;
    }

    @PostMapping("/atualizar")
    public Acesso atualizarAcesso(@RequestBody Acesso acesso) {
        return this.service.atualizarSenha(acesso);
    }
}

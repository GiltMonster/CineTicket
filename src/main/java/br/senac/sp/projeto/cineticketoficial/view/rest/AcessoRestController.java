package br.senac.sp.projeto.cineticketoficial.view.rest;


import br.senac.sp.projeto.cineticketoficial.entity.Acesso;
import br.senac.sp.projeto.cineticketoficial.services.AcessoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/acessos")
public class AcessoRestController {
    private final AcessoService service;

    @PostMapping("/atualizar")
    public Acesso atualizarAcesso(@RequestBody Acesso acesso) {
        return this.service.atualizarSenha(acesso);
    }
}

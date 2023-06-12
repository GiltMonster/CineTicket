package br.senac.sp.projeto.cineticketoficial.restAPIs;


import br.senac.sp.projeto.cineticketoficial.DTO.SessaoDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Sessao;
import br.senac.sp.projeto.cineticketoficial.services.SessaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sessoes")
public class SessaoRestController {
    private final SessaoService service;

    @GetMapping("/test")
    public SessaoDTO exemploJSON() {
        return new SessaoDTO();
    }

    @PostMapping
    public Sessao inserirSessao(@RequestBody SessaoDTO sessaoDTO) {
        return this.service.criarSessaoEFilme(sessaoDTO);
    }

    @GetMapping
    public List<Sessao> buscarTodasSessoes() {
        return this.service.buscarTodasSessoes();
    }

    @DeleteMapping("/{idSessao}")
    public Sessao excluirSessoa(@PathVariable("idSessao") Integer idSessao) {
        return this.service.deleteSessao(idSessao);
    }
}

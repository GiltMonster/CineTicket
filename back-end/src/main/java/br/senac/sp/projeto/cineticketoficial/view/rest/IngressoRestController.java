package br.senac.sp.projeto.cineticketoficial.view.rest;

import br.senac.sp.projeto.cineticketoficial.DTO.IngressoDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Ingresso;
import br.senac.sp.projeto.cineticketoficial.services.IngressoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ingressos")
public class IngressoRestController {
    private final IngressoService service;

    @GetMapping("/test")
    public IngressoDTO exemplo() {
        IngressoDTO exemplo = new IngressoDTO();
        exemplo.setCadeiras(new ArrayList<>());
        return exemplo;
    }

    @PostMapping
    public Ingresso criarIngresso(@RequestBody IngressoDTO ingressoDTO) {
        return this.service.inserirIngresso(ingressoDTO);
    }

    @GetMapping
    public List<Ingresso> listarIngressos() {
        return this.service.buscarTodosIngressos();
    }

    @DeleteMapping("/{idIngresso}")
    public Ingresso excluirIngresso(@PathVariable("idIngresso") Integer idIngresso) {
        return this.service.excluirIngresso(idIngresso);
    }
}

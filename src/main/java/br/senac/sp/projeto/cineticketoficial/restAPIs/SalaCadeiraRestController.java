package br.senac.sp.projeto.cineticketoficial.restAPIs;

import br.senac.sp.projeto.cineticketoficial.DTO.SalaCadeiraDTO;
import br.senac.sp.projeto.cineticketoficial.entity.SalaCadeira;
import br.senac.sp.projeto.cineticketoficial.services.SalaCadeiraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sala-cadeira")
@RequiredArgsConstructor
public class SalaCadeiraRestController {
    private final SalaCadeiraService service;


    @GetMapping("/test")
    public SalaCadeiraDTO testExemplo() {
        return new SalaCadeiraDTO();
    }

    @PostMapping
    public SalaCadeira inserirEAtualizarSalaCadeira(@RequestBody SalaCadeiraDTO salaCadeiraDTO) {
        return this.service.inserirEAtualizarSalaCadeira(salaCadeiraDTO);
    }

    @GetMapping
    public List<SalaCadeira> list() {
        return this.service.listarTodasSalaCadeiras();
    }

    @GetMapping("/criar-resetar")
    public boolean salaCadeiraCriadas() {
        return this.service.criarEResetSalaCadeiras();
    }
}

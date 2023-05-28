package br.senac.sp.projeto.cineticketoficial.restAPIs;

import br.senac.sp.projeto.cineticketoficial.entity.Cadeira;
import br.senac.sp.projeto.cineticketoficial.services.CadeiraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cadeiras")
public class CadeiraRestController {
    private final CadeiraService service;

    @GetMapping
    public List<Cadeira> buscarCadeiras() {
        return this.service.buscarTodasCadeiras();
    }
}

package br.senac.sp.projeto.cineticketoficial.restAPIs;

import br.senac.sp.projeto.cineticketoficial.entity.Sala;
import br.senac.sp.projeto.cineticketoficial.services.SalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/salas")
public class SalaRestController {
    private final SalaService service;

    @PostMapping
    public Sala inserirSala(@RequestBody Sala sala) {
        return this.service.inserirSala(sala);
    }

    @GetMapping()
    public List<Sala> listSalas() {
        return this.service.buscarTodasSalas();
    }

    @GetMapping("/{idSala}")
    public Sala buscarSalaPorId(@PathVariable("idSala") String idSala) {
        return this.service.buscarSalaPorId(idSala);
    }
}
